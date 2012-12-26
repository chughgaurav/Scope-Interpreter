package com.hoppr;

import java.util.ArrayList;

public class HopprExecuter implements Executer{
	
	ArrayList<String> outputArrayForTesting = new ArrayList<String>();

	@Override
	public ArrayList<String> execute(Block block) {
		
		if(block == null){
			return null;
		}
		
		for(int i=0; i<block.getStatements().size();i++)
		{
			//if (block.getStatements().get(i) is a block
			if(block.getStatements().get(i).getClass().toString().equalsIgnoreCase(Block.class.toString()))
			{
				//call execute recursively
				this.execute((Block)block.getStatements().get(i));
			}
			else
			{
				//if left of statement is print
				if(block.getStatements().get(i).getLeft().equalsIgnoreCase("print"))
				{
					boolean isVariableFound = false;
					for(int j=i-1;j>=0;j--)
					{	
						//if (block.getStatements().get(j) is a block
						if(block.getStatements().get(j).getClass().toString().equalsIgnoreCase(Block.class.toString()))
						{
							//continue
							continue;
						}
						Statement pStatement = block.getStatements().get(i);
						Statement vStatement = block.getStatements().get(j);
						//check if left of vS == right of pS
						if(vStatement.getLeft().equalsIgnoreCase(pStatement.getRight()))
						{
							//check if the found value is numeric or not
							if(Utility.isNumber(block.getStatements().get(j).getRight())){
								//print the number value of given variable 
								System.out.println(block.getStatements().get(j).getRight());
								outputArrayForTesting.add(block.getStatements().get(j).getRight());
								isVariableFound = true;
								break;
							}
							//found value is a variable
							else{
								block.getStatements().get(i).setRight(block.getStatements().get(j).getRight());
								
							}
						}
					}
					//if required variable is still not found
					if(!isVariableFound){
						//check if block.getStatements().get(i).getParent() has parent
						if((Block)block.getStatements().get(i).getParent().getParent() != null){
							//search for required variable in the upper scopes
							isVariableFound = this.searchInUpperScope((Block)block.getStatements().get(i).getParent().getParent(),block.getStatements().get(i).getRight());
						}
					}
					
					//if required variable is still not found
					if(!isVariableFound)
					{
						//variable is not found in memory
						System.out.println("0");
						outputArrayForTesting.add("0");
					}
				}
			}
			
		}
		return outputArrayForTesting;
				
	}

	private boolean searchInUpperScope(Block block, String right)
	{
		boolean found = false;
		for(int i= block.getStatements().size() - 1; i>=0; i--)
		{
			if(block.getStatements().get(i).getClass().toString().equalsIgnoreCase(Block.class.toString()))
			{
				continue;
			}
			else
			{
				if(block.getStatements().get(i).getLeft().equalsIgnoreCase(right))
				{
					//check if the found value is numeric or not
					if(Utility.isNumber(block.getStatements().get(i).getRight())){
						//print the number value of given variable 
						System.out.println(block.getStatements().get(i).getRight());
						outputArrayForTesting.add(block.getStatements().get(i).getRight());
						found = true;
						break;
					}
					//found value is a variable
					else{
						block.getStatements().get(i).setRight(block.getStatements().get(i).getRight());
						found = true;
						if((Block)block.getStatements().get(i).getParent().getParent() != null){
							//search for required variable in the upper scopes
							this.searchInUpperScope((Block)block.getStatements().get(i).getParent().getParent(),block.getStatements().get(i).getRight());
						}
						break;
					}
				}
			}
		}
		return found;
			
	}
}

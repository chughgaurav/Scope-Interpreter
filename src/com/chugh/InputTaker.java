package com.chugh;

import java.util.LinkedList;
import java.util.Scanner;

public class InputTaker {
	
	public Block getInput(){
		Scanner input = null;
		String inputString=null;
		String argumentArray[]=null;
		LinkedList<String> inputArray = new LinkedList<String>();
		Block rootBlock = null;
		Block currentBlock = null;
		
        try {
            input = new Scanner(System.in);
            while (input.hasNextLine()) {
            	    inputString = input.nextLine();
            	    //remove whitespace before & after inputString
            	    inputString=inputString.trim();
            	    //split inputString into arguments
            	    argumentArray=inputString.split(" ",0);
            	    inputArray.clear();
            	    for(int i=0;i<argumentArray.length;i++)
            	    {
            	    	//if argumentArray[i] is not null
            	    	if(!argumentArray[i].isEmpty())
            	    	{	
            	    		//then add argumentArray[i] into inputArray 
            	    		inputArray.add(argumentArray[i]);
            	    	}
            	    }
            	    if(inputArray.size() != 0)
            	    {
            	    	//if 1st starting square bracket has not been encountered
            	    	//i.e rootBlock is null
            	    	if(rootBlock == null)
            	    	{	
            	    		//check if 1st starting square bracket encountered
            	    		if(inputArray.getFirst().equalsIgnoreCase("["))
            	    		{
            	    			//initialize rootBlock
	            	    		rootBlock = new Block();
	            	    		//set rootBlock as currentBlock
	            	    		currentBlock = rootBlock;
            	    		}
            	    		//if the input does not start with [s
	            	    	else
	            	    	{
	            	    		System.out.println("Input must start with [");
	            	    	}
            	    	}
            	    	//if starting square bracket has been encountered
            	    	//i.e rootBlock has been initialized 
            	    	else
            	    	{
            	    		//if input has only 1 argument
            	    		if(inputArray.size() == 1)
            	    		{	
            	    			//if again starting square bracket is encountered 
            	    			if(inputArray.getFirst().equalsIgnoreCase("["))
                	    		{
            	    				//create new scope/block
    	            	    		Block childBlock = new Block();
    	            	    		//add child block (newly created block) to currentBlock
    	            	    		currentBlock.add(childBlock);
    	            	    		//set childBlock as currentBlock
    	            	    		currentBlock = childBlock;
                	    		}
            	    			//if ] is encountered
            	    			else if(inputArray.getFirst().equalsIgnoreCase("]"))
            	    			{	
            	    				//find parent of currentBlock
            	    				currentBlock = (Block)currentBlock.getParent();
            	    				//if currentBlock has not parent
            	    				if(currentBlock == null)
            	    				{
            	    					//then return rootBlock
            	    					return rootBlock;
            	    				}
            	    			}
            	    			//if input has only one argument, & it was neither [ nor ]
            	    			else
            	    			{
            	    				System.out.println("Single input must be either [ or ]");
            	    			}
            	    		}
            	    		//if 2 arguments were encountered
            	    		else if(inputArray.size() == 2)
            	    		{
            	    			//if left argument is print
            	    			if(inputArray.getFirst().equalsIgnoreCase("print"))
            	    			{	
            	    				//check if inputArray.getLast() is null or if it is a number
	            	    			if(inputArray.getLast()==null||Utility.isNumber(inputArray.getLast())){
	            	    				System.out.println("print must be followed a variable");
	            	    			}
	            	    			//inputArray.getLast() is neither null nor number 
	            	    			else{
	            	    				//if right argument is print
	            	    				if(inputArray.getLast().equalsIgnoreCase("print")){
	            	    					//right argument cannot be print
	            	    					System.out.println("print print is not allowed");
	            	    				}
	            	    				else{
	            	    					//right argument is not print
	            	    					currentBlock.add(new Statement(inputArray.getFirst(),inputArray.getLast()));
	            	    				}
	            	    			}
            	    			}
            	    			//check if inputArray.getFirst is a number 
            	    			else if(Utility.isNumber(inputArray.getFirst())){
            	    				System.out.println("first term must either be a variable or print command");
            	    			}
            	    			//inputArray.getFirst is a variable
            	    			else{
            	    				//inputArray.getLast() is null
            	    				if(inputArray.getLast()==null){
            	    					System.out.println("Variable must be followed by a value");
            	    				}
            	    				//inputArray.getLast() is not null
            	    				else{
            	    						currentBlock.add(new Statement(inputArray.getFirst(),inputArray.getLast()));
            	    				}
            	    			}
            	    			
            	    		}
            	    		else
            	    		{
            	    			//more than one argument is not allowed
            	    			System.out.println("Only two arguments allowed");
            	    		}
            	    	}
            	    }
            	    
            }
        } finally {
            if (input != null) {
               input.close();
            }
        }
        return rootBlock;
	}//getInput() ends
}

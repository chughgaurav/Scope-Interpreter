package tests.com.chugh.interpreter;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chugh.Block;
import com.chugh.Executer;
import com.chugh.HopprExecuter;
import com.chugh.Statement;

public class HopprExecuterTest {

	private Block rootBlock=null;
	private Block currentBlock=null; 
	Executer executer = null;
	private ArrayList<String> expectedOutputArrayForTesting=null;
	private ArrayList<String> outputArrayForTesting =null;
	
	
	@Before
	public void setUp(){
		

		
		
		rootBlock = new Block();
		currentBlock = rootBlock;
		/*Block childBlock = new Block();
		currentBlock.add(childBlock);
		currentBlock = childBlock;
		*/
		executer = new HopprExecuter();
		expectedOutputArrayForTesting = new ArrayList<String>();
		outputArrayForTesting = new ArrayList<String>();
	}

	@After
	public void tearDown() {
		rootBlock =null;
		currentBlock=null;
		executer = null;
		expectedOutputArrayForTesting =null;
		outputArrayForTesting =null;
	}
	
	private void startNewScope() {
		Block childBlock = new Block();
		currentBlock.add(childBlock);
		currentBlock = childBlock;	
	}
	
	private void getParent() {
		currentBlock = (Block)currentBlock.getParent();
	}

	@Test
	public final void testExecute() {
		
		//Case 1 - test for a variable not in memory
		setUp();
		//prepare input
		currentBlock.add(new Statement("print","a"));
		currentBlock = (Block)currentBlock.getParent();
		if(currentBlock == null)
		{
			currentBlock=rootBlock;
		}
		//prepare expected output
		expectedOutputArrayForTesting.add("0");
		//execute
		executer = new HopprExecuter();
		outputArrayForTesting = executer.execute(currentBlock);
		//assert
		Assert.assertEquals(expectedOutputArrayForTesting, outputArrayForTesting);
		tearDown();
		
		//Case 2 - test for multiple initialization
		setUp();
		//prepare input
		currentBlock.add(new Statement("a","1"));
		currentBlock.add(new Statement("print","a"));
		currentBlock.add(new Statement("a","2"));
		currentBlock.add(new Statement("print","a"));
		currentBlock = (Block)currentBlock.getParent();
		if(currentBlock == null)
		{
			currentBlock=rootBlock;
		}
		//prepare expected output
		expectedOutputArrayForTesting.add("1");
		expectedOutputArrayForTesting.add("2");
		//execute
		executer = new HopprExecuter();
		outputArrayForTesting = executer.execute(currentBlock);
		//assert
		Assert.assertEquals(expectedOutputArrayForTesting, outputArrayForTesting);
		tearDown();
		
		//Case 2 - test for multiple initialization
		setUp();
		//prepare input
		currentBlock.add(new Statement("a","1"));
		currentBlock.add(new Statement("a","2"));
		currentBlock.add(new Statement("print","a"));
		currentBlock = (Block)currentBlock.getParent();
		if(currentBlock == null)
		{
			currentBlock=rootBlock;
		}
		//prepare expected output
		expectedOutputArrayForTesting.add("2");
		//execute
		executer = new HopprExecuter();
		outputArrayForTesting = executer.execute(currentBlock);
		//assert
		Assert.assertEquals(expectedOutputArrayForTesting, outputArrayForTesting);
		tearDown();
		
		//Case 3 - test for nesting 1
		setUp();
		//prepare input
		currentBlock.add(new Statement("a","1"));
		currentBlock.add(new Statement("print","a"));
		startNewScope();
		currentBlock.add(new Statement("a","2"));
		currentBlock.add(new Statement("print","a"));
		getParent();
		if(currentBlock == null)
		{
			currentBlock=rootBlock;
		}
		//prepare expected output
		expectedOutputArrayForTesting.add("1");
		expectedOutputArrayForTesting.add("2");
		//execute
		executer = new HopprExecuter();
		outputArrayForTesting = executer.execute(currentBlock);
		//assert
		Assert.assertEquals(expectedOutputArrayForTesting, outputArrayForTesting);
		tearDown();
		
		//Case 4 - test for nesting 2
		setUp();
		//prepare input
		currentBlock.add(new Statement("a","1"));
		currentBlock.add(new Statement("print","a"));
		startNewScope();
		currentBlock.add(new Statement("print","a"));
		getParent();
		if(currentBlock == null)
		{
			currentBlock=rootBlock;
		}
		//prepare expected output
		expectedOutputArrayForTesting.add("1");
		expectedOutputArrayForTesting.add("1");
		//execute
		executer = new HopprExecuter();
		outputArrayForTesting = executer.execute(currentBlock);
		//assert
		Assert.assertEquals(expectedOutputArrayForTesting, outputArrayForTesting);
		tearDown();
		
	}
}

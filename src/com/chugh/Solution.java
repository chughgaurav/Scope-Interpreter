package com.chugh;

public class Solution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InputTaker inputTaker = new InputTaker();
		Block rootBlock = inputTaker.getInput();
		Executer executer = new HopprExecuter();
		executer.execute(rootBlock);
	}

}

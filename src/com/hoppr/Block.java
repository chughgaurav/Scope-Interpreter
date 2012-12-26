package com.hoppr;

import java.util.LinkedList;

public class Block extends Statement {

	
	private LinkedList<Statement> statements = new LinkedList<Statement>();
	
	public LinkedList<Statement> getStatements()
	{
		return this.statements;
	}
	
	public void add(Statement child)
	{
		this.statements.add(child);
		child.setParent(this);
	}
		
}

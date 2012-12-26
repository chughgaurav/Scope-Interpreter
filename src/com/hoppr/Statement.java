package com.hoppr;

public class Statement {

	private String left,right;
	private Statement parent = null;
	
	public Statement()
	{
	}
	
	public Statement(String left, String right)
	{
		this.left = left;
		this.right = right;
	}
	
	public String getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}
	
	
	public void setLeft(String left) {
		this.left = left;
	}

	public void setRight(String right) {
		this.right = right;
	}

	protected void setParent(Statement parent)
	{
		this.parent = parent;
	}
	
	public Statement getParent()
	{
		return this.parent;
	}
	
}

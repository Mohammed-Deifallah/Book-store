package controller;

public class Condition {

	private String righHandSide;
	private String operation;
	private String leftHandSide;
	
	public Condition(String left, String operation, String right) {
		this.operation = operation;
		this.righHandSide = right;
		this.leftHandSide = left;
	}

	public String getAll() {
		return righHandSide + operation + leftHandSide;
	}
	
	public String getRighHandSide() {
		return righHandSide;
	}

	public void setRighHandSide(String righHandSide) {
		this.righHandSide = righHandSide;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getLeftHandSide() {
		return leftHandSide;
	}

	public void setLeftHandSide(String leftHandSide) {
		this.leftHandSide = leftHandSide;
	}
	
}

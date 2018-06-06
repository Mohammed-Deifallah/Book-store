package controller;

public class Assignment {

	private String leftSide;
	private String rightSide;
	
	public Assignment(String left, String right) {
		this.leftSide = left;
		this.rightSide = right;
	}


	public String getAll() {
		return leftSide + "=" + rightSide;
	}
	
	public String getLeftSide() {
		return leftSide;
	}

	public void setLeftSide(String leftSide) {
		this.leftSide = leftSide;
	}

	public String getRightSide() {
		return rightSide;
	}

	public void setRightSide(String rightSide) {
		this.rightSide = rightSide;
	}

}

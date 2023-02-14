package com.roka.smarthomeg4.business;

public class ShadesControlTypeDefinition {

	
	private int ControlType;
	private String  DESC ;
	
	public ShadesControlTypeDefinition() {
		// TODO Auto-generated constructor stub
	}

	public ShadesControlTypeDefinition(int controlType, String dESC) {
		super();
		ControlType = controlType;
		DESC = dESC;
	}

	public int getControlType() {
		return ControlType;
	}

	public void setControlType(int controlType) {
		ControlType = controlType;
	}

	public String getDESC() {
		return DESC;
	}

	public void setDESC(String dESC) {
		DESC = dESC;
	}
	
	
	
	
}

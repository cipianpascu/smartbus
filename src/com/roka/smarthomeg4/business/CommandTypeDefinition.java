package com.roka.smarthomeg4.business;

public class CommandTypeDefinition {
	private int CommandTypeID;
	private String Name;

	public CommandTypeDefinition(int commandTypeID, String name) {
		super();
		CommandTypeID = commandTypeID;
		Name = name;
	}

	public CommandTypeDefinition() {
		super();
	}

	public int getCommandTypeID() {
		return CommandTypeID;
	}

	public void setCommandTypeID(int commandTypeID) {
		CommandTypeID = commandTypeID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}

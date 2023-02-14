package com.roka.smarthomeg4.business;

public class MacroButton {

	private int MacroID;
	private String MacroName;
	private int MacroIconID;
	private int SequenceNO;
	public MacroButton() {
		super();
	}
	public MacroButton(int macroID, String macroName, int macroIconID,
			int sequenceNO) {
		super();
		MacroID = macroID;
		MacroName = macroName;
		MacroIconID = macroIconID;
		SequenceNO = sequenceNO;
	}
	public int getMacroID() {
		return MacroID;
	}
	public void setMacroID(int macroID) {
		MacroID = macroID;
	}
	public String getMacroName() {
		return MacroName;
	}
	public void setMacroName(String macroName) {
		MacroName = macroName;
	}
	public int getMacroIconID() {
		return MacroIconID;
	}
	public void setMacroIconID(int macroIconID) {
		MacroIconID = macroIconID;
	}
	public int getSequenceNO() {
		return SequenceNO;
	}
	public void setSequenceNO(int sequenceNO) {
		SequenceNO = sequenceNO;
	}
	
	
}

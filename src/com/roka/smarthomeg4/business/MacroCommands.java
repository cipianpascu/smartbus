package com.roka.smarthomeg4.business;

public class MacroCommands {

	
	 private int MacroID ;
	 private int  SequenceNo ;
	 private String   Remark;
	  private int  SubnetID;
	  private int DeviceID ;
	  private int CommandTypeID ;
	  private int FirstParameter;
	  private int SecondParameter;
	  private int ThirdParameter ;
	  private int DelayMillisecondAfterSend ;
	public MacroCommands(int macroID, int sequenceNo, String remark,
			int subnetID, int deviceID, int commandTypeID, int firstParameter,
			int secondParameter, int thirdParameter,
			int delayMillisecondAfterSend) {
		super();
		MacroID = macroID;
		SequenceNo = sequenceNo;
		Remark = remark;
		SubnetID = subnetID;
		DeviceID = deviceID;
		CommandTypeID = commandTypeID;
		FirstParameter = firstParameter;
		SecondParameter = secondParameter;
		ThirdParameter = thirdParameter;
		DelayMillisecondAfterSend = delayMillisecondAfterSend;
	}
	public MacroCommands() {
		super();
	}
	public int getMacroID() {
		return MacroID;
	}
	public void setMacroID(int macroID) {
		MacroID = macroID;
	}
	public int getSequenceNo() {
		return SequenceNo;
	}
	public void setSequenceNo(int sequenceNo) {
		SequenceNo = sequenceNo;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public int getSubnetID() {
		return SubnetID;
	}
	public void setSubnetID(int subnetID) {
		SubnetID = subnetID;
	}
	public int getDeviceID() {
		return DeviceID;
	}
	public void setDeviceID(int deviceID) {
		DeviceID = deviceID;
	}
	public int getCommandTypeID() {
		return CommandTypeID;
	}
	public void setCommandTypeID(int commandTypeID) {
		CommandTypeID = commandTypeID;
	}
	public int getFirstParameter() {
		return FirstParameter;
	}
	public void setFirstParameter(int firstParameter) {
		FirstParameter = firstParameter;
	}
	public int getSecondParameter() {
		return SecondParameter;
	}
	public void setSecondParameter(int secondParameter) {
		SecondParameter = secondParameter;
	}
	public int getThirdParameter() {
		return ThirdParameter;
	}
	public void setThirdParameter(int thirdParameter) {
		ThirdParameter = thirdParameter;
	}
	public int getDelayMillisecondAfterSend() {
		return DelayMillisecondAfterSend;
	}
	public void setDelayMillisecondAfterSend(int delayMillisecondAfterSend) {
		DelayMillisecondAfterSend = delayMillisecondAfterSend;
	}
	  
	  


	  
}

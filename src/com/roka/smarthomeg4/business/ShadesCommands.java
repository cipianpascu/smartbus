package com.roka.smarthomeg4.business;

public class ShadesCommands {

	
	 private int ZoneID;
	 private int  ShadeID ;
	 private int  ShadeControlType ;
	 private int  CommandID ;
	 private int  SequenceNo ;
	 private String  Remark ;
	 private int  SubnetID ;
	 private int  DeviceID ;
	 private int  CommandTypeID ;
	 private int  FirstParameter;
	 private int  SecondParameter ;
	 private int  ThirdParameter;
	 private int  DelayMillisecondAfterSend ;
	  
	 public ShadesCommands() {
		// TODO Auto-generated constructor stub
	}

	public ShadesCommands(int zoneID, int shadeID, int shadeControlType,
			int commandID, int sequenceNo, String remark, int subnetID,
			int deviceID, int commandTypeID, int firstParameter,
			int secondParameter, int thirdParameter,
			int delayMillisecondAfterSend) {
		super();
		ZoneID = zoneID;
		ShadeID = shadeID;
		ShadeControlType = shadeControlType;
		CommandID = commandID;
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

	public int getZoneID() {
		return ZoneID;
	}

	public void setZoneID(int zoneID) {
		ZoneID = zoneID;
	}

	public int getShadeID() {
		return ShadeID;
	}

	public void setShadeID(int shadeID) {
		ShadeID = shadeID;
	}

	public int getShadeControlType() {
		return ShadeControlType;
	}

	public void setShadeControlType(int shadeControlType) {
		ShadeControlType = shadeControlType;
	}

	public int getCommandID() {
		return CommandID;
	}

	public void setCommandID(int commandID) {
		CommandID = commandID;
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

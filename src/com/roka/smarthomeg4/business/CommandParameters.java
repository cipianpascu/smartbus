package com.roka.smarthomeg4.business;

public class CommandParameters {
	public int CommandID;
	public int SequenceNo;
	public String Remark;
	public byte SubnetID;
	public byte DeviceID;
	public int CommandTypeID;
	public int FirstParameter;
	public int SecondParameter;
	public int ThirdParameter;
	public int DelayMillisecondAfterSend;
	public int zoneId;
	public int id;
	
	public CommandParameters() {
		// TODO Auto-generated constructor stub
	}

	
	
	

	



	public CommandParameters(int commandID, int sequenceNo, String remark,
			byte subnetID, byte deviceID, int commandTypeID,
			int firstParameter, int secondParameter, int thirdParameter,
			int delayMillisecondAfterSend, int zoneId, int id) {
		super();
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
		this.zoneId = zoneId;
		this.id = id;
	}






public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
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


	public byte getSubnetID() {
		return SubnetID;
	}


	public void setSubnetID(byte subnetID) {
		SubnetID = subnetID;
	}


	public byte getDeviceID() {
		return DeviceID;
	}


	public void setDeviceID(byte deviceID) {
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


	public int getZoneId() {
		return zoneId;
	}


	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	
	
	
}

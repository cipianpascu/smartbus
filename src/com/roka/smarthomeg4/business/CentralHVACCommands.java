package com.roka.smarthomeg4.business;

public class CentralHVACCommands {

	private int FloorID;
	private int CommandID;
	private int SequenceNo;
	private String Remark;
	private int SubnetID;
	private int DeviceID;
	private int CommandTypeID;
	private int FirstParameter;
	private int ThirdParameter;
	private int DelayMillisecondAfterSend;

	public CentralHVACCommands(int floorID, int commandID, int sequenceNo,
			String remark, int subnetID, int deviceID, int commandTypeID,
			int firstParameter, int thirdParameter,
			int delayMillisecondAfterSend) {
		super();
		FloorID = floorID;
		CommandID = commandID;
		SequenceNo = sequenceNo;
		Remark = remark;
		SubnetID = subnetID;
		DeviceID = deviceID;
		CommandTypeID = commandTypeID;
		FirstParameter = firstParameter;
		ThirdParameter = thirdParameter;
		DelayMillisecondAfterSend = delayMillisecondAfterSend;
	}

	public CentralHVACCommands() {
		super();
	}

	public int getFloorID() {
		return FloorID;
	}

	public void setFloorID(int floorID) {
		FloorID = floorID;
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

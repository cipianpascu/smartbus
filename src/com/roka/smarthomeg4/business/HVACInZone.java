package com.roka.smarthomeg4.business;

public class HVACInZone {
	private int ZoneID;
	private int SubnetID;
	private int DeviceID;
	private int ACNumber;
	private int ACTypeID;
	private int ACSyncNo;
	private int SequenceNo;
	private String Remark;
	
	
	
	

	public HVACInZone() {
		super();
	}

	public HVACInZone(int zoneID, int subnetID, int deviceID, int aCNumber,
			int aCTypeID, int aCSyncNo, int sequenceNo, String remark) {
		super();
		ZoneID = zoneID;
		SubnetID = subnetID;
		DeviceID = deviceID;
		ACNumber = aCNumber;
		ACTypeID = aCTypeID;
		ACSyncNo = aCSyncNo;
		SequenceNo = sequenceNo;
		Remark = remark;
	}

	public int getZoneID() {
		return ZoneID;
	}

	public void setZoneID(int zoneID) {
		ZoneID = zoneID;
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

	public int getACNumber() {
		return ACNumber;
	}

	public void setACNumber(int aCNumber) {
		ACNumber = aCNumber;
	}

	public int getACTypeID() {
		return ACTypeID;
	}

	public void setACTypeID(int aCTypeID) {
		ACTypeID = aCTypeID;
	}

	public int getACSyncNo() {
		return ACSyncNo;
	}

	public void setACSyncNo(int aCSyncNo) {
		ACSyncNo = aCSyncNo;
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

}

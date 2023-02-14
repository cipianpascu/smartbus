package com.roka.smarthomeg4.business;

public class LightInZone implements Comparable<LightInZone> {

	private int ZoneID;
	private int LightID;
	private String LightRemark;
	private int SubnetID;
	private int DeviceID;
	private int ChannelNo;
	private int CanDim;
	private int LightTypeID;
	private int SequenceNo;
	private int color;
	private int status;

	// private int oldValue;
	private int newValue;

	public LightInZone(int zoneID, int lightID, String lightRemark,
			int subnetID, int deviceID, int channelNo, int canDim,
			int lightTypeID, int sequenceNo) {
		super();
		ZoneID = zoneID;
		LightID = lightID;
		LightRemark = lightRemark;
		SubnetID = subnetID;
		DeviceID = deviceID;
		ChannelNo = channelNo;
		CanDim = canDim;
		LightTypeID = lightTypeID;
		SequenceNo = sequenceNo;
	}

	// public int getOldValue() {
	// return oldValue;
	// }
	// public void setOldValue(int oldValue) {
	// this.oldValue = oldValue;
	// }
	//
	
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	public int getNewValue() {
		return newValue;
	}

	public void setNewValue(int newValue) {
		this.newValue = newValue;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LightInZone() {
		super();
	}

	public int getZoneID() {
		return ZoneID;
	}

	public void setZoneID(int zoneID) {
		ZoneID = zoneID;
	}

	public int getLightID() {
		return LightID;
	}

	public void setLightID(int lightID) {
		LightID = lightID;
	}

	public String getLightRemark() {
		return LightRemark;
	}

	public void setLightRemark(String lightRemark) {
		LightRemark = lightRemark;
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

	public int getChannelNo() {
		return ChannelNo;
	}

	public void setChannelNo(int channelNo) {
		ChannelNo = channelNo;
	}

	public int getCanDim() {
		return CanDim;
	}

	public void setCanDim(int canDim) {
		CanDim = canDim;
	}

	public int getLightTypeID() {
		return LightTypeID;
	}

	public void setLightTypeID(int lightTypeID) {
		LightTypeID = lightTypeID;
	}

	public int getSequenceNo() {
		return SequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		SequenceNo = sequenceNo;
	}

	@Override
	public int compareTo(LightInZone another) {
		// TODO Auto-generated method stub
		if (SubnetID == another.getSubnetID()
				&& DeviceID == another.getDeviceID()){
			return 0;
		}else {
			return -1;
		}
	}

}

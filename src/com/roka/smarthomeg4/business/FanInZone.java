package com.roka.smarthomeg4.business;

public class FanInZone  implements Comparable<FanInZone>{

	private int ZoneID;
	private int FanID;
	private String FanName;
	private int SubnetID;
	private int DeviceID;
	private int ChannelNO;
	private int FanTypeID;
	private int SequenceNO;
	private String Remark;
	private int Reserved1;
	private int Reserved2;
	private int Reserved3;
	private int Reserved4;
	private int Reserved5;
	
	
	private int gearValue;
	private int status;
	
	public FanInZone() {
		super();
	}
	
	
	
	

	public FanInZone(int zoneID, int fanID, String fanName, int subnetID,
			int deviceID, int channelNO, int fanTypeID, int sequenceNO,
			String remark, int reserved1, int reserved2, int reserved3,
			int reserved4, int reserved5, int gearValue, int status) {
		super();
		ZoneID = zoneID;
		FanID = fanID;
		FanName = fanName;
		SubnetID = subnetID;
		DeviceID = deviceID;
		ChannelNO = channelNO;
		FanTypeID = fanTypeID;
		SequenceNO = sequenceNO;
		Remark = remark;
		Reserved1 = reserved1;
		Reserved2 = reserved2;
		Reserved3 = reserved3;
		Reserved4 = reserved4;
		Reserved5 = reserved5;
		this.gearValue = gearValue;
		this.status = status;
	}





	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public int getGearValue() {
		return gearValue;
	}
	
	public void setGearValue(int gearValue) {
		this.gearValue = gearValue;
	}
	
	public int getZoneID() {
		return ZoneID;
	}
	public void setZoneID(int zoneID) {
		ZoneID = zoneID;
	}
	public int getFanID() {
		return FanID;
	}
	public void setFanID(int fanID) {
		FanID = fanID;
	}
	public String getFanName() {
		return FanName;
	}
	public void setFanName(String fanName) {
		FanName = fanName;
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
	public int getChannelNO() {
		return ChannelNO;
	}
	public void setChannelNO(int channelNO) {
		ChannelNO = channelNO;
	}
	public int getFanTypeID() {
		return FanTypeID;
	}
	public void setFanTypeID(int fanTypeID) {
		FanTypeID = fanTypeID;
	}
	public int getSequenceNO() {
		return SequenceNO;
	}
	public void setSequenceNO(int sequenceNO) {
		SequenceNO = sequenceNO;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public int getReserved1() {
		return Reserved1;
	}
	public void setReserved1(int reserved1) {
		Reserved1 = reserved1;
	}
	public int getReserved2() {
		return Reserved2;
	}
	public void setReserved2(int reserved2) {
		Reserved2 = reserved2;
	}
	public int getReserved3() {
		return Reserved3;
	}
	public void setReserved3(int reserved3) {
		Reserved3 = reserved3;
	}
	public int getReserved4() {
		return Reserved4;
	}
	public void setReserved4(int reserved4) {
		Reserved4 = reserved4;
	}
	public int getReserved5() {
		return Reserved5;
	}
	public void setReserved5(int reserved5) {
		Reserved5 = reserved5;
	}
	
	

	@Override
	public int compareTo(FanInZone another) {
		// TODO Auto-generated method stub
		if (SubnetID == another.getSubnetID()
				&& DeviceID == another.getDeviceID()){
			return 0;
		}else {
			return -1;
		}
	}

}

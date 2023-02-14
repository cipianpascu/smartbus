package com.roka.smarthomeg4.business;

public class ShadesInZone {

	
	private int ZoneID;
	private int ShadeID;
	private String ShadeName;
	private int ShadeIconID;
	private int SequenceNo;
	private int HasStop;
	private int HasRotate;

public ShadesInZone() {
	// TODO Auto-generated constructor stub
}

public ShadesInZone(int zoneID, int shadeID, String shadeName, int shadeIconID,
		int sequenceNo, int hasStop, int hasRotate) {
	super();
	ZoneID = zoneID;
	ShadeID = shadeID;
	ShadeName = shadeName;
	ShadeIconID = shadeIconID;
	SequenceNo = sequenceNo;
	HasStop = hasStop;
	HasRotate = hasRotate;
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

public String getShadeName() {
	return ShadeName;
}

public void setShadeName(String shadeName) {
	ShadeName = shadeName;
}

public int getShadeIconID() {
	return ShadeIconID;
}

public void setShadeIconID(int shadeIconID) {
	ShadeIconID = shadeIconID;
}

public int getSequenceNo() {
	return SequenceNo;
}

public void setSequenceNo(int sequenceNo) {
	SequenceNo = sequenceNo;
}

public int getHasStop() {
	return HasStop;
}

public void setHasStop(int hasStop) {
	HasStop = hasStop;
}

public int getHasRotate() {
	return HasRotate;
}

public void setHasRotate(int hasRotate) {
	HasRotate = hasRotate;
}




	
}

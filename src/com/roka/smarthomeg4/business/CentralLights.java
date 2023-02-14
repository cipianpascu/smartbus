package com.roka.smarthomeg4.business;

public class CentralLights {
	private int FloorID;
	private String FloorName;
	private int SequenceNo;

	public CentralLights(int floorID, String floorName, int sequenceNo) {
		super();
		FloorID = floorID;
		FloorName = floorName;
		SequenceNo = sequenceNo;
	}

	public CentralLights() {
		super();
	}

	public int getFloorID() {
		return FloorID;
	}

	public void setFloorID(int floorID) {
		FloorID = floorID;
	}

	public String getFloorName() {
		return FloorName;
	}

	public void setFloorName(String floorName) {
		FloorName = floorName;
	}

	public int getSequenceNo() {
		return SequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		SequenceNo = sequenceNo;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.FloorName;
		
	}
}

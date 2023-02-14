package com.roka.smarthomeg4.business;

public class CentralHVAC {
	private int FloorID;
	private String FloorName;
	private int SequenceNo;
	private boolean BlnHaveHot;

	public CentralHVAC(int floorID, String floorName, int sequenceNo,
			boolean blnHaveHot) {
		super();
		FloorID = floorID;
		FloorName = floorName;
		SequenceNo = sequenceNo;
		BlnHaveHot = blnHaveHot;
	}

	public CentralHVAC() {
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

	public boolean isBlnHaveHot() {
		return BlnHaveHot;
	}

	public void setBlnHaveHot(boolean blnHaveHot) {
		BlnHaveHot = blnHaveHot;
	}

}

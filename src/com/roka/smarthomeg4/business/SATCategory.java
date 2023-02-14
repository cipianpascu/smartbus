package com.roka.smarthomeg4.business;

public class SATCategory {

	private int CategoryID;
	private String CategoryName;
	private int SequenceNo;
	private int ZoneID;
	
	public SATCategory() {
		// TODO Auto-generated constructor stub
	}

	public SATCategory(int categoryID, String categoryName, int sequenceNo,
			int zoneID) {
		super();
		CategoryID = categoryID;
		CategoryName = categoryName;
		SequenceNo = sequenceNo;
		ZoneID = zoneID;
	}

	public int getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public int getSequenceNo() {
		return SequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		SequenceNo = sequenceNo;
	}

	public int getZoneID() {
		return ZoneID;
	}

	public void setZoneID(int zoneID) {
		ZoneID = zoneID;
	}
	
	
	


}

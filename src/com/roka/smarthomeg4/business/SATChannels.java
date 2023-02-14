package com.roka.smarthomeg4.business;

public class SATChannels {
	private int CategoryID;
	private int ChannelID;
	private int ChannelNo;
	private String ChannelName;
	private int LogoID;
	private int SequenceNo;
	private int ZoneID;

	
	public SATChannels() {
		// TODO Auto-generated constructor stub
	}


	public SATChannels(int categoryID, int channelID, int channelNo,
			String channelName, int logoID, int sequenceNo, int zoneID) {
		super();
		CategoryID = categoryID;
		ChannelID = channelID;
		ChannelNo = channelNo;
		ChannelName = channelName;
		LogoID = logoID;
		SequenceNo = sequenceNo;
		ZoneID = zoneID;
	}


	public int getCategoryID() {
		return CategoryID;
	}


	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}


	public int getChannelID() {
		return ChannelID;
	}


	public void setChannelID(int channelID) {
		ChannelID = channelID;
	}


	public int getChannelNo() {
		return ChannelNo;
	}


	public void setChannelNo(int channelNo) {
		ChannelNo = channelNo;
	}


	public String getChannelName() {
		return ChannelName;
	}


	public void setChannelName(String channelName) {
		ChannelName = channelName;
	}


	public int getLogoID() {
		return LogoID;
	}


	public void setLogoID(int logoID) {
		LogoID = logoID;
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

package com.roka.smarthomeg4.business;

public class ZoneIconDefine {

	private int ZoneIconID;
	private String IconName;
	
	public ZoneIconDefine() {
		// TODO Auto-generated constructor stub
	}

	public ZoneIconDefine(int zoneIconID, String iconName) {
		super();
		ZoneIconID = zoneIconID;
		IconName = iconName;
	}

	public int getZoneIconID() {
		return ZoneIconID;
	}

	public void setZoneIconID(int zoneIconID) {
		ZoneIconID = zoneIconID;
	}

	public String getIconName() {
		return IconName;
	}

	public void setIconName(String iconName) {
		IconName = iconName;
	}
	
	
	
}

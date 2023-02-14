package com.roka.smarthomeg4.business;

public class LightTypeDefinition {

	private int LightTypeID;
	private String Remark;
	private String IconNameforOn;
	private String IconNameforOff;

	public LightTypeDefinition() {
		super();
	}

	public LightTypeDefinition(int lightTypeID, String remark,
			String iconNameforOn, String iconNameforOff) {
		super();
		LightTypeID = lightTypeID;
		Remark = remark;
		IconNameforOn = iconNameforOn;
		IconNameforOff = iconNameforOff;
	}

	public int getLightTypeID() {
		return LightTypeID;
	}

	public void setLightTypeID(int lightTypeID) {
		LightTypeID = lightTypeID;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getIconNameforOn() {
		return IconNameforOn;
	}

	public void setIconNameforOn(String iconNameforOn) {
		IconNameforOn = iconNameforOn;
	}

	public String getIconNameforOff() {
		return IconNameforOff;
	}

	public void setIconNameforOff(String iconNameforOff) {
		IconNameforOff = iconNameforOff;
	}

}

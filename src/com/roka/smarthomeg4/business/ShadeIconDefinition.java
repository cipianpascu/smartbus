package com.roka.smarthomeg4.business;

public class ShadeIconDefinition {
	 private int ShadeIconID ;
	 private String  Remark ;
	  private String  IconName ;
	  
	  public ShadeIconDefinition() {
		// TODO Auto-generated constructor stub
	}

	public ShadeIconDefinition(int shadeIconID, String remark, String iconName) {
		super();
		ShadeIconID = shadeIconID;
		Remark = remark;
		IconName = iconName;
	}

	public int getShadeIconID() {
		return ShadeIconID;
	}

	public void setShadeIconID(int shadeIconID) {
		ShadeIconID = shadeIconID;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getIconName() {
		return IconName;
	}

	public void setIconName(String iconName) {
		IconName = iconName;
	}
	  
	  
	
	

}

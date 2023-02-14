package com.roka.smarthomeg4.business;

public class HVACSetUp {
	private boolean IsCelsiur;

	private int TempertureOfCold;
	private int TempertureOfCool;
	private int TempertureOfWarm;
	private int TempertureOfHot;

	public HVACSetUp() {
		super();
	}

	public HVACSetUp(boolean isCelsiur, int tempertureOfCold,
			int tempertureOfCool, int tempertureOfWarm, int tempertureOfHot) {
		super();
		IsCelsiur = isCelsiur;
		TempertureOfCold = tempertureOfCold;
		TempertureOfCool = tempertureOfCool;
		TempertureOfWarm = tempertureOfWarm;
		TempertureOfHot = tempertureOfHot;
	}

	public boolean isIsCelsiur() {
		return IsCelsiur;
	}

	public void setIsCelsiur(boolean isCelsiur) {
		IsCelsiur = isCelsiur;
	}

	public int getTempertureOfCold() {
		return TempertureOfCold;
	}

	public void setTempertureOfCold(int tempertureOfCold) {
		TempertureOfCold = tempertureOfCold;
	}

	public int getTempertureOfCool() {
		return TempertureOfCool;
	}

	public void setTempertureOfCool(int tempertureOfCool) {
		TempertureOfCool = tempertureOfCool;
	}

	public int getTempertureOfWarm() {
		return TempertureOfWarm;
	}

	public void setTempertureOfWarm(int tempertureOfWarm) {
		TempertureOfWarm = tempertureOfWarm;
	}

	public int getTempertureOfHot() {
		return TempertureOfHot;
	}

	public void setTempertureOfHot(int tempertureOfHot) {
		TempertureOfHot = tempertureOfHot;
	}

}

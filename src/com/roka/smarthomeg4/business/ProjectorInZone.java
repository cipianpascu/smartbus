package com.roka.smarthomeg4.business;

import java.io.Serializable;

public class ProjectorInZone implements Serializable{

	private int ZoneID;
	private int SubnetID;
	private int DeviceID;
	private int UniversalSwitchIDforOn;
	private int UniversalSwitchStatusforOn;
	private int UniversalSwitchIDforOff;
	private int UniversalSwitchStatusforOff;
	private int UniversalSwitchIDfoUp;
	private int UniversalSwitchIDforDown;
	private int UniversalSwitchIDforLeft;
	private int UniversalSwitchIDforRight;
	private int UniversalSwitchIDforOK;
	private int UniversalSwitchIDfoMenu;
	private int UniversalSwitchIDforSource;
	private int IRMacroNumbForProjectorStart;
	private int IRMacroNumbForProjectorSpare1;
	private int IRMacroNumbForProjectorSpare2;
	private int IRMacroNumbForProjectorSpare3;
	private int IRMacroNumbForProjectorSpare4;
	private int IRMacroNumbForProjectorSpare5;
	
	
	public ProjectorInZone() {
		// TODO Auto-generated constructor stub
	}


	public ProjectorInZone(int zoneID, int subnetID, int deviceID,
			int universalSwitchIDforOn, int universalSwitchStatusforOn,
			int universalSwitchIDforOff, int universalSwitchStatusforOff,
			int universalSwitchIDfoUp, int universalSwitchIDforDown,
			int universalSwitchIDforLeft, int universalSwitchIDforRight,
			int universalSwitchIDforOK, int universalSwitchIDfoMenu,
			int universalSwitchIDforSource, int iRMacroNumbForProjectorStart,
			int iRMacroNumbForProjectorSpare1,
			int iRMacroNumbForProjectorSpare2,
			int iRMacroNumbForProjectorSpare3,
			int iRMacroNumbForProjectorSpare4, int iRMacroNumbForProjectorSpare5) {
		super();
		ZoneID = zoneID;
		SubnetID = subnetID;
		DeviceID = deviceID;
		UniversalSwitchIDforOn = universalSwitchIDforOn;
		UniversalSwitchStatusforOn = universalSwitchStatusforOn;
		UniversalSwitchIDforOff = universalSwitchIDforOff;
		UniversalSwitchStatusforOff = universalSwitchStatusforOff;
		UniversalSwitchIDfoUp = universalSwitchIDfoUp;
		UniversalSwitchIDforDown = universalSwitchIDforDown;
		UniversalSwitchIDforLeft = universalSwitchIDforLeft;
		UniversalSwitchIDforRight = universalSwitchIDforRight;
		UniversalSwitchIDforOK = universalSwitchIDforOK;
		UniversalSwitchIDfoMenu = universalSwitchIDfoMenu;
		UniversalSwitchIDforSource = universalSwitchIDforSource;
		IRMacroNumbForProjectorStart = iRMacroNumbForProjectorStart;
		IRMacroNumbForProjectorSpare1 = iRMacroNumbForProjectorSpare1;
		IRMacroNumbForProjectorSpare2 = iRMacroNumbForProjectorSpare2;
		IRMacroNumbForProjectorSpare3 = iRMacroNumbForProjectorSpare3;
		IRMacroNumbForProjectorSpare4 = iRMacroNumbForProjectorSpare4;
		IRMacroNumbForProjectorSpare5 = iRMacroNumbForProjectorSpare5;
	}


	public int getZoneID() {
		return ZoneID;
	}


	public void setZoneID(int zoneID) {
		ZoneID = zoneID;
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


	public int getUniversalSwitchIDforOn() {
		return UniversalSwitchIDforOn;
	}


	public void setUniversalSwitchIDforOn(int universalSwitchIDforOn) {
		UniversalSwitchIDforOn = universalSwitchIDforOn;
	}


	public int getUniversalSwitchStatusforOn() {
		return UniversalSwitchStatusforOn;
	}


	public void setUniversalSwitchStatusforOn(int universalSwitchStatusforOn) {
		UniversalSwitchStatusforOn = universalSwitchStatusforOn;
	}


	public int getUniversalSwitchIDforOff() {
		return UniversalSwitchIDforOff;
	}


	public void setUniversalSwitchIDforOff(int universalSwitchIDforOff) {
		UniversalSwitchIDforOff = universalSwitchIDforOff;
	}


	public int getUniversalSwitchStatusforOff() {
		return UniversalSwitchStatusforOff;
	}


	public void setUniversalSwitchStatusforOff(int universalSwitchStatusforOff) {
		UniversalSwitchStatusforOff = universalSwitchStatusforOff;
	}


	public int getUniversalSwitchIDfoUp() {
		return UniversalSwitchIDfoUp;
	}


	public void setUniversalSwitchIDfoUp(int universalSwitchIDfoUp) {
		UniversalSwitchIDfoUp = universalSwitchIDfoUp;
	}


	public int getUniversalSwitchIDforDown() {
		return UniversalSwitchIDforDown;
	}


	public void setUniversalSwitchIDforDown(int universalSwitchIDforDown) {
		UniversalSwitchIDforDown = universalSwitchIDforDown;
	}


	public int getUniversalSwitchIDforLeft() {
		return UniversalSwitchIDforLeft;
	}


	public void setUniversalSwitchIDforLeft(int universalSwitchIDforLeft) {
		UniversalSwitchIDforLeft = universalSwitchIDforLeft;
	}


	public int getUniversalSwitchIDforRight() {
		return UniversalSwitchIDforRight;
	}


	public void setUniversalSwitchIDforRight(int universalSwitchIDforRight) {
		UniversalSwitchIDforRight = universalSwitchIDforRight;
	}


	public int getUniversalSwitchIDforOK() {
		return UniversalSwitchIDforOK;
	}


	public void setUniversalSwitchIDforOK(int universalSwitchIDforOK) {
		UniversalSwitchIDforOK = universalSwitchIDforOK;
	}


	public int getUniversalSwitchIDfoMenu() {
		return UniversalSwitchIDfoMenu;
	}


	public void setUniversalSwitchIDfoMenu(int universalSwitchIDfoMenu) {
		UniversalSwitchIDfoMenu = universalSwitchIDfoMenu;
	}


	public int getUniversalSwitchIDforSource() {
		return UniversalSwitchIDforSource;
	}


	public void setUniversalSwitchIDforSource(int universalSwitchIDforSource) {
		UniversalSwitchIDforSource = universalSwitchIDforSource;
	}


	public int getIRMacroNumbForProjectorStart() {
		return IRMacroNumbForProjectorStart;
	}


	public void setIRMacroNumbForProjectorStart(int iRMacroNumbForProjectorStart) {
		IRMacroNumbForProjectorStart = iRMacroNumbForProjectorStart;
	}


	public int getIRMacroNumbForProjectorSpare1() {
		return IRMacroNumbForProjectorSpare1;
	}


	public void setIRMacroNumbForProjectorSpare1(int iRMacroNumbForProjectorSpare1) {
		IRMacroNumbForProjectorSpare1 = iRMacroNumbForProjectorSpare1;
	}


	public int getIRMacroNumbForProjectorSpare2() {
		return IRMacroNumbForProjectorSpare2;
	}


	public void setIRMacroNumbForProjectorSpare2(int iRMacroNumbForProjectorSpare2) {
		IRMacroNumbForProjectorSpare2 = iRMacroNumbForProjectorSpare2;
	}


	public int getIRMacroNumbForProjectorSpare3() {
		return IRMacroNumbForProjectorSpare3;
	}


	public void setIRMacroNumbForProjectorSpare3(int iRMacroNumbForProjectorSpare3) {
		IRMacroNumbForProjectorSpare3 = iRMacroNumbForProjectorSpare3;
	}


	public int getIRMacroNumbForProjectorSpare4() {
		return IRMacroNumbForProjectorSpare4;
	}


	public void setIRMacroNumbForProjectorSpare4(int iRMacroNumbForProjectorSpare4) {
		IRMacroNumbForProjectorSpare4 = iRMacroNumbForProjectorSpare4;
	}


	public int getIRMacroNumbForProjectorSpare5() {
		return IRMacroNumbForProjectorSpare5;
	}


	public void setIRMacroNumbForProjectorSpare5(int iRMacroNumbForProjectorSpare5) {
		IRMacroNumbForProjectorSpare5 = iRMacroNumbForProjectorSpare5;
	}
	
	
	
	

}

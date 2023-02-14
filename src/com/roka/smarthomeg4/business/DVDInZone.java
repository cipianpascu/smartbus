package com.roka.smarthomeg4.business;

import java.io.Serializable;

public class DVDInZone  implements Serializable{
	private int ZoneID;
	private int SubnetID;
	private int DeviceID;
	private int UniversalSwitchIDforOn;
	private int UniversalSwitchStatusforOn;
	private int UniversalSwitchIDforOff;
	private int UniversalSwitchStatusforOff;
	private int UniversalSwitchIDfoMenu;
	private int UniversalSwitchIDfoUp;
	private int UniversalSwitchIDforDown;
	private int UniversalSwitchIDforFastForward;
	private int UniversalSwitchIDforBackForward;
	private int UniversalSwitchIDforOK;
	private int UniversalSwitchIDforPREVChapter;
	private int UniversalSwitchIDforNextChapter;
	private int UniversalSwitchIDforPlayPause;
	private int UniversalSwithIDForRecord;
	private int UniversalSwithIDForStopRecord;
	private int IRMacroNumbForDVDStart;
	private int IRMacroNumbForDVDSpare1;
	private int IRMacroNumbForDVDSpare2;
	private int IRMacroNumbForDVDSpare3;
	private int IRMacroNumbForDVDSpare4;
	private int IRMacroNumbForDVDSpare5;
	public DVDInZone() {
		super();
	}
	public DVDInZone(int zoneID, int subnetID, int deviceID,
			int universalSwitchIDforOn, int universalSwitchStatusforOn,
			int universalSwitchIDforOff, int universalSwitchStatusforOff,
			int universalSwitchIDfoMenu, int universalSwitchIDfoUp,
			int universalSwitchIDforDown, int universalSwitchIDforFastForward,
			int universalSwitchIDforBackForward, int universalSwitchIDforOK,
			int universalSwitchIDforPREVChapter,
			int universalSwitchIDforNextChapter,
			int universalSwitchIDforPlayPause, int universalSwithIDForRecord,
			int universalSwithIDForStopRecord, int iRMacroNumbForDVDStart,
			int iRMacroNumbForDVDSpare1, int iRMacroNumbForDVDSpare2,
			int iRMacroNumbForDVDSpare3, int iRMacroNumbForDVDSpare4,
			int iRMacroNumbForDVDSpare5) {
		super();
		ZoneID = zoneID;
		SubnetID = subnetID;
		DeviceID = deviceID;
		UniversalSwitchIDforOn = universalSwitchIDforOn;
		UniversalSwitchStatusforOn = universalSwitchStatusforOn;
		UniversalSwitchIDforOff = universalSwitchIDforOff;
		UniversalSwitchStatusforOff = universalSwitchStatusforOff;
		UniversalSwitchIDfoMenu = universalSwitchIDfoMenu;
		UniversalSwitchIDfoUp = universalSwitchIDfoUp;
		UniversalSwitchIDforDown = universalSwitchIDforDown;
		UniversalSwitchIDforFastForward = universalSwitchIDforFastForward;
		UniversalSwitchIDforBackForward = universalSwitchIDforBackForward;
		UniversalSwitchIDforOK = universalSwitchIDforOK;
		UniversalSwitchIDforPREVChapter = universalSwitchIDforPREVChapter;
		UniversalSwitchIDforNextChapter = universalSwitchIDforNextChapter;
		UniversalSwitchIDforPlayPause = universalSwitchIDforPlayPause;
		UniversalSwithIDForRecord = universalSwithIDForRecord;
		UniversalSwithIDForStopRecord = universalSwithIDForStopRecord;
		IRMacroNumbForDVDStart = iRMacroNumbForDVDStart;
		IRMacroNumbForDVDSpare1 = iRMacroNumbForDVDSpare1;
		IRMacroNumbForDVDSpare2 = iRMacroNumbForDVDSpare2;
		IRMacroNumbForDVDSpare3 = iRMacroNumbForDVDSpare3;
		IRMacroNumbForDVDSpare4 = iRMacroNumbForDVDSpare4;
		IRMacroNumbForDVDSpare5 = iRMacroNumbForDVDSpare5;
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
	public int getUniversalSwitchIDfoMenu() {
		return UniversalSwitchIDfoMenu;
	}
	public void setUniversalSwitchIDfoMenu(int universalSwitchIDfoMenu) {
		UniversalSwitchIDfoMenu = universalSwitchIDfoMenu;
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
	public int getUniversalSwitchIDforFastForward() {
		return UniversalSwitchIDforFastForward;
	}
	public void setUniversalSwitchIDforFastForward(
			int universalSwitchIDforFastForward) {
		UniversalSwitchIDforFastForward = universalSwitchIDforFastForward;
	}
	public int getUniversalSwitchIDforBackForward() {
		return UniversalSwitchIDforBackForward;
	}
	public void setUniversalSwitchIDforBackForward(
			int universalSwitchIDforBackForward) {
		UniversalSwitchIDforBackForward = universalSwitchIDforBackForward;
	}
	public int getUniversalSwitchIDforOK() {
		return UniversalSwitchIDforOK;
	}
	public void setUniversalSwitchIDforOK(int universalSwitchIDforOK) {
		UniversalSwitchIDforOK = universalSwitchIDforOK;
	}
	public int getUniversalSwitchIDforPREVChapter() {
		return UniversalSwitchIDforPREVChapter;
	}
	public void setUniversalSwitchIDforPREVChapter(
			int universalSwitchIDforPREVChapter) {
		UniversalSwitchIDforPREVChapter = universalSwitchIDforPREVChapter;
	}
	public int getUniversalSwitchIDforNextChapter() {
		return UniversalSwitchIDforNextChapter;
	}
	public void setUniversalSwitchIDforNextChapter(
			int universalSwitchIDforNextChapter) {
		UniversalSwitchIDforNextChapter = universalSwitchIDforNextChapter;
	}
	public int getUniversalSwitchIDforPlayPause() {
		return UniversalSwitchIDforPlayPause;
	}
	public void setUniversalSwitchIDforPlayPause(int universalSwitchIDforPlayPause) {
		UniversalSwitchIDforPlayPause = universalSwitchIDforPlayPause;
	}
	public int getUniversalSwithIDForRecord() {
		return UniversalSwithIDForRecord;
	}
	public void setUniversalSwithIDForRecord(int universalSwithIDForRecord) {
		UniversalSwithIDForRecord = universalSwithIDForRecord;
	}
	public int getUniversalSwithIDForStopRecord() {
		return UniversalSwithIDForStopRecord;
	}
	public void setUniversalSwithIDForStopRecord(int universalSwithIDForStopRecord) {
		UniversalSwithIDForStopRecord = universalSwithIDForStopRecord;
	}
	public int getIRMacroNumbForDVDStart() {
		return IRMacroNumbForDVDStart;
	}
	public void setIRMacroNumbForDVDStart(int iRMacroNumbForDVDStart) {
		IRMacroNumbForDVDStart = iRMacroNumbForDVDStart;
	}
	public int getIRMacroNumbForDVDSpare1() {
		return IRMacroNumbForDVDSpare1;
	}
	public void setIRMacroNumbForDVDSpare1(int iRMacroNumbForDVDSpare1) {
		IRMacroNumbForDVDSpare1 = iRMacroNumbForDVDSpare1;
	}
	public int getIRMacroNumbForDVDSpare2() {
		return IRMacroNumbForDVDSpare2;
	}
	public void setIRMacroNumbForDVDSpare2(int iRMacroNumbForDVDSpare2) {
		IRMacroNumbForDVDSpare2 = iRMacroNumbForDVDSpare2;
	}
	public int getIRMacroNumbForDVDSpare3() {
		return IRMacroNumbForDVDSpare3;
	}
	public void setIRMacroNumbForDVDSpare3(int iRMacroNumbForDVDSpare3) {
		IRMacroNumbForDVDSpare3 = iRMacroNumbForDVDSpare3;
	}
	public int getIRMacroNumbForDVDSpare4() {
		return IRMacroNumbForDVDSpare4;
	}
	public void setIRMacroNumbForDVDSpare4(int iRMacroNumbForDVDSpare4) {
		IRMacroNumbForDVDSpare4 = iRMacroNumbForDVDSpare4;
	}
	public int getIRMacroNumbForDVDSpare5() {
		return IRMacroNumbForDVDSpare5;
	}
	public void setIRMacroNumbForDVDSpare5(int iRMacroNumbForDVDSpare5) {
		IRMacroNumbForDVDSpare5 = iRMacroNumbForDVDSpare5;
	}

	
	
}

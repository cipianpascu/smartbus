package com.roka.smarthomeg4.business;

public class ScheduleCommands {
	private int ScheduleID ;
	private int CommandID ;
	private int fstPara;
	private int sndPara;
	private int thirdPara;
	private int fourthPara;
	private int fivthPara;
	private int sixthPara;

	
	public ScheduleCommands() {
		// TODO Auto-generated constructor stub
	}


	public ScheduleCommands(int scheduleID, int commandID, int fstPara,
			int sndPara, int thirdPara, int fourthPara, int fivthPara,
			int sixthPara) {
		super();
		ScheduleID = scheduleID;
		CommandID = commandID;
		this.fstPara = fstPara;
		this.sndPara = sndPara;
		this.thirdPara = thirdPara;
		this.fourthPara = fourthPara;
		this.fivthPara = fivthPara;
		this.sixthPara = sixthPara;
	}


	public int getScheduleID() {
		return ScheduleID;
	}


	public void setScheduleID(int scheduleID) {
		ScheduleID = scheduleID;
	}


	public int getCommandID() {
		return CommandID;
	}


	public void setCommandID(int commandID) {
		CommandID = commandID;
	}


	public int getFstPara() {
		return fstPara;
	}


	public void setFstPara(int fstPara) {
		this.fstPara = fstPara;
	}


	public int getSndPara() {
		return sndPara;
	}


	public void setSndPara(int sndPara) {
		this.sndPara = sndPara;
	}


	public int getThirdPara() {
		return thirdPara;
	}


	public void setThirdPara(int thirdPara) {
		this.thirdPara = thirdPara;
	}


	public int getFourthPara() {
		return fourthPara;
	}


	public void setFourthPara(int fourthPara) {
		this.fourthPara = fourthPara;
	}


	public int getFivthPara() {
		return fivthPara;
	}


	public void setFivthPara(int fivthPara) {
		this.fivthPara = fivthPara;
	}


	public int getSixthPara() {
		return sixthPara;
	}


	public void setSixthPara(int sixthPara) {
		this.sixthPara = sixthPara;
	}
	
	
	
	

}

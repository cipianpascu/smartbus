package com.roka.smarthomeg4.business;

public class Schedules {
	private int ScheduleID ;
	private String ScheduleName ;
	private boolean EnabledSchedule ;
	private int ControlledItemID ;
	private int ZoneID ;
	private int FrequencyID ;
	private boolean WithSunday ;
	private boolean WithMonday ;
	private boolean WithTuesday ;
	private boolean WithWednesday ;
	private boolean WithThursday ;
	private boolean WithFriday ;
	private boolean WithSaturday ;
	private int ExecutionHours ;
	private int ExecutionMins ;
	private String ExecutionDate;
	private boolean HaveSound;

	
	public Schedules() {
		// TODO Auto-generated constructor stub
	}


	public Schedules(int scheduleID, String scheduleName,
			boolean enabledSchedule, int controlledItemID, int zoneID,
			int frequencyID, boolean withSunday, boolean withMonday,
			boolean withTuesday, boolean withWednesday, boolean withThursday,
			boolean withFriday, boolean withSaturday, int executionHours,
			int executionMins, String executionDate, boolean haveSound) {
		super();
		ScheduleID = scheduleID;
		ScheduleName = scheduleName;
		EnabledSchedule = enabledSchedule;
		ControlledItemID = controlledItemID;
		ZoneID = zoneID;
		FrequencyID = frequencyID;
		WithSunday = withSunday;
		WithMonday = withMonday;
		WithTuesday = withTuesday;
		WithWednesday = withWednesday;
		WithThursday = withThursday;
		WithFriday = withFriday;
		WithSaturday = withSaturday;
		ExecutionHours = executionHours;
		ExecutionMins = executionMins;
		ExecutionDate = executionDate;
		HaveSound = haveSound;
	}


	public int getScheduleID() {
		return ScheduleID;
	}


	public void setScheduleID(int scheduleID) {
		ScheduleID = scheduleID;
	}


	public String getScheduleName() {
		return ScheduleName;
	}


	public void setScheduleName(String scheduleName) {
		ScheduleName = scheduleName;
	}


	public boolean isEnabledSchedule() {
		return EnabledSchedule;
	}


	public void setEnabledSchedule(boolean enabledSchedule) {
		EnabledSchedule = enabledSchedule;
	}


	public int getControlledItemID() {
		return ControlledItemID;
	}


	public void setControlledItemID(int controlledItemID) {
		ControlledItemID = controlledItemID;
	}


	public int getZoneID() {
		return ZoneID;
	}


	public void setZoneID(int zoneID) {
		ZoneID = zoneID;
	}


	public int getFrequencyID() {
		return FrequencyID;
	}


	public void setFrequencyID(int frequencyID) {
		FrequencyID = frequencyID;
	}


	public boolean isWithSunday() {
		return WithSunday;
	}


	public void setWithSunday(boolean withSunday) {
		WithSunday = withSunday;
	}


	public boolean isWithMonday() {
		return WithMonday;
	}


	public void setWithMonday(boolean withMonday) {
		WithMonday = withMonday;
	}


	public boolean isWithTuesday() {
		return WithTuesday;
	}


	public void setWithTuesday(boolean withTuesday) {
		WithTuesday = withTuesday;
	}


	public boolean isWithWednesday() {
		return WithWednesday;
	}


	public void setWithWednesday(boolean withWednesday) {
		WithWednesday = withWednesday;
	}


	public boolean isWithThursday() {
		return WithThursday;
	}


	public void setWithThursday(boolean withThursday) {
		WithThursday = withThursday;
	}


	public boolean isWithFriday() {
		return WithFriday;
	}


	public void setWithFriday(boolean withFriday) {
		WithFriday = withFriday;
	}


	public boolean isWithSaturday() {
		return WithSaturday;
	}


	public void setWithSaturday(boolean withSaturday) {
		WithSaturday = withSaturday;
	}


	public int getExecutionHours() {
		return ExecutionHours;
	}


	public void setExecutionHours(int executionHours) {
		ExecutionHours = executionHours;
	}


	public int getExecutionMins() {
		return ExecutionMins;
	}


	public void setExecutionMins(int executionMins) {
		ExecutionMins = executionMins;
	}


	public String getExecutionDate() {
		return ExecutionDate;
	}


	public void setExecutionDate(String executionDate) {
		ExecutionDate = executionDate;
	}


	public boolean isHaveSound() {
		return HaveSound;
	}


	public void setHaveSound(boolean haveSound) {
		HaveSound = haveSound;
	}
	
	
	
	

}

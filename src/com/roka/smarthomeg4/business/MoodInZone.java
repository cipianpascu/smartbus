package com.roka.smarthomeg4.business;

public class MoodInZone {
	private int MoodID ;
	private int   ZoneID;
	private String  MoodName ;
	  private int MoodIconID ;
	  private int SequenceNO;
	  private int IsSystemMood ;
	  
	  public MoodInZone() {
		// TODO Auto-generated constructor stub
	}

	public MoodInZone(int moodID, int zoneID, String moodName, int moodIconID,
			int sequenceNO, int isSystemMood) {
		super();
		MoodID = moodID;
		ZoneID = zoneID;
		MoodName = moodName;
		MoodIconID = moodIconID;
		SequenceNO = sequenceNO;
		IsSystemMood = isSystemMood;
	}

	public int getMoodID() {
		return MoodID;
	}

	public void setMoodID(int moodID) {
		MoodID = moodID;
	}

	public int getZoneID() {
		return ZoneID;
	}

	public void setZoneID(int zoneID) {
		ZoneID = zoneID;
	}

	public String getMoodName() {
		return MoodName;
	}

	public void setMoodName(String moodName) {
		MoodName = moodName;
	}

	public int getMoodIconID() {
		return MoodIconID;
	}

	public void setMoodIconID(int moodIconID) {
		MoodIconID = moodIconID;
	}

	public int getSequenceNO() {
		return SequenceNO;
	}

	public void setSequenceNO(int sequenceNO) {
		SequenceNO = sequenceNO;
	}

	public int getIsSystemMood() {
		return IsSystemMood;
	}

	public void setIsSystemMood(int isSystemMood) {
		IsSystemMood = isSystemMood;
	}
	  
	
	


}

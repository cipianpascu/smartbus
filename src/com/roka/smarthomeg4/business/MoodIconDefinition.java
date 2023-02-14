package com.roka.smarthomeg4.business;

public class MoodIconDefinition {
	 private int MoodIconID; 
	 private String  IconName;
	 public MoodIconDefinition() {
		// TODO Auto-generated constructor stub
	}
	public MoodIconDefinition(int moodIconID, String iconName) {
		super();
		MoodIconID = moodIconID;
		IconName = iconName;
	}
	public int getMoodIconID() {
		return MoodIconID;
	}
	public void setMoodIconID(int moodIconID) {
		MoodIconID = moodIconID;
	}
	public String getIconName() {
		return IconName;
	}
	public void setIconName(String iconName) {
		IconName = iconName;
	}
	 
	

}

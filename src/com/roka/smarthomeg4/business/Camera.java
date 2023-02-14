package com.roka.smarthomeg4.business;

public class Camera {

	private int CameraID;
	private String Name;
	private String URL;
	private int SequenceNo;
	public Camera(int cameraID, String name, String uRL, int sequenceNo) {
		super();
		CameraID = cameraID;
		Name = name;
		URL = uRL;
		SequenceNo = sequenceNo;
	}
	public Camera() {
		super();
	}
	public int getCameraID() {
		return CameraID;
	}
	public void setCameraID(int cameraID) {
		CameraID = cameraID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public int getSequenceNo() {
		return SequenceNo;
	}
	public void setSequenceNo(int sequenceNo) {
		SequenceNo = sequenceNo;
	}
	
	
	
}

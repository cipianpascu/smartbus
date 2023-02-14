package com.roka.smarthomeg4.business;

public class IpCamera {
	private int CameraID ;
	private String Brand;
	private int Type;
	private String Ip ;
	private int Port ;
	private String Mask ;
	private String Gateway ;
	private String Dns ;
	private int IsP2P ;
	private int MediaPort ;
	private int SysVersions ;
	private int AppVersions ;
	private int DhcpEnabled ;
	private String CameraName ;
	private int UIDForP2P ;
	private int IsMainStream ;
	public IpCamera() {
		super();
	}
	public IpCamera(int cameraID, String brand, int type, String ip, int port,
			String mask, String gateway, String dns, int isP2P, int mediaPort,
			int sysVersions, int appVersions, int dhcpEnabled,
			String cameraName, int uIDForP2P, int isMainStream) {
		super();
		CameraID = cameraID;
		Brand = brand;
		Type = type;
		Ip = ip;
		Port = port;
		Mask = mask;
		Gateway = gateway;
		Dns = dns;
		IsP2P = isP2P;
		MediaPort = mediaPort;
		SysVersions = sysVersions;
		AppVersions = appVersions;
		DhcpEnabled = dhcpEnabled;
		CameraName = cameraName;
		UIDForP2P = uIDForP2P;
		IsMainStream = isMainStream;
	}
	public int getCameraID() {
		return CameraID;
	}
	public void setCameraID(int cameraID) {
		CameraID = cameraID;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getIp() {
		return Ip;
	}
	public void setIp(String ip) {
		Ip = ip;
	}
	public int getPort() {
		return Port;
	}
	public void setPort(int port) {
		Port = port;
	}
	public String getMask() {
		return Mask;
	}
	public void setMask(String mask) {
		Mask = mask;
	}
	public String getGateway() {
		return Gateway;
	}
	public void setGateway(String gateway) {
		Gateway = gateway;
	}
	public String getDns() {
		return Dns;
	}
	public void setDns(String dns) {
		Dns = dns;
	}
	public int getIsP2P() {
		return IsP2P;
	}
	public void setIsP2P(int isP2P) {
		IsP2P = isP2P;
	}
	public int getMediaPort() {
		return MediaPort;
	}
	public void setMediaPort(int mediaPort) {
		MediaPort = mediaPort;
	}
	public int getSysVersions() {
		return SysVersions;
	}
	public void setSysVersions(int sysVersions) {
		SysVersions = sysVersions;
	}
	public int getAppVersions() {
		return AppVersions;
	}
	public void setAppVersions(int appVersions) {
		AppVersions = appVersions;
	}
	public int getDhcpEnabled() {
		return DhcpEnabled;
	}
	public void setDhcpEnabled(int dhcpEnabled) {
		DhcpEnabled = dhcpEnabled;
	}
	public String getCameraName() {
		return CameraName;
	}
	public void setCameraName(String cameraName) {
		CameraName = cameraName;
	}
	public int getUIDForP2P() {
		return UIDForP2P;
	}
	public void setUIDForP2P(int uIDForP2P) {
		UIDForP2P = uIDForP2P;
	}
	public int getIsMainStream() {
		return IsMainStream;
	}
	public void setIsMainStream(int isMainStream) {
		IsMainStream = isMainStream;
	}
	
	


}

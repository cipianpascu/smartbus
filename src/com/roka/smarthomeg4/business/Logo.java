package com.roka.smarthomeg4.business;

import android.graphics.Bitmap;

public class Logo {

	private int LogoTypeID ;
	private int LogoID ;
	private String LogoName ;
	private Bitmap blogData;
	
	public Logo() {
		// TODO Auto-generated constructor stub
	}

	public Logo(int logoTypeID, int logoID, String logoName) {
		super();
		LogoTypeID = logoTypeID;
		LogoID = logoID;
		LogoName = logoName;
	}
	
	

	public Logo(int logoTypeID, int logoID, String logoName, Bitmap blogData) {
		super();
		LogoTypeID = logoTypeID;
		LogoID = logoID;
		LogoName = logoName;
		this.blogData = blogData;
	}

	
	
	
	public Bitmap getBlogData() {
		return blogData;
	}

	public void setBlogData(Bitmap blogData) {
		this.blogData = blogData;
	}

	public int getLogoTypeID() {
		return LogoTypeID;
	}

	public void setLogoTypeID(int logoTypeID) {
		LogoTypeID = logoTypeID;
	}

	public int getLogoID() {
		return LogoID;
	}

	public void setLogoID(int logoID) {
		LogoID = logoID;
	}

	public String getLogoName() {
		return LogoName;
	}

	public void setLogoName(String logoName) {
		LogoName = logoName;
	}
	
		
	
}

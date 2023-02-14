package com.roka.smarthomeg4.business;

public class ZAudio_Album {

	private int no;
	private String name;
	private int Big_Package_No;
	private int Big_Package_No_song;
	private int songNoHigh;
	private int songNoLow;

	public ZAudio_Album() {
		// TODO Auto-generated constructor stub
	}

	

	public ZAudio_Album(int no, String name, int big_Package_No,
			int big_Package_No_song, int songNoHigh, int songNoLow) {
		super();
		this.no = no;
		this.name = name;
		Big_Package_No = big_Package_No;
		Big_Package_No_song = big_Package_No_song;
		this.songNoHigh = songNoHigh;
		this.songNoLow = songNoLow;
	}



	public int getSongNoHigh() {
		return songNoHigh;
	}



	public void setSongNoHigh(int songNoHigh) {
		this.songNoHigh = songNoHigh;
	}



	public int getSongNoLow() {
		return songNoLow;
	}



	public void setSongNoLow(int songNoLow) {
		this.songNoLow = songNoLow;
	}



	public int getBig_Package_No_song() {
		return Big_Package_No_song;
	}

	public void setBig_Package_No_song(int big_Package_No_song) {
		Big_Package_No_song = big_Package_No_song;
	}

	public int getBig_Package_No() {
		return Big_Package_No;
	}

	public void setBig_Package_No(int big_Package_No) {
		Big_Package_No = big_Package_No;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}

}

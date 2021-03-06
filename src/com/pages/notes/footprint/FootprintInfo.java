package com.pages.notes.footprint;

public class FootprintInfo {

	String coverPicName;
	//String coverSongPath;
	String coverSongName;
	String footprintPicName;
	String diary;
	String date;
	String encourage;
	String days;
	String daysLeft;
	
	
	
	public FootprintInfo(String coverPicPath, 
			String coverSongName, String footprintPicPath, String diary,
			String date, String encourage, String days, String daysLeft) {
		super();
		this.coverPicName = coverPicPath;
		//this.coverSongPath = coverSongPath;
		this.coverSongName = coverSongName;
		this.footprintPicName = footprintPicPath;
		this.diary = diary;
		this.date = date;
		this.encourage = encourage;
		this.days = days;
		this.daysLeft = daysLeft;
	}



	public String getEncourage() {
		return encourage;
	}



	public void setEncourage(String encourage) {
		this.encourage = encourage;
	}



	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(String daysLeft) {
		this.daysLeft = daysLeft;
	}


	
	public String getCoverSongName() {
		return coverSongName;
	}
	public void setCoverSongName(String coverSongName) {
		this.coverSongName = coverSongName;
	}
	
	public String getCoverPicName() {
		return coverPicName;
	}



	public void setCoverPicName(String coverPicName) {
		this.coverPicName = coverPicName;
	}



	public String getFootprintPicName() {
		return footprintPicName;
	}



	public void setFootprintPicName(String footprintPicName) {
		this.footprintPicName = footprintPicName;
	}



	public String getDiary() {
		return diary;
	}
	public void setDiary(String diary) {
		this.diary = diary;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}

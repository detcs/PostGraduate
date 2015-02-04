package com.data.model;

public class CourseRecordInfo {
	
	private String photoName;
	private String photobase64;
	private String remark;
	private String date;
	private String time;
	private String masterState;
	private String ifUpload;
	private int flag;
	public CourseRecordInfo(String photoName, String photobase64,
			String remark, String date, String time, String masterState,
			String ifUplaod, int flag) {
		super();
		this.photoName = photoName;
		this.photobase64 = photobase64;
		this.remark = remark;
		this.date = date;
		this.time = time;
		this.masterState = masterState;
		this.ifUpload = ifUplaod;
		this.flag = flag;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotobase64() {
		return photobase64;
	}
	public void setPhotobase64(String photobase64) {
		this.photobase64 = photobase64;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMasterState() {
		return masterState;
	}
	public void setMasterState(String masterState) {
		this.masterState = masterState;
	}
	public String getIfUpload() {
		return ifUpload;
	}
	public void setIfUpload(String ifUplaod) {
		this.ifUpload = ifUplaod;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	

}

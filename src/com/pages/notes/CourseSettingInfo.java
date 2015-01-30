package com.pages.notes;

import com.data.model.UserConfigs;

public class CourseSettingInfo {

	String english=null;
	String math=null;
	String politics=null;
	String profess1=null;
	String profess2=null;
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getMath() {
		return math;
	}
	public void setMath(String math) {
		this.math = math;
	}
	public String getPolitics() {
		return politics;
	}
	public void setPolitics(String politics) {
		this.politics = politics;
	}
	public String getProfess1() {
		return profess1;
	}
	public void setProfess1(String profess1) {
		this.profess1 = profess1;
	}
	public String getProfess2() {
		return profess2;
	}
	public void setProfess2(String profess2) {
		this.profess2 = profess2;
	}
	public void storeToConfig()
	{
		UserConfigs.storeCourseEnglishName(english);
		UserConfigs.storeCourseMathName(math);
		UserConfigs.storeCoursePoliticsName(politics);
		UserConfigs.storeCourseProfessOneName(profess1);
		UserConfigs.storeCourseProfessTwoName(profess2);
	}

}

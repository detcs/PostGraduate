package com.data.model;


import java.util.ArrayList;
import java.util.List;

import com.app.ydd.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class UserConfigs 
{
	static SharedPreferences sp=null;
	static Context context;
	static Editor editor;
	public UserConfigs(Context mcontext)
	{
		this.context=mcontext;
		sp=context.getSharedPreferences(context.getResources().getString(R.string.user_config_file), Context.MODE_PRIVATE);
		
	}
	public static void storeLoginWay(String loginWay)
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.login_way),loginWay);
		editor.commit();//提交修改
	}
	public static String getLoginWay()
	{		
		return sp.getString(context.getResources().getString(R.string.login_way),null);
	}
	public static void storeAccount(String account)
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.user_account),account);
		editor.commit();//提交修改
	}
	public static String getAccount()
	{		
		return sp.getString(context.getResources().getString(R.string.user_account),null);
	}
	public static void storePhone(String phone)
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.user_phone),phone);
		editor.commit();//提交修改
	}
	public static String getPhone()
	{		
		return sp.getString(context.getResources().getString(R.string.user_phone),null);
	}
	public static void storePassword(String pwd)
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.user_password),pwd);
		editor.commit();//提交修改
	}
	public static String getPassword()
	{		
		return sp.getString(context.getResources().getString(R.string.user_password),null);
	}
	public static void storeNickName(String nickName)
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.user_nickname),nickName);
		editor.commit();//提交修改
	}
	public static String getNickName()
	{		
		return sp.getString(context.getResources().getString(R.string.user_nickname),null);
	}
	public static void storeVerify(String verify)
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.user_verify),verify);
		editor.commit();//提交修改
	}
	public static String getVerify()
	{		
		return sp.getString(context.getResources().getString(R.string.user_verify),null);
	}
	public static void storeId(String id)
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.user_id),id);
		editor.commit();//提交修改
	}
	public static String getId()
	{		
		return sp.getString(context.getResources().getString(R.string.user_id),null);
	}
	public static void storeIsFirstTakePhoto(String is)
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.is_first_take_photo),is);
		editor.commit();//提交修改
	}
	public static String getIsFirstTakePhoto()
	{		
		return sp.getString(context.getResources().getString(R.string.is_first_take_photo),null);
	}
	public static void storeCourseEnglishName(String englishName)//1,2,3
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.course_english_name),englishName);
		editor.commit();//提交修改
	}
	public static String getCourseEnglishName()//1,2,3
	{		
		return sp.getString(context.getResources().getString(R.string.course_english_name),null);
	}
	public static void storeCourseMathName(String mathName)
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.course_math_name),mathName);
		editor.commit();//提交修改
	}
	public static String getCourseMathName()
	{		
		return sp.getString(context.getResources().getString(R.string.course_math_name),null);
	}
	public static void storeCoursePoliticsName(String politicsName)
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.course_politics_name),politicsName);
		editor.commit();//提交修改
	}
	public static String getCoursePoliticsName()
	{		
		return sp.getString(context.getResources().getString(R.string.course_politics_name),null);
	}
	public static void storeCourseProfessOneName(String professName)
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.course_profess1_name),professName);
		editor.commit();//提交修改
	}
	public static String getCourseProfessOneName()
	{		
		return sp.getString(context.getResources().getString(R.string.course_profess1_name),null);
	}
	public static void storeCourseProfessTwoName(String professName)
	{
		editor= sp.edit();//获取编辑器
		editor.putString(context.getResources().getString(R.string.course_profess2_name),professName);
		editor.commit();//提交修改
	}
	public static String getCourseProfessTwoName()
	{		
		return sp.getString(context.getResources().getString(R.string.course_profess2_name),null);
	}
	
	public static List<String> getCourseNames()
	{	List<String> names=new ArrayList<String>();
		names.add(context.getResources().getString(R.string.english)+getCourseEnglishName());
		names.add(context.getResources().getString(R.string.politics));
		if(getCourseMathName()!=null)
			names.add(context.getResources().getString(R.string.math)+getCourseMathName());
		names.add(getCourseProfessOneName());
		if(getCourseProfessTwoName()!=null)
			names.add(getCourseProfessTwoName());
		Log.e(DataConstants.TAG,"names.size() "+names.size());
		return names;
	}

}

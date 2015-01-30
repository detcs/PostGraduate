package com.data.model;


import com.app.ydd.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

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
	

}

package com.data.model;

import com.app.ydd.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
 
    private static final String DB_NAME = "ydd.db"; //数据库名称
    private static final int version = 1; //数据库版本
     
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, version);
        // TODO Auto-generated constructor stub
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "create table user(username varchar(20) not null , password varchar(60) not null );";          
//        db.execSQL(sql);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
 
    }
    public static void createMathTable(SQLiteDatabase db)
    {
    	String sql = "create table math(photoname varchar(100) not null , photobase64 varchar not null,remark varchar not null,time varchar not null );";          
        db.execSQL(sql);
    }
    public static void createEnglishTable(SQLiteDatabase db)
    {
    	String sql = "create table english(photoname varchar(100) not null , photobase64 varchar not null,remark varchar not null,time varchar not null );";          
        db.execSQL(sql);
    }
    public static void createPoliticsTable(SQLiteDatabase db)
    {
    	String sql = "create table politics(photoname varchar(100) not null , photobase64 varchar not null,remark varchar not null,time varchar not null );";          
        db.execSQL(sql);
    }
    public static void createProfessionalOneTable(SQLiteDatabase db)
    {
    	String sql = "create table profession1(photoname varchar(100) not null , photobase64 varchar not null,remark varchar not null,time varchar not null );";          
        db.execSQL(sql);
    }
    public static void createProfessionalTwoTable(SQLiteDatabase db)
    {
    	String sql = "create table profession2(photoname varchar(100) not null , photobase64 varchar not null,remark varchar not null,time varchar not null );";          
        db.execSQL(sql);
    }
    public static void createCourseTable(SQLiteDatabase db,String courseName)
    {
    	String sql = "create table "+courseName+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,photoname TEXT not null , photobase64 TEXT not null,remark TEXT not null,flag INTEGER,time TEXT not null );";          
        db.execSQL(sql);
    }
    public static void insertCourseRecord(Context context,SQLiteDatabase db,String tableName,String photoname,String photobase64,String remark,String time,int flag)
    {
    	ContentValues cv=new ContentValues();
    	cv.put(context.getResources().getString(R.string.db_photo_name), photoname);
    	cv.put(context.getResources().getString(R.string.db_photo_base64), photobase64);
    	cv.put(context.getResources().getString(R.string.db_remark), remark);
    	cv.put(context.getResources().getString(R.string.db_time), time);
    	cv.put(context.getResources().getString(R.string.db_flag), flag);
    	db.insert(tableName, null, cv);
    }
    public static void updateCourseRecordFlag(Context context,SQLiteDatabase db,String tableName,String photoname,int flag)
    {
    	ContentValues cv=new ContentValues();
       	cv.put(context.getResources().getString(R.string.db_flag), flag);
    	String whereClause =context.getResources().getString(R.string.db_photo_name)+ "=?";//修改条件
    	String[] whereArgs = {photoname};//修改条件的参数
    	db.update(tableName,cv,whereClause,whereArgs);//执行修改
    }
    public static void updateCourseRecordRemark(Context context,SQLiteDatabase db,String tableName,String photoname,String remark)
    {
    	ContentValues cv=new ContentValues();
       	cv.put(context.getResources().getString(R.string.db_remark), remark);
    	String whereClause =context.getResources().getString(R.string.db_photo_name)+ "=?";//修改条件
    	String[] whereArgs = {photoname};//修改条件的参数
    	db.update(tableName,cv,whereClause,whereArgs);//执行修改
    }
    public static boolean tableIsExist(SQLiteDatabase db,String tableName)
    {
        boolean result = false;
        if(tableName == null)
        {
                return false;
        }
        //SQLiteDatabase db = null;
        Cursor cursor = null;
        try 
        {
                //db = this.getReadableDatabase();
                String sql = "select count(*) as c from "+DB_NAME+" where type ='table' and name ='"+tableName.trim()+"' ";
                cursor = db.rawQuery(sql, null);
                if(cursor.moveToNext())
                {
                        int count = cursor.getInt(0);
                        if(count>0)
                        {
                                result = true;
                        }
                }
                
        } 
        catch (Exception e) 
        {
                // TODO: handle exception
        }               
        return result;
    }
}

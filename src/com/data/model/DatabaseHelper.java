package com.data.model;

import com.app.ydd.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
   
    public static void createCourseTable( Context context,SQLiteDatabase db,String courseName)
    {
    	String sql = "create table if not exists "+courseName+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+context.getResources().getString(R.string.dbcol_photo_name)+" TEXT not null , "
    			+context.getResources().getString(R.string.dbcol_photo_base64)+" TEXT not null,"
    			+context.getResources().getString(R.string.dbcol_remark)+" TEXT not null,"
    			+context.getResources().getString(R.string.dbcol_flag)+" INTEGER,"
    			+context.getResources().getString(R.string.dbcol_time)+" TEXT not null );";          
        Log.e(DataConstants.TAG, "sql:"+sql);
    	db.execSQL(sql);
    }
    public static void insertCourseRecord(Context context,SQLiteDatabase db,String tableName,String photoname,String photobase64,String remark,String time,int flag)
    {
    	ContentValues cv=new ContentValues();
    	cv.put(context.getResources().getString(R.string.dbcol_photo_name), photoname);
    	cv.put(context.getResources().getString(R.string.dbcol_photo_base64), photobase64);
    	cv.put(context.getResources().getString(R.string.dbcol_remark), remark);
    	cv.put(context.getResources().getString(R.string.dbcol_time), time);
    	cv.put(context.getResources().getString(R.string.dbcol_flag), flag);
    	long rowid=db.insert(tableName, null, cv);
    	Log.e(DataConstants.TAG,"rowid:"+rowid);
    }
    public static void updateCourseRecordFlag(Context context,SQLiteDatabase db,String tableName,String photoname,int flag)
    {
    	ContentValues cv=new ContentValues();
       	cv.put(context.getResources().getString(R.string.dbcol_flag), flag);
    	String whereClause =context.getResources().getString(R.string.dbcol_photo_name)+ "=?";//修改条件
    	String[] whereArgs = {photoname};//修改条件的参数
    	db.update(tableName,cv,whereClause,whereArgs);//执行修改
    }
    public static void updateCourseRecordRemark(Context context,SQLiteDatabase db,String tableName,String photoname,String remark)
    {
    	ContentValues cv=new ContentValues();
       	cv.put(context.getResources().getString(R.string.dbcol_remark), remark);
    	String whereClause =context.getResources().getString(R.string.dbcol_photo_name)+ "=?";//修改条件
    	String[] whereArgs = {photoname};//修改条件的参数
    	db.update(tableName,cv,whereClause,whereArgs);//执行修改
    }
    public static void dropTable(SQLiteDatabase db,String tableName){
        try
        {
            String sql="drop table "+tableName;                
            db.execSQL(sql);            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Log.e(DataConstants.TAG,"had deleted table: user->");  
    }
    public static void queryShowRecords(SQLiteDatabase db,String tableName)
    {
    	Cursor result=db.rawQuery("SELECT * FROM "+tableName,null); 
	    result.moveToFirst(); 
	    while (!result.isAfterLast()) { 
	         
	        String id=result.getString(0); 
	        String name=result.getString(1); 
	        Log.e(DataConstants.TAG,"db:query "+id+","+name);
	        result.moveToNext(); 
	      } 
	      result.close();
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
                Log.e(DataConstants.TAG,"exist "+sql);
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
        Log.e(DataConstants.TAG,"exist "+result);
        return result;
    }
}

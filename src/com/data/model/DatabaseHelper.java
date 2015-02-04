package com.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.app.ydd.R;
import com.pages.notes.footprint.FootprintInfo;

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
    			+context.getResources().getString(R.string.dbcol_date)+" TEXT not null,"
    			+context.getResources().getString(R.string.dbcol_master_state)+" TEXT not null,"
    			+context.getResources().getString(R.string.dbcol_time)+" TEXT not null );";          
        Log.e(DataConstants.TAG, "sql:"+sql);
    	db.execSQL(sql);
    }
    public static void createFootprintTable( Context context,SQLiteDatabase db)
    {
    	String sql = "create table if not exists "+context.getResources().getString(R.string.db_footprint_table)
    			+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
    			+context.getResources().getString(R.string.dbcol_cover_pic)+" TEXT not null , "
    			+context.getResources().getString(R.string.dbcol_cover_song)+" TEXT not null,"
    			+context.getResources().getString(R.string.dbcol_footprint_pic)+" TEXT not null,"
    			+context.getResources().getString(R.string.dbcol_diary)+" TEXT not null,"
    			+context.getResources().getString(R.string.dbcol_encourage)+" TEXT not null,"
    			+context.getResources().getString(R.string.dbcol_days)+" TEXT not null,"
    			+context.getResources().getString(R.string.dbcol_daysleft)+" TEXT not null,"
    			+context.getResources().getString(R.string.dbcol_date)+" TEXT not null );";          
        Log.e(DataConstants.TAG, "sql:"+sql);
    	db.execSQL(sql);
    }
    public static FootprintInfo queryFootPrintInfo(Context context,SQLiteDatabase db,String date)
    {
    	FootprintInfo fpInfo=null;
    	Cursor result=db.rawQuery("SELECT * FROM "+context.getResources().getString(R.string.db_footprint_table)+" where "+context.getResources().getString(R.string.dbcol_date)+"=' "+date+"'",null); 
	    result.moveToFirst(); 
	    while (!result.isAfterLast()) { 
	         
	        String coverPicPath=result.getString(1); 
	        String coverSongName=result.getString(2);
	        String footprintPic=result.getString(3);
	        String diary=result.getString(4);
	        String encourage=result.getString(5);
	        String days=result.getString(6);
	        String daysleft=result.getString(7);
	        fpInfo=new FootprintInfo(coverPicPath,coverSongName,footprintPic,diary,encourage,days,daysleft,date); 
	       // Log.e(DataConstants.TAG,"db:query "+id+","+name);
	        result.moveToNext(); 
	      } 
	      result.close();
	      return fpInfo;
    }
    
    public static void insertFootprintInfoRecord(Context context,SQLiteDatabase db,FootprintInfo fpInfo)
    {
    	ContentValues cv=new ContentValues();
    	cv.put(context.getResources().getString(R.string.dbcol_cover_pic), fpInfo.getCoverPicName());
    	cv.put(context.getResources().getString(R.string.dbcol_footprint_pic), fpInfo.getFootprintPicName());
    	cv.put(context.getResources().getString(R.string.dbcol_cover_song), fpInfo.getCoverSongName());
    	cv.put(context.getResources().getString(R.string.dbcol_encourage), fpInfo.getEncourage());
    	cv.put(context.getResources().getString(R.string.dbcol_date), fpInfo.getDate());
    	cv.put(context.getResources().getString(R.string.dbcol_diary), fpInfo.getDiary());
    	cv.put(context.getResources().getString(R.string.dbcol_days), fpInfo.getDays());
    	cv.put(context.getResources().getString(R.string.dbcol_daysleft), fpInfo.getDaysLeft());
    	long rowid=db.insert(context.getResources().getString(R.string.db_footprint_table), null, cv);
    	Log.e(DataConstants.TAG,"insert footprint rowid:"+rowid);
    }
    public static void updateFootprintRecord(Context context,SQLiteDatabase db,String updateCol,String updateValue,String date)
    {
    	Log.e(DataConstants.TAG, "updateFootprint "+updateCol+"="+updateValue+" at "+date);
    	ContentValues cv=new ContentValues();
       	cv.put(updateCol, updateValue);
    	String whereClause =context.getResources().getString(R.string.dbcol_date)+ "=?";//修改条件
    	String[] whereArgs = { date };//修改条件的参数
    	db.update(context.getResources().getString(R.string.db_footprint_table),cv,whereClause,whereArgs);//执行修改
    }
    public static void insertCourseRecord(Context context,SQLiteDatabase db,String tableName,String photoname,String photobase64,String remark,String date,String time,String masterState,int flag)
    {
    	ContentValues cv=new ContentValues();
    	cv.put(context.getResources().getString(R.string.dbcol_photo_name), photoname);
    	cv.put(context.getResources().getString(R.string.dbcol_photo_base64), photobase64);
    	cv.put(context.getResources().getString(R.string.dbcol_remark), remark);
    	cv.put(context.getResources().getString(R.string.dbcol_date), date);
    	cv.put(context.getResources().getString(R.string.dbcol_time), time);
    	cv.put(context.getResources().getString(R.string.dbcol_master_state), masterState);
    	cv.put(context.getResources().getString(R.string.dbcol_flag), flag);
    	long rowid=db.insert(tableName, null, cv);
    	Log.e(DataConstants.TAG,"rowid:"+rowid);
    }
    public static void updateCourseRecord(Context context,SQLiteDatabase db,String tableName,String updateCol,String updateValue,String date)
    {
    	ContentValues cv=new ContentValues();
       	cv.put(updateCol, updateValue);
    	String whereClause =context.getResources().getString(R.string.dbcol_date)+ "=?";//修改条件
    	String[] whereArgs = { date };//修改条件的参数
    	db.update(tableName,cv,whereClause,whereArgs);//执行修改
    }
    public static void updateCourseMasterState(Context context,SQLiteDatabase db,String tableName,String updateValue,String photoName)
    {
    	ContentValues cv=new ContentValues();
       	cv.put(context.getResources().getString(R.string.dbcol_master_state), updateValue);
    	String whereClause =context.getResources().getString(R.string.dbcol_photo_name)+ "=?";//修改条件
    	String[] whereArgs = { photoName };//修改条件的参数
    	db.update(tableName,cv,whereClause,whereArgs);//执行修改
    }
    public static void updateCourseRecordFlag(Context context,SQLiteDatabase db,String tableName,String photoname,int flag)
    {
    	ContentValues cv=new ContentValues();
       	cv.put(context.getResources().getString(R.string.dbcol_flag), flag);
    	String whereClause =context.getResources().getString(R.string.dbcol_photo_name)+ "=?";//修改条件
    	String[] whereArgs = {photoname};//修改条件的参数
    	db.update(tableName,cv,whereClause,whereArgs);//执行修改
    }
    public static int queryAllCourseRecordsCount(Context context,SQLiteDatabase db)
    {
    	int count=0;
    	count+=queryCourseRecordsCount(db, context.getResources().getString(R.string.db_english_table));
    	count+=queryCourseRecordsCount(db, context.getResources().getString(R.string.db_politics_table));
    	count+=queryCourseRecordsCount(db, context.getResources().getString(R.string.db_profess1_table));
    	if(UserConfigs.getCourseMathName()!=null)
    		count+=queryCourseRecordsCount(db, context.getResources().getString(R.string.db_math_table));
    	if(UserConfigs.getCourseProfessTwoName()!=null)
    		count+=queryCourseRecordsCount(db, context.getResources().getString(R.string.db_profess2_table));
	      return count;
    }
    public static int queryCourseRecordsCount(SQLiteDatabase db,String tableName)
    {
    	Cursor result=db.rawQuery("SELECT count(*) FROM "+tableName,null); 
	    result.moveToFirst(); 
	    int count=0;
	    while (!result.isAfterLast()) { 
	         
	        count=result.getInt(0); 
	       // String name=result.getString(1); 
	        Log.e(DataConstants.TAG,"db:query count:"+tableName+":"+count);
	        result.moveToNext(); 
	      } 
	      result.close();
	      return count;
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
    public static TreeSet<String> queryDates(Context context,SQLiteDatabase db,String tableName)
    {
    	TreeSet<String> dates=new TreeSet<String>();
    	Cursor result=db.rawQuery("SELECT "+context.getResources().getString(R.string.dbcol_date)+" FROM "+tableName,null); 
	    result.moveToFirst(); 
	    while (!result.isAfterLast()) { 
	         
	        String date=result.getString(0); 
	        dates.add(date); 
	       // Log.e(DataConstants.TAG,"db:query "+id+","+name);
	        result.moveToNext(); 
	      } 
	      result.close();
	      return dates;
    }
    public static List<String> queryPhotoNamesAtDate(Context context,SQLiteDatabase db,String tableName,String date)
    {
    	List<String> names=new ArrayList<String>();
    	Cursor result=db.rawQuery("SELECT "+context.getResources().getString(R.string.dbcol_photo_name)+" FROM "+tableName+" where "+context.getResources().getString(R.string.dbcol_date)+" = '"+date+"'",null); 
	    result.moveToFirst(); 
	    while (!result.isAfterLast()) { 
	         
	        String name=result.getString(0); 
	        names.add(name); 
	        Log.e(DataConstants.TAG,"db:query photo name:"+name);
	        result.moveToNext(); 
	      } 
	      result.close();
	      return names;
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

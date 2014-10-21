package com.example.eatwhat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final int VERSION=1;
	/**
	 * SQLiteOpenHelper 子类中必须要重写onCreate及onUpgrade函数
	 * @param context   上下文对象
	 * @param name    数据库名称
	 * @param factory    
	 * @param version    版本号
	 */
	public DatabaseHelper(Context context,String name,CursorFactory factory ,int version){
		super(context,name,factory, version);
	}

	public DatabaseHelper(Context context, String name, int version){  
        this(context,name,null,version);  
    }  
  
    public DatabaseHelper(Context context, String name){  
        this(context,name,VERSION);  
    }  
    @Override 
    public void onCreate(SQLiteDatabase db) {  
        // TODO Auto-generated method stub  
        System.out.println("create a database");  
        //execSQL用于执行SQL语句  
        db.execSQL("create table dishes(id int,name varchar(20))");  
    }  
    
    @Override 
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){
    	System.out.println("Upgrade a database");
    	
    }
}
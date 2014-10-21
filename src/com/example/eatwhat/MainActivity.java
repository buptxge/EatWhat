package com.example.eatwhat;

import com.example.eatwhat.R.string;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button mButton1;
	private TextView mTextView1;
	private TextView rTextView1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mButton1 = (Button) findViewById(R.id.startButton);
		mButton1.setOnClickListener(new StartButtonOnClickListener());
		mTextView1 = (TextView) findViewById(R.id.textView1);
		rTextView1 =(TextView) findViewById(R.id.resultTextView);
		
		DatabaseHelper dbHelper =  new DatabaseHelper(this,"dishes_db");
		//创建了一个DatabaseHelper对象,只执行这句话不会创建或打开连接
		SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
		// 执行了getReadableDatabase 或者 getWriteableDatabase之后会打开连接
		
		ContentValues values = new ContentValues();
		values.put("id",2);
		values.put("name","yuxiangrousi");
		sqLiteDatabase.insert("dishes", null, values);
		
	}
	
	 class StartButtonOnClickListener implements Button.OnClickListener {
		public void onClick(View v){	
			String id = null;
			String name= null;
			DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "dishes_db");
			
			// 调用SQLiteDatabase对象的query方法进行查询，返回一个Cursor对象：由数据库查询返回的结果集对象  
            // 第一个参数String：表名  
            // 第二个参数String[]:要查询的列名  
            // 第三个参数String：查询条件  
            // 第四个参数String[]：查询条件的参数  
            // 第五个参数String:对查询的结果进行分组  
            // 第六个参数String：对分组的结果进行限制  
            // 第七个参数String：对查询的结果进行排序  
			SQLiteDatabase sqliteDatabase = dbHelper.getReadableDatabase();
			Cursor cursor = sqliteDatabase.query("dishes", new String[] { "id",  
            "name" }, "id>?", new String[] { "0" }, null, null, null);  
			
			while (cursor.moveToNext()) {  
                id = cursor.getString(cursor.getColumnIndex("id"));  
                name = cursor.getString(cursor.getColumnIndex("name"));  
                rTextView1.setText(id+name);
			}
		}
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}

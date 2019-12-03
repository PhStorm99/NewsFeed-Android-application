package com.example.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;

public class MyDatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyNewsFileforsaving";
    public static final int VERSION_NUM = 1;
    public static final String TABLE_NAME = "database1";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "TITLE";
    public static final String COL_desription = "DESCRIPTION";
    public static final String COL_img = "image";
   public static final String COL_URL = "URL";
   public MyDatabaseOpenHelper(Activity ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM );
    }
    public void onCreate(SQLiteDatabase db)
    { db.execSQL("CREATE TABLE " + TABLE_NAME + "( "
                + COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_TITLE + " TEXT,\"\n"
                + COL_desription + " TEXT,\n\")");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.i("Database upgrade", "Old version:" + oldVersion + " newVersion:"+newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.i("Database downgrade", "Old version:" + oldVersion + " newVersion:"+newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void printCursor(Cursor c, SQLiteDatabase db) {
        Log.e(MyDatabaseOpenHelper.class.getName(), "DB Version:" + db.getVersion());
        Log.e(MyDatabaseOpenHelper.class.getName(), "Number of Columns" + c.getColumnCount());
        Log.e(MyDatabaseOpenHelper.class.getName(), "Column names:" + Arrays.toString(c.getColumnNames()));
        c.moveToLast();
        Log.e(MyDatabaseOpenHelper.class.getName(), "Number of results:" + c.getPosition());
        c.moveToFirst();

        while (c.moveToNext()) {
            String title = c.getString(0);
        //    String url = c.getString(2);
            String fullText = c.getString(1);
            Log.e(MyDatabaseOpenHelper.class.getName(), "title: " + title + ";" + " fullText: " + fullText);
        }
    }
    public Cursor alldata()
    {  final SQLiteDatabase db = this.getWritableDatabase();

        Cursor c=db.rawQuery("select * from MyDatabaseOpenHelper.TABLE_NAME ",null);
        return c;
    }
    }

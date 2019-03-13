package com.example.admin.ciassignment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.TableRow;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="dbAIS";
    public static final int DATABASE_VERSION=3;

    public static final String TABLE_NAME="tb1Students";

    public static final String COLUMN_USER_ID="userID";
    public static final String COLUMN_PASSWORD="password";

    private SQLiteDatabase db;



    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        String createTable="Create Table "+ TABLE_NAME + "( "+ COLUMN_USER_ID + " TEXT, "+ COLUMN_PASSWORD + " TEXT) ";
        db.execSQL(createTable);

        String insertQuery1="insert into "+ TABLE_NAME + " values( 'A123','123456')";

        db.execSQL(insertQuery1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+ TABLE_NAME);
        onCreate(db);
    }

    public void insertUserData(String userID, String password)
    {
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_USER_ID,userID);
        contentValues.put(COLUMN_PASSWORD,password);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

      public boolean isUserExit(String userID, String password){
        boolean checkData;
        int count;
        db=this.getReadableDatabase();
        String query="Select * from " + TABLE_NAME + " where " + COLUMN_USER_ID + " ='" + userID + "' AND "+
                COLUMN_PASSWORD + " ='"+ password +"'";
        Cursor cursor= db.rawQuery(query,null);
        count = cursor.getCount();
        if(count==1){
            checkData=true;
        }
        else {
            checkData=false;
        }
        db.close();
        cursor.close();
        return checkData;
    }
}

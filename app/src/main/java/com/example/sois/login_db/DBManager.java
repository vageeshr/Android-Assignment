package com.example.sois.login_db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public int Insert1(String id, String name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper._ID, id);
        contentValue.put(DBHelper.NAME, name);

        //database.insert(DBHelper.TABLE_NAME, null, contentValue);
        long userid = database.insert(DBHelper.TABLE_NAME, null, contentValue);

        return (int) userid;

    }

    public String fetchByID(String id) {
        String sql = "SELECT " + DBHelper.NAME + " FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper._ID + "='" + id + "' LIMIT 1";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            cursor.close();
            return name;
        } else {
            cursor.close();
            return null;
        }
    }
}

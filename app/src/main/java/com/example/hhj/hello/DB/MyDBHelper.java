package com.example.hhj.hello.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hhj.hello.Model.Message;
import com.example.hhj.hello.Model.User;

import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String sCREATE_USER = "create table users ("
            + "user_id integer primary key autoincrement, "
            + "user_name text, "
            + "password text)";//用户表

    public static final String sCREATE_MESSAGE = "create table messages ("
            + "message_id integer primary key autoincrement, "
            + "user_send_id integer, "
            + "user_rec_id integer, "
            + "content text, "
            + "send_date integer)";//消息表

    public static final String sCREATE_LIST = "create table lists ("
            + "list_id integer primary key autoincrement, "
            + "user_id integer, "
            + "list_name text)";//好友列表表

    public static final String sCREATE_LIST_CONTENT = "create table list_content ("
            + "list_id integer, "
            + "user_id integer)";//好友列表内容表

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sCREATE_USER);
        db.execSQL(sCREATE_MESSAGE);
        db.execSQL(sCREATE_LIST);
        db.execSQL(sCREATE_LIST_CONTENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

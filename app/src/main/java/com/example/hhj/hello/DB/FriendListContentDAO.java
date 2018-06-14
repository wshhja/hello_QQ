package com.example.hhj.hello.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hhj.hello.Model.FriendListContent;
import com.example.hhj.hello.utils.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class FriendListContentDAO {
    static MyDBHelper sHelper;
    static SQLiteDatabase sDb;

    public static void Insert(FriendListContent content){
        if(sHelper==null){
            sHelper=new MyDBHelper(MyApplication.getContext(),"qq_test.db",null,1);
            sDb=sHelper.getWritableDatabase();
        }
        ContentValues contentValues=new ContentValues();
        contentValues.put("user_id",content.getUserId());
        contentValues.put("list_id",content.getListId());
        sDb.insert("list_content",null,contentValues);
    }

    public static List<FriendListContent> GetById(int id){
        List<FriendListContent> contents=new ArrayList<>();
        FriendListContent content;
        if(sHelper==null){
            sHelper=new MyDBHelper(MyApplication.getContext(),"qq_test.db",null,1);
            sDb=sHelper.getWritableDatabase();
        }
        Cursor cursor=sDb.query("list_content",null,"list_id=?",new String[]{id+""},null,null,null);
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                content = new FriendListContent();
                content.setListId(id);
                content.setUserId(cursor.getInt(1));
                contents.add(content);
            }
        }
        cursor.close();
        return contents;
    }
}

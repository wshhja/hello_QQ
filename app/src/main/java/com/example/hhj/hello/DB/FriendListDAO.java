package com.example.hhj.hello.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hhj.hello.Model.FriendList;
import com.example.hhj.hello.utils.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class FriendListDAO {
    static MyDBHelper sHelper;
    static SQLiteDatabase sDb;

    public static void Insert(FriendList friendList){
        if(sHelper==null){
            sHelper=new MyDBHelper(MyApplication.getContext(),"qq_test.db",null,1);
            sDb=sHelper.getWritableDatabase();
        }
        ContentValues contentValues=new ContentValues();
        contentValues.put("user_id",friendList.getUserId());
        contentValues.put("list_name",friendList.getListName());
        sDb.insert("lists",null,contentValues);
    }

    public static List<FriendList> GetByUserId(int id){
        List<FriendList> list=new ArrayList<>();
        FriendList friendList;
        if(sHelper==null){
            sHelper=new MyDBHelper(MyApplication.getContext(),"qq_test.db",null,1);
            sDb=sHelper.getWritableDatabase();
        }
        Cursor cursor=sDb.query("lists",null,"user_id=?",new String[]{id+""},null,null,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                friendList=new FriendList();
                friendList.setListId(cursor.getInt(0));
                friendList.setUserId(id);
                friendList.setListName(cursor.getString(2));
                list.add(friendList);
            }
        }
        cursor.close();
        return list;
    }

}

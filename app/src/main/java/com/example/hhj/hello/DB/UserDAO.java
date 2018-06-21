package com.example.hhj.hello.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hhj.hello.Model.User;
import com.example.hhj.hello.utils.MyApplication;

import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    static MyDBHelper sHelper;
    static SQLiteDatabase sDb;
    public static User GetById(int id){
        if(sHelper==null){
            sHelper=new MyDBHelper(MyApplication.getContext(),"qq_test.db",null,1);
            sDb=sHelper.getWritableDatabase();
        }
        Cursor cursor=sDb.query("users",null,"user_id=?",new String[]{id+""},null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            User user=new User();
            user.setUserId(id);
            user.setUserName(cursor.getString(1));
            user.setUserPs(cursor.getString(2));
            cursor.close();
            return user;
        }
        else{
            cursor.close();
            return null;
        }
    }

    public static User GetByNamePs(String name,String ps){
        if(sHelper==null){
            sHelper=new MyDBHelper(MyApplication.getContext(),"qq_test.db",null,1);
            sDb=sHelper.getWritableDatabase();
        }
        Cursor cursor=sDb.query("users",null,"user_name=? and password=?",new String[]{name,ps},null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            User user=new User();
            user.setUserId(cursor.getInt(0));
            user.setUserName(cursor.getString(1));
            user.setUserPs(cursor.getString(2));
            cursor.close();
            return user;
        }
        else{
            cursor.close();
            return null;
        }
    }

    public static User GetByName(String name){
        if(sHelper==null){
            sHelper=new MyDBHelper(MyApplication.getContext(),"qq_test.db",null,1);
            sDb=sHelper.getWritableDatabase();
        }
        Cursor cursor=sDb.query("users",null,"user_name=?",new String[]{name},null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            User user=new User();
            user.setUserId(cursor.getInt(0));
            user.setUserName(cursor.getString(1));
            user.setUserPs(cursor.getString(2));
            cursor.close();
            return user;
        }
        else{
            cursor.close();
            return null;
        }
    }

    public static void Insert(User user){
        if(sHelper==null){
            sHelper=new MyDBHelper(MyApplication.getContext(),"qq_test.db",null,1);
            sDb=sHelper.getWritableDatabase();
        }
        ContentValues contentValues=new ContentValues();
        contentValues.put("user_name",user.getUserName());
        contentValues.put("password",user.getUserPs());
        sDb.insert("users",null,contentValues);
    }

    public static List<User> GetAll(){
        List<User> list=new ArrayList<>();
        User user;
        if(sHelper==null){
            sHelper=new MyDBHelper(MyApplication.getContext(),"qq_test.db",null,1);
            sDb=sHelper.getWritableDatabase();
        }
        Cursor cursor=sDb.query("users",null,null,null,null,null,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                user=new User();
                user.setUserId(cursor.getInt(0));
                user.setUserName(cursor.getString(1));
                user.setUserPs(cursor.getString(2));
            }
            cursor.close();
            return list;
        }
        else{
            cursor.close();
            return null;
        }
    }

}

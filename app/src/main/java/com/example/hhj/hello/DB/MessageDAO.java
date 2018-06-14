package com.example.hhj.hello.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hhj.hello.Model.Message;
import com.example.hhj.hello.utils.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    static MyDBHelper sHelper;
    static SQLiteDatabase sDb;

    public static List<Message> GetByUserId(int id){//获取当前用户所有聊天记录
        List<Message> messages=new ArrayList<Message>();
        Message message;
        if(sHelper==null){
            sHelper=new MyDBHelper(MyApplication.getContext(),"qq_test.db",null,1);
            sDb=sHelper.getWritableDatabase();
        }
        Cursor cursor=sDb.query("messages",null,"user_send_id=? or user_rec_id=?",new String[]{id+"",id+""},null,null,"send_date");
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                message=new Message();
                message.setMessageId(cursor.getInt(0));
                message.setUserSend(cursor.getInt(1));
                message.setUserRec(cursor.getInt(2));
                message.setContent(cursor.getString(3));
                message.setSendDT(cursor.getLong(4));
                messages.add(message);
            }

            for(int i=0;i<messages.size();i++){//去重，每个对话保留一项
                for(int j=i+1;j<messages.size();j++){
                    if(((messages.get(i).getUserSend()==messages.get(j).getUserSend())&&(messages.get(i).getUserRec()==messages.get(j).getUserRec()))||((messages.get(i).getUserSend()==messages.get(j).getUserRec())&&(messages.get(i).getUserRec()==messages.get(j).getUserSend()))){
                        messages.remove(j);j--;
                    }
                }
            }
        }

        cursor.close();
        return messages;
    }

    public static List<Message> GetByBothId(int id1,int id2){//获取两个人的聊天记录
        List<Message> messages=new ArrayList<Message>();
        Message message;
        if(sHelper==null){
            sHelper=new MyDBHelper(MyApplication.getContext(),"qq_test.db",null,1);
            sDb=sHelper.getWritableDatabase();
        }
        Cursor cursor=sDb.query("messages",null,"(user_send_id=? and user_rec_id=?) or (user_send_id=? and user_rec_id=?)",new String[]{id1+"",id2+"",id2+"",id1+""},null,null,"send_date asc");
        while (cursor.moveToNext()){
            message=new Message();
            message.setMessageId(cursor.getInt(0));
            message.setUserSend(cursor.getInt(1));
            message.setUserRec(cursor.getInt(2));
            message.setContent(cursor.getString(3));
            message.setSendDT(cursor.getLong(4));
            messages.add(message);
        }
        cursor.close();
        return messages;
    }

    public static void Insert(Message message){//插入新消息
        if(sHelper==null){
            sHelper=new MyDBHelper(MyApplication.getContext(),"qq_test.db",null,1);
            sDb=sHelper.getWritableDatabase();
        }
        ContentValues contentValues=new ContentValues();
        contentValues.put("user_send_id",message.getUserSend());
        contentValues.put("user_rec_id",message.getUserRec());
        contentValues.put("content",message.getContent());
        contentValues.put("send_date",message.getSendDT());
        sDb.insert("messages",null,contentValues);
    }
}

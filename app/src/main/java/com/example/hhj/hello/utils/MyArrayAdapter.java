package com.example.hhj.hello.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hhj.hello.Activity.AddFriendActivity;
import com.example.hhj.hello.Activity.MainFormActivity;
import com.example.hhj.hello.DB.FriendListContentDAO;
import com.example.hhj.hello.Model.FriendList;
import com.example.hhj.hello.Model.FriendListContent;
import com.example.hhj.hello.R;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter {

    private int mResourceId;
    private List<FriendList> mFriendLists;
    public MyArrayAdapter(@NonNull Context context, int resource, @NonNull List<FriendList> list) {
        super(context, resource, list);
        mResourceId=resource;
        mFriendLists=list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(mResourceId, null);//实例化一个对象
        TextView textView=view.findViewById(R.id.expand_list_parent_tv);
        textView.setText(mFriendLists.get(position).getListName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getContext())
                        .setTitle("确认")
                        .setMessage("确定将"+AddFriendActivity.sNAME_TO_ADD+"添加到分组"+mFriendLists.get(position).getListName()+"吗？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FriendListContent friendListContent=new FriendListContent();
                                friendListContent.setListId(mFriendLists.get(position).getListId());
                                friendListContent.setUserId(AddFriendActivity.USER_TO_ADD);

                                FriendListContentDAO.Insert(friendListContent);
                                Toast.makeText(MyApplication.getContext(),"添加成功",Toast.LENGTH_SHORT).show();
                                getContext().startActivity(new Intent(getContext(), MainFormActivity.class));
                            }
                        })
                        .create().show();

            }
        });
        return view;
    }
}


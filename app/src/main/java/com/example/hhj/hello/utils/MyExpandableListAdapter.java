package com.example.hhj.hello.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hhj.hello.Activity.UserInfoActivity;
import com.example.hhj.hello.DB.UserDAO;
import com.example.hhj.hello.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.support.v4.content.ContextCompat.startActivity;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Map<String,List<String>> mDataset;
    private List<String> mList;
    private Handler mHandler;

    public MyExpandableListAdapter(Map<String,List<String>> map){
        mDataset=map;
        Set<String> keySet=mDataset.keySet();
        mList=new ArrayList<>();
        for(String s:keySet){
            mList.add(s);
        }
        mHandler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                notifyDataSetChanged();
                super.handleMessage(msg);
            }
        };
    }
    @Override
    public int getGroupCount() {
        return mDataset.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mDataset.get(mList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDataset.get(mList.get(groupPosition));
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDataset.get(mList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_parent_item, null);
        }
        convertView.setTag(R.layout.expandable_list_parent_item, groupPosition);
        convertView.setTag(R.layout.expandable_list_child_item, -1);
        TextView text = convertView.findViewById(R.id.expand_list_parent_tv);
        text.setText(mList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_child_item, null);
        }
        convertView.setTag(R.layout.expandable_list_parent_item, groupPosition);
        convertView.setTag(R.layout.expandable_list_child_item, childPosition);
        TextView text = convertView.findViewById(R.id.expand_list_child_tv);
        final String name=mDataset.get(mList.get(groupPosition)).get(childPosition);
        text.setText(name);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApplication.getContext(), UserInfoActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("id", UserDAO.GetByName(name).getUserId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(MyApplication.getContext(),intent,null);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void reFresh(){
        mDataset.clear();
        mList.clear();
        mHandler.sendMessage(new Message());
    }
}

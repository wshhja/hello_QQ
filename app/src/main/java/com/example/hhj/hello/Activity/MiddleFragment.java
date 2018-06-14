package com.example.hhj.hello.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.hhj.hello.DB.FriendListContentDAO;
import com.example.hhj.hello.DB.FriendListDAO;
import com.example.hhj.hello.DB.UserDAO;
import com.example.hhj.hello.Model.FriendList;
import com.example.hhj.hello.Model.FriendListContent;
import com.example.hhj.hello.R;
import com.example.hhj.hello.utils.MyExpandableListAdapter;
import com.example.hhj.hello.utils.SharePUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiddleFragment extends Fragment {
    private Map<String,List<String>> mDataSet;
    private int user_id;
    private MyExpandableListAdapter mAdapter;
    private ExpandableListView mListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataSet=new HashMap<>();
        user_id=SharePUtil.Get();
        List<FriendList> list= FriendListDAO.GetByUserId(user_id);
        for(FriendList friendList:list){
            List<FriendListContent> list1=FriendListContentDAO.GetById(friendList.getListId());
            List<String> list2=new ArrayList<>();
            for(FriendListContent content:list1){
                list2.add(UserDAO.GetById(content.getUserId()).getUserName());
            }
            mDataSet.put(friendList.getListName(),list2);
        }
        View view=inflater.inflate(R.layout.middle_fragment,container,false);
        mListView=view.findViewById(R.id.rv_middle);
        mAdapter=new MyExpandableListAdapter(mDataSet);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return view;
    }

}

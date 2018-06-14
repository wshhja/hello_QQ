package com.example.hhj.hello.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hhj.hello.DB.MessageDAO;
import com.example.hhj.hello.Model.Message;
import com.example.hhj.hello.R;
import com.example.hhj.hello.utils.MessageAdapter;
import com.example.hhj.hello.utils.MyApplication;
import com.example.hhj.hello.utils.SharePUtil;

import java.util.List;

public class LeftFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<Message> mList;
    private MessageAdapter mAdapter;
    private int user_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.rv_left_fragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        user_id = SharePUtil.Get();
        mList = MessageDAO.GetByUserId(user_id);
        mAdapter = new MessageAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}

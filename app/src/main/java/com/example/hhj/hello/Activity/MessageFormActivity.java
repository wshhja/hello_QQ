package com.example.hhj.hello.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hhj.hello.DB.MessageDAO;
import com.example.hhj.hello.DB.UserDAO;
import com.example.hhj.hello.Model.Message;
import com.example.hhj.hello.R;
import com.example.hhj.hello.utils.MessageFormAdapter;
import com.example.hhj.hello.utils.MyApplication;
import com.example.hhj.hello.utils.SharePUtil;

import java.util.List;

public class MessageFormActivity extends AppCompatActivity {
    private List<Message> mList;
    private int user_id;
    private int id_other;

    private RecyclerView mRecyclerView;
    private MessageFormAdapter mAdapter;
    private Button mButton;
    private EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_form);

        mRecyclerView = findViewById(R.id.message_recycler);
        mButton = findViewById(R.id.btn_send);
        mEditText = findViewById(R.id.et_message);

        user_id = SharePUtil.Get();
        Intent intent = getIntent();
        int id1 = intent.getIntExtra("id1", -1);
        int id2 = intent.getIntExtra("id2", -1);
        if (id1 == user_id) {
            id_other = id2;
        } else {
            id_other = id1;
        }
        mList = MessageDAO.GetByBothId(id1, id2);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(UserDAO.GetById(id_other).getUserName());

        mAdapter = new MessageFormAdapter(mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.smoothScrollToPosition(mList.size());


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = mEditText.getText().toString();
                if (msg == "") return;
                Message message = new Message();
                message.setSendDT(System.currentTimeMillis());
                message.setContent(msg);
                message.setUserSend(user_id);
                message.setUserRec(id_other);
                MessageDAO.Insert(message);
                mEditText.setText("");
                mList.add(message);
                mAdapter.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(mList.size());
            }
        });
    }
}

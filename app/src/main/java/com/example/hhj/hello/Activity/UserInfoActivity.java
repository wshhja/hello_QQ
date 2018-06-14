package com.example.hhj.hello.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.hhj.hello.R;
import com.example.hhj.hello.utils.SharePUtil;

public class UserInfoActivity extends AppCompatActivity {

    private TextView mTextView_name;
    private TextView mTextView_button;
    private TextView mTextView_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mTextView_name=findViewById(R.id.user_info_user_name);
        mTextView_button=findViewById(R.id.tv_tv_tv);
        mTextView_back=findViewById(R.id.tv_user_info_back);
        final Intent intent=getIntent();
        mTextView_name.setText(intent.getStringExtra("name"));
        mTextView_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(UserInfoActivity.this,MessageFormActivity.class);
                intent1.putExtra("id1",intent.getIntExtra("id",0));
                intent1.putExtra("id2", SharePUtil.Get());
                startActivity(intent1);
            }
        });
        mTextView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

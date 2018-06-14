package com.example.hhj.hello.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hhj.hello.DB.FriendListDAO;
import com.example.hhj.hello.DB.UserDAO;
import com.example.hhj.hello.Model.FriendList;
import com.example.hhj.hello.Model.User;
import com.example.hhj.hello.R;
import com.example.hhj.hello.utils.MyArrayAdapter;
import com.example.hhj.hello.utils.SharePUtil;

import java.util.List;

public class AddFriendActivity extends AppCompatActivity {

    private int mUserId;
    private EditText mEditText;
    private Button mButton;
    private View mView;
    private TextView mTextView_name;
    private ListView mListView;
    private MyArrayAdapter mMyArrayAdapter;
    private List<FriendList> mList;
    public static String sNAME_TO_ADD;

    public static int USER_TO_ADD;

    public AddFriendActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        getSupportActionBar().setTitle("添加好友");

        mUserId =SharePUtil.Get();
        mEditText=findViewById(R.id.et_add_friend);
        mButton=findViewById(R.id.btn_search);
        mView=findViewById(R.id.layout_user);
        mView.setVisibility(View.GONE);
        mTextView_name=mView.findViewById(R.id.expand_list_child_tv);
        mListView=findViewById(R.id.lv_add_friend);
        mListView.setVisibility(View.INVISIBLE);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mEditText.getText().toString();
                if(!("".equals(name))){
                    sNAME_TO_ADD =name;
                    User user= UserDAO.GetByName(name);
                    if(!(user.getUserId()== mUserId)){
                        USER_TO_ADD=user.getUserId();
                        mView.setVisibility(View.VISIBLE);
                        mTextView_name.setText(name);
                    }
                }
            }
        });
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListView.setVisibility(View.VISIBLE);
            }
        });

        mList=FriendListDAO.GetByUserId(mUserId);
        mMyArrayAdapter=new MyArrayAdapter(AddFriendActivity.this,R.layout.expandable_list_parent_item,mList);
        mListView.setAdapter(mMyArrayAdapter);

    }
}

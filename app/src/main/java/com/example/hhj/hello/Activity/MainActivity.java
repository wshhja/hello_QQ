package com.example.hhj.hello.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hhj.hello.DB.FriendListContentDAO;
import com.example.hhj.hello.DB.FriendListDAO;
import com.example.hhj.hello.DB.MessageDAO;
import com.example.hhj.hello.DB.UserDAO;
import com.example.hhj.hello.Model.FriendList;
import com.example.hhj.hello.Model.FriendListContent;
import com.example.hhj.hello.Model.Message;
import com.example.hhj.hello.Model.User;
import com.example.hhj.hello.R;
import com.example.hhj.hello.utils.SharePUtil;


public class MainActivity extends AppCompatActivity {
    Button btn_login;
    Button btn_register;
    EditText et_name;
    EditText et_ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        int id = SharePUtil.Get();
        if (id != -1) {
            Intent intent = new Intent(MainActivity.this, MainFormActivity.class);
            startActivity(intent);
            finish();
        } else {
            //init();
            btn_login = findViewById(R.id.login);
            btn_register = findViewById(R.id.register);
            et_name = findViewById(R.id.account);
            et_ps = findViewById(R.id.password);

            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = et_name.getText().toString();
                    String ps = et_ps.getText().toString();
                    User user = UserDAO.GetByNamePs(name, ps);
                    if (user != null) {
                        SharePUtil.Put(user.getUserId());
                        Intent intent = new Intent(MainActivity.this, MainFormActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "账号密码错误，请检查", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btn_register.setOnClickListener(new View.OnClickListener() {//注册
                @Override
                public void onClick(View v) {
                    String name = et_name.getText().toString();
                    String ps = et_ps.getText().toString();
                    UserDAO.Insert(new User(name, ps));
                    User user = UserDAO.GetByName(name);
                    FriendList friendList = new FriendList();
                    friendList.setUserId(user.getUserId());
                    friendList.setListName("我的好友");
                    FriendListDAO.Insert(friendList);
                    Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void init() {//初始化数据库数据
        User user = new User();//1
        user.setUserName("胡恒建");
        user.setUserPs("123");
        UserDAO.Insert(user);

        user.setUserName("古月");//2
        user.setUserPs("456");
        UserDAO.Insert(user);

        user.setUserName("张三");//3
        user.setUserPs("789");
        UserDAO.Insert(user);

        FriendList friendList = new FriendList();
        friendList.setUserId(1);
        friendList.setListName("t1");
        FriendListDAO.Insert(friendList);//1
        friendList.setUserId(1);
        friendList.setListName("t2");
        FriendListDAO.Insert(friendList);//2
        friendList.setUserId(1);
        friendList.setListName("t3");
        FriendListDAO.Insert(friendList);//3
        friendList.setUserId(1);
        friendList.setListName("t4");
        FriendListDAO.Insert(friendList);//4

        friendList.setUserId(2);
        friendList.setListName("t21");
        FriendListDAO.Insert(friendList);//5
        friendList.setUserId(2);
        friendList.setListName("t22");
        FriendListDAO.Insert(friendList);//6
        friendList.setUserId(2);
        friendList.setListName("t23");
        FriendListDAO.Insert(friendList);//7
        friendList.setUserId(2);
        friendList.setListName("t24");
        FriendListDAO.Insert(friendList);//8

        FriendListContent friendListContent = new FriendListContent();
        friendListContent.setListId(1);
        friendListContent.setUserId(2);
        FriendListContentDAO.Insert(friendListContent);
        friendListContent.setListId(1);
        friendListContent.setUserId(3);
        FriendListContentDAO.Insert(friendListContent);

        friendListContent.setListId(2);
        friendListContent.setUserId(2);
        FriendListContentDAO.Insert(friendListContent);
        friendListContent.setListId(2);
        friendListContent.setUserId(3);
        FriendListContentDAO.Insert(friendListContent);

        friendListContent.setListId(3);
        friendListContent.setUserId(2);
        FriendListContentDAO.Insert(friendListContent);
        friendListContent.setListId(3);
        friendListContent.setUserId(3);
        FriendListContentDAO.Insert(friendListContent);

        friendListContent.setListId(5);
        friendListContent.setUserId(1);
        FriendListContentDAO.Insert(friendListContent);
        friendListContent.setListId(5);
        friendListContent.setUserId(3);
        FriendListContentDAO.Insert(friendListContent);

        friendListContent.setListId(6);
        friendListContent.setUserId(1);
        FriendListContentDAO.Insert(friendListContent);
        friendListContent.setListId(6);
        friendListContent.setUserId(3);
        FriendListContentDAO.Insert(friendListContent);

        friendListContent.setListId(7);
        friendListContent.setUserId(1);
        FriendListContentDAO.Insert(friendListContent);
        friendListContent.setListId(7);
        friendListContent.setUserId(3);
        FriendListContentDAO.Insert(friendListContent);


        Message message = new Message();
        message.setUserRec(2);
        message.setUserSend(1);
        message.setContent("test1test1test1test1test1test1test1test1test1test1test1test11test1test1test1");
        message.setSendDT(System.currentTimeMillis());
        MessageDAO.Insert(message);

        message.setUserRec(1);
        message.setUserSend(2);
        message.setContent("test20");
        message.setSendDT(System.currentTimeMillis());
        MessageDAO.Insert(message);

        message.setUserRec(1);
        message.setUserSend(3);
        message.setContent("test2");
        message.setSendDT(System.currentTimeMillis());
        MessageDAO.Insert(message);

    }
}

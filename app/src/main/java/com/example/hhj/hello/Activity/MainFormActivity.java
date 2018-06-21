package com.example.hhj.hello.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.hhj.hello.R;
import com.example.hhj.hello.utils.SharePUtil;

public class MainFormActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView mImageView;//工具栏
    private TextView mTextView_tool_bar_middle;
    private TextView mTextView_tool_bar_right;

    private BottomNavigationBar mBottomNavigationBar;//底部导航栏

    private FragmentManager mManager;//三个碎片
    private LeftFragment mLeftFragment;
    private MiddleFragment mMiddleFragment;
    private RightFragment mRightFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);
        mLeftFragment = new LeftFragment();
        mMiddleFragment = new MiddleFragment();
        mRightFragment = new RightFragment();
        if (getSupportActionBar() != null) {//隐藏系统自带标题栏
            getSupportActionBar().hide();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mManager = getSupportFragmentManager();
        replaceFragment(mLeftFragment);

        mImageView = findViewById(R.id.iv_tool_bar_left);
        mTextView_tool_bar_middle = findViewById(R.id.tv_tool_bar_middle);
        mTextView_tool_bar_right = findViewById(R.id.tv_tool_bar_right);

        mTextView_tool_bar_middle.setText("消息");
        mTextView_tool_bar_right.setText("");
        mTextView_tool_bar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//启动添加好友
                if ("添加好友".equals(mTextView_tool_bar_right.getText().toString())) {
                    Intent intent = new Intent(MainFormActivity.this, AddFriendActivity.class);
                    startActivity(intent);
                }
            }
        });

        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_action_sms, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.ic_action_persoon, "联系人"))
                .addItem(new BottomNavigationItem(R.drawable.ic_action_star, "空间"))
                .initialise();//初始化地步导航栏
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {//为底部导航栏设置监听
            @Override
            public void onTabSelected(int position) {
                if (position == 0) {
                    mImageView.setVisibility(View.VISIBLE);
                    mTextView_tool_bar_right.setVisibility(View.INVISIBLE);
                    mTextView_tool_bar_middle.setText("消息");
                    replaceFragment(mLeftFragment);
                } else if (position == 1) {
                    mImageView.setVisibility(View.INVISIBLE);
                    mTextView_tool_bar_middle.setText("联系人");
                    mTextView_tool_bar_right.setVisibility(View.VISIBLE);
                    mTextView_tool_bar_right.setText("添加好友");
                    replaceFragment(mMiddleFragment);
                } else if (position == 2) {
                    mImageView.setVisibility(View.INVISIBLE);
                    mTextView_tool_bar_middle.setText("空间");
                    mTextView_tool_bar_right.setVisibility(View.INVISIBLE);
                    replaceFragment(mRightFragment);
                }
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    @Override
    public void onBackPressed() {//重写返回键功能，因为有DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {//DrawerLayout功能
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            SharePUtil.Remove();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {//替换fragment
        android.support.v4.app.FragmentTransaction transaction = mManager.beginTransaction();
        transaction.replace(R.id.line2, fragment);
        transaction.commit();
    }

}

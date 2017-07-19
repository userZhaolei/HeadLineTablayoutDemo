package com.example.a3559dell.headlinetablayoutdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        sp = getSharedPreferences("user",MODE_PRIVATE);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });

    }

     private void initData() {
         String key = sp.getString("key", null);
         if(key==null){
             List<ChannelBean> list =  new ArrayList<>();
             list.add(new ChannelBean("视频",true));
             list.add(new ChannelBean("育儿",true));
             list.add(new ChannelBean("美食",true));
             list.add(new ChannelBean("旅游",true));
             list.add(new ChannelBean("探索",false));
             list.add(new ChannelBean("财经",false));
             list.add(new ChannelBean("军事",false));
             list.add(new ChannelBean("娱乐",false));
             list.add(new ChannelBean("故事",false));
             list.add(new ChannelBean("热点",false));
             ChannelActivity.startChannelActivity(MainActivity.this,list);
         }else{
             ChannelActivity.startChannelActivity(MainActivity.this,key);
         }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(ChannelActivity.REQUEST_CODE==requestCode&&ChannelActivity.REQUEST_CODE==resultCode){
            String str = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
            sp.edit().putString("key",str).commit();
        }
    }
}

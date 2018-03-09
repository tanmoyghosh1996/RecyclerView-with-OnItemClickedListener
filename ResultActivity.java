package com.example.tanmoy.recyclerviewwithonclicklistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tv_result1 = (TextView)findViewById(R.id.tv_result1);
        TextView tv_result2 = (TextView)findViewById(R.id.tv_result2);
        String result1=getIntent().getExtras().getString("MonthName");
        String result2=getIntent().getExtras().getString("Name");
        tv_result1.setText(result1);
        tv_result2.setText(result2);

    }
}

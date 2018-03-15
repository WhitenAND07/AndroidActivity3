package com.example.jordiblanco.androidactivityopcional3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = (TextView) findViewById(R.id.text2);
        Intent intent = getIntent();
        Uri data = intent.getData();
        if(data !=null){
            text.setText(data.toString());
        }
    }
}

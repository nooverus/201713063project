package com.example.a201713063;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //시작이미지 버튼을 누를시 userinformation 액티비티로 넘어가도록함
    public void startListener(View v) {
        Intent intent = new Intent(getApplicationContext(), userinformation.class);
        startActivity(intent);
    }

    //주석 버튼(책모양 이미지)을 누를시 reference 액티비티로 넘어가도록함
    public void reference(View view) {
        Intent intent = new Intent(getApplicationContext(), reference.class);
        startActivity(intent);
    }
}
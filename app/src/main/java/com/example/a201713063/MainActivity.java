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
    public void startListener(View v) {
        Intent intent = new Intent(getApplicationContext(), userinformation.class);
        startActivity(intent);
    }

    public void reference(View view) {
        Intent intent = new Intent(getApplicationContext(), reference.class);
        startActivity(intent);
    }
}
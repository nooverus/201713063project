package com.example.a201713063;

import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class exercise extends AppCompatActivity {
    Button work;
    ArrayList<String> sports;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        Button insert = (Button)findViewById(R.id.insert);
        work = (Button)findViewById(R.id.work);
        sports=new ArrayList<String>();
        defaultData();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sports);
        ListView list = (ListView)findViewById(R.id.list);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = sports.get(position);
                work.setText(item);
            }
        });


    }

    public void workListener(View target) {

        Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);


        switch(target.getId()) {
            case R.id.work_start:
                    chronometer.start();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                return;
            case R.id.work_stop:
                chronometer.stop();
                return;

            case R.id.work_reset:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                return;
        }

    }
    public void insertListener(View v){
        final  Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("운동 입력");
        Button OK = (Button) dialog.findViewById(R.id.OK);
        Button CANCEL = (Button) dialog.findViewById(R.id.CANCEL);
        final EditText insert_sport = (EditText) dialog.findViewById(R.id.insert_sport);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(insert_sport.getText().toString().trim().length()>0){
                    sports.add(insert_sport.getText().toString());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
                else{
                    Toast.makeText(getApplicationContext(),"다시 입력하시오",Toast.LENGTH_SHORT).show();
                }
            }
        });
        CANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();//커스텀 대화상자를 종료한다
            }
        });
        dialog.show();
    }

    public void delListener(View view){
        sports.remove(sports.size()-1);
        adapter.notifyDataSetChanged();
    }
    public void defaultData()
    {
        sports.clear();
        sports.add("스쿼트");
        sports.add("윗몸일으키기");
        sports.add("버피테스트");
        sports.add("턱걸이");
        sports.add("런지");
        sports.add("플랭크");

    }
}

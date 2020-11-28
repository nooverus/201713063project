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
//리스트뷰로 구성한 액티비티로 각항목을 클릭시 항목의 title이 이름인 버튼이 생성되고 항목을 추가삭제할수있고 리스트뷰 밑은 타이머를 달아 운동시갖을 나타내는 액티비티
public class exercise extends AppCompatActivity {

    Button work;//리스트뷰에서 클릭한 항목의 이름이 들어갈 버튼
    ArrayList<String> sports;//리스트뷰의 항목값들을 담은 Arraylist변수
    ArrayAdapter<String> adapter;//어댑터변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);//exercise layout이 화면에 나타나도록 설정
        Button insert = (Button)findViewById(R.id.insert);
        work = (Button)findViewById(R.id.work);
        sports=new ArrayList<String>();
        defaultData();//sports리스트를 초기화하고 기본값들을 담는 메소드
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sports);//어댑터 설정
        ListView list = (ListView)findViewById(R.id.list);

        list.setAdapter(adapter);//리스트뷰로 나타나게한다
        //각 항목 클릭시 항목의 이름을 버튼에 담도록한다
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = sports.get(position);
                work.setText(item);
            }
        });


    }
//start stop reset으로 구성된 버튼들을 클릭시 event설정
    public void workListener(View target) {

        Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);//운동시간을 나타내기위해 타이머 chronometer 생성


        switch(target.getId()) {
            case R.id.work_start://start의경우 타이머start
                    chronometer.start();
                return;
            case R.id.work_stop://stop시 타이머를 멈추도록한다
                chronometer.stop();
                return;

            case R.id.work_reset://reset의 경우 타이머를 0으로 초기화시키고 멈춘다
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                return;
        }

    }
    //추가버튼을 누를시 리스트뷰에 운동을 추가시킬수있도록한다
    public void insertListener(View v){
        //클릭시 dialog를 생성해 운동이름을 입력받도록하는 대화상자가 나타나도록한다
        final  Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("운동 입력");
        Button OK = (Button) dialog.findViewById(R.id.OK);
        Button CANCEL = (Button) dialog.findViewById(R.id.CANCEL);
        final EditText insert_sport = (EditText) dialog.findViewById(R.id.insert_sport);
        //확인버튼을 누를시 입력한 운동이름을 리스트뷰에 나타나도록한다
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(insert_sport.getText().toString().trim().length()>0){
                    sports.add(insert_sport.getText().toString());//입력값을 sports리스트에 추가
                    adapter.notifyDataSetChanged();//그후 adapter에게 갱신해야된다는것을 알린다
                    dialog.dismiss();//대화상자 종료
                }
                else{
                    Toast.makeText(getApplicationContext(),"다시 입력하시오",Toast.LENGTH_SHORT).show();//입력한 값이 없으면 다시 입력하라는 메시지 출력
                }
            }
        });
        //취소버튼을 누를시 대화상자를 종료시킨다
        CANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();//커스텀 대화상자를 종료한다
            }
        });
        dialog.show();
    }
//삭제버튼을 누를시 가장 밑에 항목을 삭제하고 adpater에게 갱신해야된다는것을 알린다
    public void delListener(View view){
        sports.remove(sports.size()-1);
        adapter.notifyDataSetChanged();
    }
    //sports리스트에 들어갈 기본값
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

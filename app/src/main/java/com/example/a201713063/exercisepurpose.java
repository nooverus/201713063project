package com.example.a201713063;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//userinformation으로부터 입력받은 유저정보를 토대로 기초대사량 유지칼로리 값을 알려주고 운동목적을 선택하도록하는 액티비티
public class exercisepurpose extends AppCompatActivity {
    int purpose;//운동목적값을 담을 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercisepurpose);//exercisepurpose layout을 화면에 나타나도록함

        TextView BMR;//기초대사량값을 담을 textview
        TextView TDEE;//유지칼로리값을 담을 textview

        BMR=(TextView)findViewById(R.id.BMR);
        TDEE=(TextView)findViewById(R.id.TDEE);
        ImageButton manageButton=(ImageButton)findViewById(R.id.manageButton);
        //이전의 액티비티로부터 받아온 intent값들을 담는다
        Intent intent = getIntent();
        //유저의 신체정보값들을 토대로 기초대사량 유지칼로리를 계산하고 화면에 textview로 나타나게한다
        final String NAME = intent.getStringExtra("name");
        int result_BMR=0;
        int result_TDEE=0;
        final int result_weight=intent.getIntExtra("weight",0);

        result_BMR = (int)(66.47 + ((intent.getIntExtra("weight",0))*13.75)+(5*(intent.getIntExtra("height",0)))-(6.76*(intent.getIntExtra("age",0))));
        result_TDEE= (int)(result_BMR*(intent.getDoubleExtra("intensity",0)));
        final int Tdee = result_TDEE;
        BMR.setText(intent.getStringExtra("name")+"님의 기초대사량은"+Integer.toString(result_BMR)+"kcal입니다");
        TDEE.setText(intent.getStringExtra("name")+"님의 유지칼로리는"+Integer.toString(result_TDEE)+"kcal입니다");
        //managebutton 클릭시 manage액티비티로 넘어가도록 설정
        manageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //운동목적을 선택하지않았다면 선택하라는 메시지를 출력한다
                if  (purpose==0) {
                    Toast.makeText(getApplicationContext(),"운동목적을 선택하세요.",Toast.LENGTH_LONG).show();
                    return;
                }
                //각 intent객체에 변수값들을 담고 넘어간다
                Intent intent_manage = new Intent(getApplicationContext(), manage.class);
                intent_manage.putExtra("name",NAME);
                intent_manage.putExtra("TDEE",Tdee);
                intent_manage.putExtra("purpose",purpose);
                intent_manage.putExtra("weight",result_weight);


                startActivity(intent_manage);


            }
        });

    }
//diet leanmassup bulkup등 용어의 대한 설명을 알수있도록 ?이미지버튼을 클릭시 설명이 나타나도록한다.(dialog로 구성)
    public void helpListener(View v) {
             final AlertDialog.Builder helpDialogBuilder = new AlertDialog.Builder(this);

        switch(v.getId()) {
            //diet설명
            case R.id.help1:
                helpDialogBuilder.setMessage("다이어트란 미용이나 건강을 위해 살이 찌지 않도록 먹는 것을 제한하는 일을 말한다.(몸의 지방을 제거하려면 저열량 식이뿐 아니라 운동이 필요하다) ");
                helpDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });

                AlertDialog helpDialog1 = helpDialogBuilder.create();
                helpDialog1.show();
                break;
            //lean mass up설명

            case R.id.help2:
                helpDialogBuilder.setMessage("린매스업이란 지방없이 근육의 사이즈를 키운다는 의미로 체지방률은 줄이고 근육량을 늘리는 방법을 뜻합니다. 그대신 벌크업에 비해 시간을 길게 잡고 해야합니다 ");
                helpDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });

                AlertDialog helpDialog2 = helpDialogBuilder.create();
                helpDialog2.show();
                break;
            //bulkup 설명

            case R.id.help3:
                helpDialogBuilder.setMessage("벌크업이란 체지방과 근육량을 모두 불려 체중과 근육량의 증가를통해 체격을 키우는것을 의미합니다.");
                helpDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });

                AlertDialog helpDialog3 = helpDialogBuilder.create();
                helpDialog3.show();
                break;
        }
    }
//운동목적에 대한 radiobutton listener
    public void onPurposeListener(View target) {
        boolean checked = ((RadioButton) target).isChecked();
//각 버튼에대해서 purpose에 값들을 담는다
        switch(target.getId()) {
            case R.id.radio_diet:
                if(checked)
                    purpose=-450;
                break;
            case R.id.radio_lean_mass:
                if(checked)
                    purpose=200;
                break;
            case R.id.radio_bulk:
                if(checked)
                    purpose=550;

                break;
        }

    }

}

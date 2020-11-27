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

public class exercisepurpose extends AppCompatActivity {
    int purpose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercisepurpose);

        TextView BMR;
        TextView TDEE;

        BMR=(TextView)findViewById(R.id.BMR);
        TDEE=(TextView)findViewById(R.id.TDEE);
        ImageButton manageButton=(ImageButton)findViewById(R.id.manageButton);

        Intent intent = getIntent();

        final String NAME = intent.getStringExtra("name");
        int result_BMR=0;
        int result_TDEE=0;
        final int result_weight=intent.getIntExtra("weight",0);

        result_BMR = (int)(66.47 + ((intent.getIntExtra("weight",0))*13.75)+(5*(intent.getIntExtra("height",0)))-(6.76*(intent.getIntExtra("age",0))));
        result_TDEE= (int)(result_BMR*(intent.getDoubleExtra("intensity",0)));
        final int Tdee = result_TDEE;
        BMR.setText(intent.getStringExtra("name")+"님의 기초대사량은"+Integer.toString(result_BMR)+"kcal입니다");
        TDEE.setText(intent.getStringExtra("name")+"님의 유지칼로리는"+Integer.toString(result_TDEE)+"kcal입니다");

        manageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if  (purpose==0) {
                    Toast.makeText(getApplicationContext(),"운동목적을 선택하세요.",Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent_manage = new Intent(getApplicationContext(), manage.class);
                intent_manage.putExtra("name",NAME);
                intent_manage.putExtra("TDEE",Tdee);
                intent_manage.putExtra("purpose",purpose);
                intent_manage.putExtra("weight",result_weight);


                startActivity(intent_manage);


            }
        });

    }

    public void helpListener(View v) {
             final AlertDialog.Builder helpDialogBuilder = new AlertDialog.Builder(this);

        switch(v.getId()) {
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

    public void onPurposeListener(View target) {
        boolean checked = ((RadioButton) target).isChecked();

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

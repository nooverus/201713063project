package com.example.a201713063;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class userinformation extends AppCompatActivity {
    double intensity;//운동량에 대한 radiobutton 클릭시 들어갈 값에대한 변수
    String imageName,imageAge,imageHeight,imageWeight;//사용자의  신체정보값 변수
    EditText name,age,height,weight;//edittext 신체정보들의 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinformation);//userinformation layout을 화면에 나타나도록한다
        //각 신체정보 edittext 변수를 layout으로부터 가져온다
        name = (EditText)findViewById(R.id.name);//유저의 이름 edittext
        height=(EditText)findViewById(R.id.height);//유저의 키 edittext
        weight=(EditText)findViewById(R.id.weight);//유저의 체중 edittext
        age=(EditText)findViewById(R.id.age);//유저의 나이 edittext

        ImageButton next=(ImageButton) findViewById(R.id.next);//다음 액티비티로 넘어갈 이미지버튼 변수


//preference객체를 생성해 유저의 신체정보값들이 앱을 종료해도 저장되도록한다
        SharedPreferences settings = getSharedPreferences("prefs",0);
        imageName = settings.getString("imageName","");
        imageAge = settings.getString("imageAge","");
        imageHeight = settings.getString("imageHeight","");
        imageWeight = settings.getString("imageWeight","");

        name.setText(imageName);
        age.setText(imageAge);
        height.setText(imageHeight);
        weight.setText(imageWeight);

        //다음(이미지버튼)클릭시 event로 exercisepurpose액티비티로 넘어가면서 유저의 신체정보값들을 intent객체에 담아 보낸다
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //정보를 다입력하지않았다면 toast메시지를 출력해 다입력받도록한다
                if  (name.getText().length()==0||age.getText().length()==0||height.getText().length()==0||weight.getText().length()==0||intensity==0) {
                    Toast.makeText(getApplicationContext(),"모든 값을 다 입력하세요.",Toast.LENGTH_LONG).show();
                    return;
                }
                //신체정보를 intent객체에 담아 다음액티비티로 전송
                String NAME = name.getText().toString();
                int num_age = Integer.parseInt(age.getText().toString());
                int num_height = Integer.parseInt(height.getText().toString());
                int num_weight = Integer.parseInt(weight.getText().toString());


                Intent intent = new Intent(getApplicationContext(), exercisepurpose.class);
                intent.putExtra("name",NAME);
                intent.putExtra("age",num_age);
                intent.putExtra("height",num_height);
                intent.putExtra("weight",num_weight);
                intent.putExtra("intensity",intensity);


                startActivity(intent);


            }
        });

    }
    //운동량에 대한 radiobutton클릭시 event
        public void onRadioButtonClicked(View target) {
        boolean checked = ((RadioButton) target).isChecked();
            //체크된 버튼에 따라 intensity변수에 값을 담는다.
        switch(target.getId()) {
            case R.id.radio_do_not:
                if(checked)
                    intensity=1.2;
                break;
            case R.id.radio_little_work:
                if(checked)
                    intensity=1.4;
                break;
            case R.id.radio_lot:
                if(checked)
                    intensity=1.6;

                break;
            }

        }

        //액티비티 종료시 preference객체에 신체정보값들을 담는다
        @Override
    protected  void onStop(){
        super.onStop();

        SharedPreferences settings = getSharedPreferences("prefs",0);
        SharedPreferences.Editor editor = settings.edit();
            imageName = name.getText().toString();
            imageAge = age.getText().toString();
            imageHeight = height.getText().toString();
            imageWeight = weight.getText().toString();


        editor.putString("imageName",imageName);
        editor.putString("imageAge",imageAge);
        editor.putString("imageHeight",imageHeight);
        editor.putString("imageWeight",imageWeight);
        editor.commit();

        }
}

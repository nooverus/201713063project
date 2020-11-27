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
    double intensity;
    String imageName,imageAge,imageHeight,imageWeight;
    EditText name,age,height,weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinformation);
        name = (EditText)findViewById(R.id.name);
        height=(EditText)findViewById(R.id.height);
        weight=(EditText)findViewById(R.id.weight);
        age=(EditText)findViewById(R.id.age);

        ImageButton next=(ImageButton) findViewById(R.id.next);



        SharedPreferences settings = getSharedPreferences("prefs",0);
        imageName = settings.getString("imageName","");
        imageAge = settings.getString("imageAge","");
        imageHeight = settings.getString("imageHeight","");
        imageWeight = settings.getString("imageWeight","");

        name.setText(imageName);
        age.setText(imageAge);
        height.setText(imageHeight);
        weight.setText(imageWeight);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if  (name.getText().length()==0||age.getText().length()==0||height.getText().length()==0||weight.getText().length()==0||intensity==0) {
                    Toast.makeText(getApplicationContext(),"모든 값을 다 입력하세요.",Toast.LENGTH_LONG).show();
                    return;
                }

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
        public void onRadioButtonClicked(View target) {
        boolean checked = ((RadioButton) target).isChecked();

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

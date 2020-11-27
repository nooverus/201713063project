package com.example.a201713063;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class manage extends AppCompatActivity {
    int result_Kcal;
    int Carbohydrate_kcal,Carbohydrate_g;
    int Protein_kcal,Protein_g;
    int Fat_kcal,Fat_g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage);




        TextView Kcal;
        TextView Carbohydrate;
        TextView Protein;
        TextView Fat;

        Kcal = (TextView)findViewById(R.id.Kcal);
        Carbohydrate = (TextView)findViewById(R.id.Carbohydrate);
        Protein = (TextView)findViewById(R.id.Protein);
        Fat = (TextView)findViewById(R.id.Fat);

        Intent intent = getIntent();

        Protein_kcal=(int)(intent.getIntExtra("weight",0)*1.2*4);
        Fat_kcal=(int)(intent.getIntExtra("TDEE",0)*0.2);
        Carbohydrate_kcal=intent.getIntExtra("TDEE",0)-(Fat_kcal+Protein_kcal);
        Protein_g=(int)(Protein_kcal/4);
        Fat_g=(int)(Fat_kcal/9);
        Carbohydrate_g=(int)(Carbohydrate_kcal/4);

        result_Kcal=(intent.getIntExtra("TDEE",0)+intent.getIntExtra("purpose",0));
        Kcal.setText(intent.getStringExtra("name")+"님의 하루섭취 칼로리는"+Integer.toString(result_Kcal)+"kcal입니다");
        Carbohydrate.setText("섭취해야할 탄수화물: "+Integer.toString(Carbohydrate_kcal)+"kcal-약 "+Integer.toString(Carbohydrate_g)+"g");
        Protein.setText("섭취해야할 단백질: "+Integer.toString(Protein_kcal)+"kcal-약 "+Integer.toString(Protein_g)+"g");
        Fat.setText("섭취해야할 지방: "+Integer.toString(Fat_kcal)+"kcal-약 "+Integer.toString(Fat_g)+"g");

    }

    public void buttonListener(View target) {
        switch(target.getId()) {
            case R.id.sport:
                Intent intent_sport = new Intent(getApplicationContext(), exercise.class);
                startActivity(intent_sport);
                return;

            case R.id.food:
                Intent intent_food = new Intent(getApplicationContext(), diet.class);
                intent_food.putExtra("Total_Kcal",result_Kcal);
                intent_food.putExtra("Total_Carbohydrate",Carbohydrate_kcal);
                intent_food.putExtra("Total_Protein",Protein_kcal);
                intent_food.putExtra("Total_Fat",Fat_kcal);


                startActivity(intent_food);
                return;

        }
    }
}

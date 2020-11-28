package com.example.a201713063;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//커스텀리스트로 구성해 식품의 이름과 칼로리정보를 나타내는 액티비티
public class diet extends AppCompatActivity {
    ListView food_list;//리스트뷰
    //식품 리스트
    String[] titles = {
            "고구마","현미밥","쌀밥","닭가슴살","삼겹살","요거트"
    };
    //각 식품에 대한 칼로리 리스트
    String[] cals ={"86","153","143","109","331","68"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet);//diet layout이 화면에 나타나도록한다
        CustomList adapter = new CustomList(diet.this);
        food_list=(ListView)findViewById(R.id.food_list);
        food_list.setAdapter(adapter);
        //항목클릭시 항목의 이름을 toast메시지로 출력
        food_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),titles[+position],Toast.LENGTH_SHORT).show();
            }
        });


    }
//customlist 구성
    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList(Activity context) {
            super(context,R.layout.foodlist,titles);
            this.context=context;
        }
        @Override
        //각 항목에 리스트값들이 나타나도록한다
       public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.foodlist,null,true);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView cal = (TextView) rowView.findViewById(R.id.cal);
            title.setText(titles[position]);
            cal.setText(cals[position]);
            return rowView;
        }

    }

}

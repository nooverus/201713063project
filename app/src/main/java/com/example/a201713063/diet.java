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

public class diet extends AppCompatActivity {
    ListView food_list;
    String[] titles = {
            "고구마","현미밥","쌀밥","닭가슴살","삼겹살","요거트"
    };
    String[] cals ={"86","153","143","109","331","68"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet);
        CustomList adapter = new CustomList(diet.this);
        food_list=(ListView)findViewById(R.id.food_list);
        food_list.setAdapter(adapter);
        food_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),titles[+position],Toast.LENGTH_SHORT).show();
            }
        });


    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList(Activity context) {
            super(context,R.layout.foodlist,titles);
            this.context=context;
        }
        @Override
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

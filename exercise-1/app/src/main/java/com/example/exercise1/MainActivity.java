package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> carlist = new ArrayList<>();

    private static void getcars(){
        carlist.add("BMW");
        carlist.add("Alfa Romeo");
        carlist.add("Corvette");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getcars();

        final ListView myListView = (ListView)findViewById(R.id.listview);
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, carlist);
        myListView.setAdapter(aa);

        Button addCar = (Button) findViewById(R.id.add);
        final EditText line = (EditText) findViewById(R.id.editText);

        addCar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0){
                String mark = line.getText().toString();
                carlist.add(mark);
                myListView.setAdapter(aa);
            }
        });

        Button editCar = (Button) findViewById(R.id.edit);

        editCar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0){
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                       String selectedCar = carlist.get(position);
                       Toast.makeText(getApplicationContext(), "Car Selected : " + selectedCar, Toast.LENGTH_LONG).show();
                       line.setText(selectedCar);
                   }
                });
            }
        });

        Button removeCar = (Button) findViewById(R.id.remove);

        removeCar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0){
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                        String selectedCar = carlist.get(position);
                        Toast.makeText(getApplicationContext(), "Car Removed : " + selectedCar, Toast.LENGTH_LONG).show();
                        carlist.remove(position);
                        myListView.setAdapter(aa);
                    }
                });
            }
        });

        Button secondActivity = (Button) findViewById(R.id.secondActivity);

        secondActivity.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0){
                Toast.makeText(getApplicationContext(), "Second Activity Selected!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.muayad.maxhit;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    DBAdapter dbHandler;
    TextView data;

    ListView userList;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    viewData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        data = findViewById(R.id.data);
        dbHandler = new DBAdapter(this,  null, null, 1);

        userList = findViewById(R.id.list);
        printDatabase();
    }

    private void viewData() {
        Cursor cursor = DBAdapter.viewData();

        if(cursor.getCount() == 0 ) {
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();

        }else {
            while(cursor.moveToNext()) {
                listItem.add(cursor.getString(1));
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            data.setAdapter(adapter);
        }
    }

    public void printDatabase() {
        String dbString = dbHandler.databaseToString();
        data.setText(dbString);

    }
}

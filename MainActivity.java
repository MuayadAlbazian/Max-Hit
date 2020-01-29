package com.example.muayad.maxhit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int weight;
    private static String reps;
    private static int bodyWeight;
    EditText edit1;
    EditText edit2;
    DBAdapter myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NumberPicker np2 = findViewById(R.id.numberPicker2);
        np2.setMinValue(20);
        np2.setMaxValue((100));
        np2.setValue(40);
        np2.setOnValueChangedListener(onValueChangeListener2);
        np2.setFormatter(formatter);

        NumberPicker np = findViewById(R.id.numberPicker);
        np.setMinValue(100);
        np.setMaxValue(400);
        np.setValue(170);
        np.setOnValueChangedListener(onValueChangeListener);


        EditText hitText = (EditText) findViewById(R.id.set5);
        final Button calcButton = findViewById(R.id.button2);
        final Button historyButton = findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHistory();
            }
        });
        calcButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                displayWeight();
            }
        });

        //openDB();
    }

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(MainActivity.this,
                            "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);

                    //Doing the work
                    bodyWeight = numberPicker.getValue();
                    setBodyWeight(bodyWeight);
                }
            };

    NumberPicker.OnValueChangeListener onValueChangeListener2 =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(MainActivity.this,
                            "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);

                    //Doing the work
                    weight = numberPicker.getValue();
                    setWeight(weight);
                }
            };

    NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
        @Override
        public String format(int value) {
            int temp = value * 5;
            return "" + temp;
        }
    };



    public void startHistory() {
        Intent nextPage = new Intent(this, History.class);
        startActivity(nextPage);

    }
    public void displayText(String message) {
        TextView textView = (TextView) findViewById(R.id.textDisplay);
        textView.setText(message);
    }


    public void displayWeight() {
        /*edit2 = findViewById(R.id.editText);
        bodyWeight = edit2.getText().toString();
        edit1   = (EditText)findViewById(R.id.editText2);
        weight = edit1.getText().toString();

        if(weight.isEmpty()) {
            edit1.setText("Please enter a number.");
        }
        else if(bodyWeight.isEmpty()) {
            edit2.setText("Enter your body weight!");
        }
        else {*/
            Intent nextPage = new Intent(this, weightDisplay.class);
            startActivity(nextPage);

            //setWeight(weight);
            //setBodyWeight(bodyWeight);

    }


    public void setWeight(int w) {
        this.weight = w;
    }
    public static int getWeight() {
        return weight * 5;
    }
    public void setReps(String r) {
        this.reps = r;
    }
    public static String getReps() {
        return reps;
    }
    public void setBodyWeight(int x) {
        this.bodyWeight = x;
    }
    public static int getBodyWeight() {
        return bodyWeight;
    }

}

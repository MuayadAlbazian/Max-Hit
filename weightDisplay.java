package com.example.muayad.maxhit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class weightDisplay extends AppCompatActivity {

    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    String theDate = localDate.getMonthValue() + "/" + localDate.getDayOfMonth() + "/" + localDate.getYear();
    private int max;
    private int reps;


    DBAdapter myDB;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_display);

        final Button bench = findViewById(R.id.benchRoutine);
        bench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRoutine();
            }
        });


        /*NumberPicker np2 = findViewById(R.id.numberPicker2);
        np2.setMinValue(135);
        np2.setMaxValue((300));
        np2.setValue(MainActivity.getWeight());
        np2.setOnValueChangedListener(onValueChangeListener2);*/

        NumberPicker np = findViewById(R.id.numberPicker);
        np.setMinValue(1);
        np.setMaxValue((4));
        np.setValue(1);
        np.setOnValueChangedListener(onValueChangeListener);
        TextView hitText = (TextView) findViewById(R.id.set5);

        final int weight = MainActivity.getWeight();
        final int bodyWeight = MainActivity.getBodyWeight();
        //final int bodyWeightInt = Integer.parseInt(bodyWeight);
        int number = weight;
        if(number == 0) {
            System.out.println("Please enter a proper number");
        }

        int maxHit1 = (int) Math.round((.033 * number * 5) + number); //equation step 1
        setMax(maxHit1);
        final int maxHit = (int) Math.round((getMax()/(1*.033 + 1)));
        setMax(maxHit);
        System.out.println(Math.round(maxHit));

        String max = Integer.toString(maxHit);
        hitText.setText(max);
        hitText.setTextSize(56);


        final Button save = findViewById(R.id.saveHit);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDB((Integer)maxHit, theDate, bodyWeight);
            }
        });





    }
    public void startRoutine() {
        Intent nextPage = new Intent(this, BenchRoutine.class);
        startActivity(nextPage);
    }


    NumberPicker.OnValueChangeListener onValueChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(weightDisplay.this,
                            "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);

                    //Doing the work
                    TextView hitText = (TextView) findViewById(R.id.set5);
                    TextView statement = (TextView) findViewById(R.id.textView2);

                    final int weight = MainActivity.getWeight();
                    //int number = Integer.parseInt(weight);
                    int value = numberPicker.getValue();
                    final int maxHit = (int) Math.round((getMax()/(value*.033 + 1)));
                    String max = Integer.toString(maxHit);
                    hitText.setText(max);
                    setReps(numberPicker.getValue());

                    switch(value) {
                        case 1:
                            statement.setText("Your 1RM is: ");
                            break;
                        case 2:
                            statement.setText("Your 2RM is: ");
                            break;
                        case 3:
                            statement.setText("Your 3RM is: ");
                            break;
                        case 4:
                            statement.setText("Your 4RM is: ");
                            break;

                    }

                }
            };

   /* NumberPicker.OnValueChangeListener onValueChangeListener2 =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(weightDisplay.this,
                            "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);

                    //Doing the work
                    EditText hitText = (EditText) findViewById(R.id.textView);
                    int value = numberPicker.getValue();
                    int maxHit1 = (int) Math.round((.033 * value * 5) + value); //equation step 1
                    setMax(maxHit1);
                    final int maxHit = (int) Math.round((getMax()/(getReps()*.033 + 1)));
                    System.out.println(Math.round(maxHit));

                    String max = Integer.toString(maxHit);
                    hitText.setText(max);




                }
            };*/

    public void addToDB(int max, String date, int weight) {
        myDB = new DBAdapter(this,  null, null, 1);
        myDB.insertRow(max, date, weight);
    }

    public int getMax() {
        return max;
    }
    public void setMax(int x) {
        this.max = x;
    }
    public int getReps() {
        return reps;
    }

    public void setReps(int x) {
        this.reps = x;
    }


}

package com.example.muayad.maxhit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BenchRoutine extends AppCompatActivity {

    TextView set1;
    TextView set2;
    TextView set3;
    TextView set4;
    TextView set5;


    int count;

    int weights[] = {(int) (MainActivity.getWeight()*.60), (int) (MainActivity.getWeight()*.80), (int) (MainActivity.getWeight()*.90), MainActivity.getWeight()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bench_routine);

        for(int i = 0; i < weights.length; i++) {
            if(weights[i] % 5 != 0) {
                count = weights[i] % 5;
                weights[i] = weights[i] + 5 - count;
            }
        }
        int setOne = weights[0];
        String setOneS = Integer.toString(setOne);
        int setTwo = weights[1];
        String setTwoS = Integer.toString(setTwo);
        int set34 = weights[2];
        String set34S = Integer.toString(set34);
        int setFive = weights[3];
        String set5S = Integer.toString(setFive);

        set1 = (TextView) findViewById(R.id.set1);
        set2 = (TextView) findViewById(R.id.set2);
        set3 = (TextView) findViewById(R.id.set3);
        set4 = (TextView) findViewById(R.id.set4);
        set5 = (TextView) findViewById(R.id.set5);

          set1.setText("Warm-Up Set: "+ setOneS + " - 10 Reps");
          set2.setText("Set 1: " + setTwoS + " - 6 Reps");
          set3.setText("Set 2: " + set34S + " - 5 Reps");
          set4.setText("Set 3: " + setFive + " - 5 Reps");
          set5.setText("Set 4: " + setFive + " - 5 Reps");

    }
}

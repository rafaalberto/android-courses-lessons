package com.example.rafael.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int THREE_POINTS = 3;
    private static final int TWO_POINTS = 2;
    private static final int ONE_POINT = 1;
    private static final int ZERO_POINT = 0;

    int points = ZERO_POINT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setThreePoints(View view) {
        points = points + THREE_POINTS;
        displayPoints(points);
    }

    public void setTwoPoints(View view) {
        points = points + TWO_POINTS;
        displayPoints(points);
    }

    public void setFreeThrow(View view) {
        points = points + ONE_POINT;
        displayPoints(points);
    }

    public void reset(View view) {
        points = ZERO_POINT;
        displayPoints(ZERO_POINT);
    }

    private void displayPoints(int points) {
        TextView textViewPoints = (TextView) findViewById(R.id.text_view_points);
        textViewPoints.setText(String.valueOf(points));
    }
}

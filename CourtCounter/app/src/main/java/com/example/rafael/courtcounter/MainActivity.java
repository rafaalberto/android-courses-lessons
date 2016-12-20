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

    int points[] = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setThreePointsTeamA(View view) {
        points[0] = points[0] + THREE_POINTS;
        displayPointsTeamA(points[0]);
    }

    public void setTwoPointsTeamA(View view) {
        points[0] = points[0] + TWO_POINTS;
        displayPointsTeamA(points[0]);
    }

    public void setFreeThrowTeamA(View view) {
        points[0] = points[0] + ONE_POINT;
        displayPointsTeamA(points[0]);
    }

    public void setThreePointsTeamB(View view) {
        points[1] = points[1] + THREE_POINTS;
        displayPointsTeamB(points[1]);
    }

    public void setTwoPointsTeamB(View view) {
        points[1] = points[1] + TWO_POINTS;
        displayPointsTeamB(points[1]);
    }

    public void setFreeThrowTeamB(View view) {
        points[1] = points[1] + ONE_POINT;
        displayPointsTeamB(points[1]);
    }

    public void reset(View view) {
        points[0] = ZERO_POINT;
        points[1] = ZERO_POINT;
        displayPointsTeamA(ZERO_POINT);
        displayPointsTeamB(ZERO_POINT);
    }

    private void displayPointsTeamA(int points) {
        TextView textViewPoints = (TextView) findViewById(R.id.text_view_points_team_a);
        textViewPoints.setText(String.valueOf(points));
    }

    private void displayPointsTeamB(int points) {
        TextView textViewPoints = (TextView) findViewById(R.id.text_view_points_team_b);
        textViewPoints.setText(String.valueOf(points));
    }
}

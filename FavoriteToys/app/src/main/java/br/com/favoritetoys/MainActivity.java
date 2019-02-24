package br.com.favoritetoys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> toys = Arrays.asList(
                "New York",
                "São Paulo",
                "Mexico City",
                "Paris",
                "Rome",
                "London",
                "Toronto",
                "Tokyo",
                "Madrid",
                "Lisbon",
                "Berlin",
                "Buenos Aires",
                "Bogota",
                "Santiago",
                "Porto");
        StringBuilder builder = new StringBuilder();
        for(String toy : toys) {
            builder.append(toy).append("\n\n");
        }

        TextView textViewToys = findViewById(R.id.text_view_toys);
        textViewToys.setText(builder.toString());
    }
}

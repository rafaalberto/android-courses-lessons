package ra.com.br.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadViews();
    }

    private void loadViews() {
        TextView textViewNumbers = (TextView) findViewById(R.id.text_view_numbers);
        textViewNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NumbersActivity.class));
            }
        });

        TextView textViewFamily = (TextView) findViewById(R.id.text_view_family);
        textViewFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FamilyActivity.class));
            }
        });

        TextView textViewColors = (TextView) findViewById(R.id.text_view_colors);
        textViewColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ColorsActivity.class));
            }
        });

        TextView textViewPhrases = (TextView) findViewById(R.id.text_view_phrases);
        textViewPhrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PhrasesActivity.class));
            }
        });
    }
}

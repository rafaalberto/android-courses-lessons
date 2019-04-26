package br.com.caraoucoroa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private ImageView imageViewCoin;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imageViewCoin = findViewById(R.id.imageViewCoin);
        buttonBack = findViewById(R.id.buttonBack);

        Bundle data = getIntent().getExtras();
        int number = data.getInt("number");

        if(number == 0) {
            imageViewCoin.setImageResource(R.drawable.moeda_cara);
        } else {
            imageViewCoin.setImageResource(R.drawable.moeda_coroa);
        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

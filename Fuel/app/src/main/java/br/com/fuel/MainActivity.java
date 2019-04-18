package br.com.fuel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final double LIMIT_PERCENTUAL = 0.7;

    private EditText editTextEthanolPrice;
    private EditText editTextGasolinePrice;
    private TextView textViewResult;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadComponents();
    }

    private void loadComponents() {
        editTextEthanolPrice = findViewById(R.id.edit_text_ethanol_price);
        editTextGasolinePrice = findViewById(R.id.edit_text_gasoline_price);
        textViewResult = findViewById(R.id.text_view_result);
        buttonCalculate = findViewById(R.id.button_price);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allFieldsAreValid()) {
                    calculate();
                }
            }
        });
    }

    private boolean allFieldsAreValid() {
        if(editTextEthanolPrice == null || editTextEthanolPrice.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Preço do Álcool deve ser informado!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(editTextGasolinePrice == null || editTextGasolinePrice.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Preço da Gasolina deve ser informada!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void calculate() {
        Double ethanolPrice = Double.valueOf(editTextEthanolPrice.getText().toString());
        Double gasolinePrice = Double.valueOf(editTextGasolinePrice.getText().toString());

        Double result = ethanolPrice / gasolinePrice;
        if(result >= LIMIT_PERCENTUAL) {
            textViewResult.setText("Melhor utilizar Gasolina!");
        }else{
            textViewResult.setText("Melhor utilizar Álcool!");
        }
    }
}

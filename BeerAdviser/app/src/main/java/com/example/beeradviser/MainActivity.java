package com.example.beeradviser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BeerExpert expert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.FindBeer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView brands =(TextView) findViewById(R.id.textView);
                Spinner color = (Spinner) findViewById(R.id.color);
                String beertype = String.valueOf(color.getSelectedItem());
                //brands.setText(beertype);
                //get recomendation from the beer Expert class
                List<String> brandlist = expert.getBrands(beertype);
                StringBuilder brandFormatted = new StringBuilder();
                for(String brand:brandlist){
                    brandFormatted.append(brand).append("\n");
                }
                //display the beer
                brands.setText(brandFormatted);
            }
        });
    }
}

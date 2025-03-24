package com.example.michal.semestralka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button oAplikaciiButton;
    private Button kategoriaButton;
    private Button buttonVkladananieVtipu;


    /**
     * Metoda volana pri vziku aktivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oAplikaciiButton = findViewById(R.id.oAplikaciiButton);
        oAplikaciiButton.setOnClickListener(this);

        kategoriaButton = findViewById(R.id.kategorieButton);
        kategoriaButton.setOnClickListener(this);

        buttonVkladananieVtipu = findViewById(R.id.VlastneVtipy);
        buttonVkladananieVtipu.setOnClickListener(this);


    }


    /**
     * Metoda sluziaca na zistenie kliknutej polozky
     *
     * @param v view
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.oAplikaciiButton:
                Intent intent = new Intent(this, OAplikacii.class);
                startActivity(intent);
                break;

            case R.id.kategorieButton:
                Intent intent2 = new Intent(this, Kategoria.class);
                startActivity(intent2);

            case R.id.VlastneVtipy:
                Intent intent3 = new Intent(this, VkladanieVtipov.class);
                startActivity(intent3);
        }

    }
}

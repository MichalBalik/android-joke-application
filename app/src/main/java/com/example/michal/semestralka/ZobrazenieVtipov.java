package com.example.michal.semestralka;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ZobrazenieVtipov extends AppCompatActivity implements View.OnClickListener {
    private String nazovTabulky;
    private ArrayList<String> vysledok;
    private String kategoriaVtipov;
    private int pozicia;
    private TextView ZobrazenieVtipu;

    /**
     * Metoda oncreate volana pri vzniku aktivity
     *
     * @param savedInstanceState - savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zobrazenie_vtipov);
        Intent i = getIntent();
        nazovTabulky = Databaza.getNazovTabulky();
        vysledok = new ArrayList<String>();
        pozicia = 0;
        ZobrazenieVtipu = (TextView) findViewById(R.id.poleDat);

        kategoriaVtipov = i.getStringExtra("kategoria");
        setTitle(kategoriaVtipov);

        ZobrazenieVtipu.setMovementMethod(new ScrollingMovementMethod());


        queryDB();
        displayResultList(0);
        Button dalsiVtip = (Button) findViewById(R.id.buttonVtipDopredu);
        dalsiVtip.setOnClickListener(this);

        Button predosliVtip = (Button) findViewById(R.id.buttonVtipDozadu);
        predosliVtip.setOnClickListener(this);
    }

    /**
     * Metoda sluziaca na query vtipov z databazy
     */
    private void queryDB() {
        SQLiteDatabase newDB = null;
        try {
            Databaza dbHelper = new Databaza(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();

            Cursor c = newDB.rawQuery("SELECT text, kategoria, oblubene FROM "
                    + nazovTabulky + " where kategoria ='" + kategoriaVtipov + "'", null);


            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        String text = c.getString(c.getColumnIndex("text"));
                        //int oblubene = c.getInt(c.getColumnIndex("oblubene"));
                        vysledok.add(text);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(),
                    "Chyba databazy");
        } finally {
            if (newDB != null)
                newDB.close();


        }

    }

    /**
     * Metoda sluziaca na zobrazenie vtipu
     *
     * @param poziciaVtipu - pozicia vtipu v arrayliste
     */
    private void displayResultList(int poziciaVtipu) {

        if (poziciaVtipu >= 0 && poziciaVtipu <= vysledok.size() - 1) {

            ZobrazenieVtipu.setText(vysledok.get(poziciaVtipu));
        }

    }

    /**
     * Metoda na zistenie kliknutia tlacidiel
     *
     * @param v view
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonVtipDopredu:
                int pom = pozicia + 1;
                if (pom < vysledok.size()) {
                    displayResultList(++pozicia);
                    Toast.makeText(this, "Vtip " + ++pom + "/" + vysledok.size(), Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.buttonVtipDozadu:
                int pom2 = pozicia - 1;
                if (pom2 >= 0) {
                    displayResultList(--pozicia);
                    Toast.makeText(this, "Vtip " + ++pom2 + "/" + vysledok.size(), Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }
}

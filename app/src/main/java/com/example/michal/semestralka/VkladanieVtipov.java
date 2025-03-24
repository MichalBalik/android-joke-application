package com.example.michal.semestralka;

import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;


public class VkladanieVtipov extends AppCompatActivity implements View.OnClickListener, Serializable {

    private ArrayList<String> zoznamVtipov;
    private Button buttonUloz;
    private Button posunVtipudole;
    private Button posunVtipuhore;
    private Button buttonVymaz;

    private EditText poleVkladanieDat;
    private ListIterator<String> it;

    /**
     * On create metoda volana pri vzniku aktivity
     *
     * @param savedInstanceState - savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vkladanie_vtipov);

        zoznamVtipov = new ArrayList<>();
        buttonUloz = findViewById(R.id.buttonUloz);
        buttonUloz.setOnClickListener(this);

        posunVtipudole = findViewById(R.id.buttonPosunDole);
        posunVtipudole.setOnClickListener(this);

        posunVtipuhore = findViewById(R.id.buttonPosunHore);
        posunVtipuhore.setOnClickListener(this);

        buttonVymaz = findViewById(R.id.buttonVymaz);
        buttonVymaz.setOnClickListener(this);

        poleVkladanieDat = findViewById(R.id.poleVkladanieDat);

        it = zoznamVtipov.listIterator();
        deserializacia();

    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        serializaciaUdajov();
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        deserializacia();
    }

    /**
     * Metoda sluziaca na vkladanie vtiou do arraylistu spolu s overenim zadania
     */
    public void ulozVtip() {

        if (poleVkladanieDat.length() != 0 && !porovnajStringyDatabazou(poleVkladanieDat.getText().toString())) {
            zoznamVtipov.add(poleVkladanieDat.getText().toString());
            it = zoznamVtipov.listIterator();
            Toast.makeText(this, "Vtip uspense ulozeny", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Nespravne zadany vtip", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Metoda sluziaca na zistenie kliknutia buttnoch
     *
     * @param v - view
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonUloz:
                ulozVtip();


                break;


            case R.id.buttonPosunHore:
                zobrazDalsiVtip();
                break;

            case R.id.buttonPosunDole:
                zobrazPredchadzajuciVtip();
                break;
            case R.id.buttonVymaz:
                vymazVtip(poleVkladanieDat.getText().toString());
                break;
        }

    }

    /**
     * Metoda sluziaca na serializaciu dat z arraylistu
     */
    public void serializaciaUdajov() {
        try {
            FileOutputStream subor = openFileOutput("data.ser", Context.MODE_PRIVATE);
            ObjectOutputStream zapisovac = new ObjectOutputStream(subor);


            zapisovac.writeObject(zoznamVtipov);
            zapisovac.close();
            subor.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metoda sluziaca na nacitanie - deserializaciu dat z interneho uloziska
     */
    public void deserializacia() {


        try {

            FileInputStream subor = openFileInput("data.ser");
            ObjectInputStream citac = new ObjectInputStream(subor);
            zoznamVtipov
                    = (ArrayList) citac.readObject();
            it = zoznamVtipov.listIterator();

            citac.close();
            subor.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * Metoda sluziaca na vymazanie vtipu z  arraylistu
     *
     * @param vymazanie String text vtipu ktory sa ma vymazat
     */
    private void vymazVtip(String vymazanie) {
        if (porovnajStringyDatabazou(vymazanie)) {
            zoznamVtipov.remove(vymazanie);

            it = zoznamVtipov.listIterator();
            Toast.makeText(this, "Vtip uspesne vymazany", Toast.LENGTH_SHORT).show();
            poleVkladanieDat.setText("");
        }


    }

    /**
     * Metoda sluziaca na zobrazenie predosleho vtipu
     */
    private void zobrazPredchadzajuciVtip() {
        if (it.hasPrevious()) {
            poleVkladanieDat.setText(it.previous());
        }

    }


    /**
     * Metoda sluziaca na zobrazenie dalsieho vtipu
     */
    private void zobrazDalsiVtip() {


        if (it.hasNext()) {

            poleVkladanieDat.setText(it.next());
        }


    }

    /**
     * Metoda sluziaca na porovnanie textu s ulozenymi vtipmy
     *
     * @param naPorovnanie - text na porovnanie
     * @return true ak sa text nasiel v arayliste inak false
     */
    public boolean porovnajStringyDatabazou(String naPorovnanie) {
        for (String prehladavane : zoznamVtipov) {

            if (prehladavane.equals(naPorovnanie)) {
                return true;
            }

        }

        return false;
    }


}

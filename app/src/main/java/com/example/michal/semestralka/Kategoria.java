package com.example.michal.semestralka;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Kategoria extends ListActivity {

    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] arrayKategorii = new String[]{"Alkohol", "Blondinky", "Chuck Norris",
                "Politika"};

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrayKategorii);
        setListAdapter(adapter);


    }

    /**
     * Metoda sluziaca na zistenie kliknutia polozky
     *
     * @param l        - listview
     * @param v        - view
     * @param position - pozicia kliknutia
     * @param id       - id
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Toast.makeText(this, l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();


        adapter.notifyDataSetChanged();
        Intent intent2 = new Intent(this, ZobrazenieVtipov.class);
        intent2.putExtra("kategoria", l.getItemAtPosition(position).toString());
        startActivity(intent2);


    }


}


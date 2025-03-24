package com.example.michal.semestralka;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Databaza extends SQLiteOpenHelper {

    private static final String NAZOV_DATABAZY = "DATABAZA_VTIPOV";
    private static final String NAZOV_TABULKY = "Vtipy";
    public static final int version = '1';

    /**
     * Konstruktor pomocnej triedy databaza sluziaci na tvorbu sqlite databazy
     *
     * @param context - obsah
     */
    public Databaza(Context context) {
        super(context, NAZOV_DATABAZY, null, version);
    }

    /**
     * Metoda onCreate sluziaca na tvorbu databazy a vkladanie udajov
     *
     * @param db - databaza
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "
                + NAZOV_TABULKY
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, text VARCHAR, kategoria VARCHAR, oblubene INT);");

        ContentValues values = new ContentValues();

        values.put("text", "Opitý muž sa vracia domov. Žena ho už čaká, s nahnevaným pohľadom ukazuje na hodinky. Muž na to:\n" +
                "- Aké veľké haló okolo toho... hodinky. Keď sa môj otec vracal domov, mama ukazovala na kalendár!");
        values.put("kategoria", "Alkohol");
        values.put("oblubene", 1);
        db.insert(NAZOV_TABULKY, null, values);

        values.clear();
        values.put("text", "Vraví hosť čašníkovi:\n" +
                "- Prosím si jedno pivo!\n" +
                "- Musíte počkať, najskôr dámy...\n" +
                "- A čo ste krčma, alebo záchranný čln?!");
        values.put("kategoria", "Alkohol");
        values.put("oblubene", 0);

        db.insert(NAZOV_TABULKY, null, values);


        values.clear();
        values.put("text", "Mišo príde zo žúrovačky domov opitý. Žena keď ho uvidí, skríkne na neho:\n" +
                "- Hovädo opité, vypadni tam, kde si sa tak zrúbal. \n" +
                "Mišo si horko ťažko nájde mobil a volá: \n" +
                "- Fero, príďte po mňa, žena ma pustila.");
        values.put("kategoria", "Alkohol");
        values.put("oblubene", 0);

        db.insert(NAZOV_TABULKY, null, values);


        values.clear();
        values.put("text", "Príde bača do obchodu a pýta sa:\n" +
                "- Máte také tie podložky na jógu?\n" +
                "Predavačka:\n" +
                "- Nie.\n" +
                "Bača:\n" +
                "- A máte také tie veľké gule, tie fitlopty?\n" +
                "Predavačka:\n" +
                "- Nie mojko, my tu predávame alkohol.\n" +
                "Bača:\n" +
                "- Hmm, tak dve fľaše borovičky, ale Boh vie, že som chcel cvičiť.");
        values.put("kategoria", "Alkohol");
        values.put("oblubene", 0);

        db.insert(NAZOV_TABULKY, null, values);


        values.clear();
        values.put("text", "- Od kedy veria blondínky na lásku na prvý pohľad?\n" +
                "- Keď sa prvýkrát uvidia v zrkadle.");
        values.put("kategoria", "Blondinky");
        values.put("oblubene", 1);

        db.insert(NAZOV_TABULKY, null, values);


        values.clear();
        values.put("text", "- Prečo si blondínka ráno oblieka skafander?\n" +
                "- V rádiu hlásili vysoký tlak.");
        values.put("kategoria", "Blondinky");
        values.put("oblubene", 1);

        db.insert(NAZOV_TABULKY, null, values);


        values.clear();
        values.put("text", "Chuck Norris je taký rýchly, že musí brzdiť, aby do seba nenarazil.");
        values.put("kategoria", "Chuck Norris");
        values.put("oblubene", 1);
        db.insert(NAZOV_TABULKY, null, values);

        values.clear();
        values.put("text", "Chuck Norris došiel na koniec kruhového objazdu.");
        values.put("kategoria", "Chuck Norris");
        values.put("oblubene", 1);
        db.insert(NAZOV_TABULKY, null, values);


        values.clear();
        values.put("text", "Peniaze oslobodzujú človeka. V parlamente je najvyšší výskyt slobody na 1 m2.");
        values.put("kategoria", "Politika");
        values.put("oblubene", 1);
        db.insert(NAZOV_TABULKY, null, values);

        values.clear();
        values.put("text", "Pravda oči kole. Asi preto sa naši politici neboja slepoty.\n");
        values.put("kategoria", "Politika");
        values.put("oblubene", 1);
        db.insert(NAZOV_TABULKY, null, values);


    }

    /**
     * Metoda volana pri update udajov v tabulke
     *
     * @param db         - databaza
     * @param oldVersion - cislo starej verzie
     * @param newVersion - cislo novej verzie
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NAZOV_TABULKY);
        onCreate(db);
    }

    /**
     * @return String nazov tabulky
     */
    public static String getNazovTabulky() {
        return NAZOV_TABULKY;
    }
}



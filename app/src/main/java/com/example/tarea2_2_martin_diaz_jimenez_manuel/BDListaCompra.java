package com.example.tarea2_2_martin_diaz_jimenez_manuel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.SQLException;

public class BDListaCompra extends SQLiteOpenHelper {

    private Context context;
    //Cadena de creación de la tabla. Añadir los campos necesarios
    private String CREAR_TABLA="CREATE TABLE productos (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "producto VARCHAR," +
            "cantidad INTEGER," +
            "precio FLOAT )";
    //Cadena de eliminación de la tabla. Falta por indicar el nombre de la tabla
    private String ELIMINAR_TABLA="DROP TABLE IF EXISTS productos";

    public BDListaCompra(Context context) {
        super(context, "BD", null, 1);
        this.context = context;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        try {
            db.execSQL(CREAR_TABLA);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        try {
            db.execSQL(ELIMINAR_TABLA);
            onCreate(db);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
        }
    }

}


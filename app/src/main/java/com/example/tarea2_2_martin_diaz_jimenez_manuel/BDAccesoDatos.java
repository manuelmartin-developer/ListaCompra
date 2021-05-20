package com.example.tarea2_2_martin_diaz_jimenez_manuel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class BDAccesoDatos{
    private Context contexto;
    private BDListaCompra bdLista;
    private SQLiteDatabase bd;


    public BDAccesoDatos(Context c) {
        this.contexto = c;
        bdLista=new BDListaCompra(c);
    }

    //Método de inserción. Indicar el tipo de datos devuelto
    public long insertar(Producto p){
        //Insertar el código de inserción aquí
        /**Creamos un acceso para la escritura con los objetos ya declarados**/
        bd = bdLista.getWritableDatabase();
        /**Creamos un registro especificando el campo y su valor**/
        ContentValues registro = new ContentValues();
        registro.put("producto", p.getProducto());
        registro.put("cantidad", p.getCantidad());
        registro.put("precio", p.getPrecio());
        long resultado = bd.insert("productos",null,registro);

        //Fin código de inserción
         bd.close();
        //Añadir cláusula return
        return resultado;

    }

    //Método de consulta. Indicar el tipo de datos devuelto
    public void consultar(ArrayList<Producto> productos){
        // Creamos acceso para lectura
        bd=bdLista.getReadableDatabase();
        // Limpiamos
        productos.clear();
        //Rellenar un ArrayList con el resultado de la consulta
        String[] campos = {"id", "producto", "cantidad", "precio"};
        Cursor cursor = bd.query("productos", campos, null, null, null, null, null);
        /**Añadimos el producto a nuestro ArrayList**/
        while (cursor.moveToNext()){
            Producto p = new Producto(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getFloat(3));
            productos.add(p);
        }
        //Fin código de consulta
        bd.close();

    }

    public long eliminar(Producto p){
        bd=bdLista.getWritableDatabase();
        //Insertar el código de eliminación aquí
        /**Creamos una consulta por la ID del producto y borramos el registro**/
        long resultado = bd.delete("productos", "id=" + p.getID(), null);


        //Fin código de consulta
        bd.close();
        //Añadir cláusula return
        return resultado;
    }
}
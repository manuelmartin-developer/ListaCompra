package com.example.tarea2_2_martin_diaz_jimenez_manuel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Declaración de variables y objetos a enlazar
    private ListView listado_productos;
    EditText producto, cantidad, precio;
    BDAccesoDatos basedatos;
    ArrayList<Producto> productos = new ArrayList<>();
    ArrayAdapter<Producto> adaptador;
    Boolean borrarRegistro = false;
    String id_detalle, producto_detalle, cantidad_detalle, precio_detalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basedatos = new BDAccesoDatos(this);

        // Enlazamos en objeto
        listado_productos = findViewById(R.id.listado_productos);


        // Obtenemos los productos de la base de datos para poder crear el adaptador
        basedatos.consultar(productos);
        adaptador = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,productos);
        listado_productos.setAdapter(adaptador);

        /******************** PULSACIÓN CORTA SOBRE ELEMENTO DEL LISTVIEW - CONSULTA PRODUCTO *********************/

            listado_productos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Asociamos las variables a los objetos a pasar a la siguiente activity
                    Producto p = (Producto)parent.getItemAtPosition(position);
                    id_detalle = String.valueOf(p.getID());
                    producto_detalle = p.getProducto();
                    cantidad_detalle = String.valueOf(p.getCantidad());
                    precio_detalle = String.valueOf(p.getPrecio());

                    // Cargamos los datos a pasar
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id_detalle);
                    bundle.putString("producto", producto_detalle);
                    bundle.putString("cantidad", cantidad_detalle);
                    bundle.putString("precio", precio_detalle);

                    // Iniciamos la segunda Activity con los datos cargados

                    Intent intent = new Intent(MainActivity.this,DetallesProductoActivity.class);
                     intent.putExtras(bundle);
                     startActivity(intent);
                }
            });

        /******************** PULSACIÓN LARGA SOBRE ELEMENTO DEL LISTVIEW - BORRADO PRODUCTO *********************/

            listado_productos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

                    /*************************DIÁLOGO***********************/
                    AlertDialog.Builder dialogo_confirmacion = new AlertDialog.Builder(MainActivity.this);

                    dialogo_confirmacion.setTitle("Borrar Producto");
                    dialogo_confirmacion.setMessage("¿Estás seguro que deseas borrar el producto?");
                    dialogo_confirmacion.setCancelable(false);
                    dialogo_confirmacion.setPositiveButton("BORRAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Producto p = (Producto)parent.getItemAtPosition(position);

                            basedatos.eliminar(p);
                            Toast.makeText(getApplicationContext(),"Se ha borrado el contacto con id "+p.getID(), Toast.LENGTH_LONG).show();

                            // Actualizamos
                            basedatos.consultar(productos);
                            adaptador.notifyDataSetChanged();
                        }
                    });

                    dialogo_confirmacion.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialogo_confirmacion.show();
                    return true;
                }
            });
    }

    // ***************************** MENÚ ***************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.anadirLista_menuItem) {
            anadirProducto();
        }
        return true;
    }
    /*************** MÉTODO QUE MUESTRA EL DIÁLOGO AÑADIR A LA LISTA *****************/

    private void anadirProducto(){
            // Obtenemos el layout del diálogo
        final LayoutInflater li = LayoutInflater.from(this);
        final View layout_dialogo = li.inflate(R.layout.dialogo, null);
            // Creamos el cuadro de diálogo
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            // Establecemos el layout del cuadro de diálogo
        dialog.setView(layout_dialogo);
        // Título
        dialog.setTitle("Añadir Producto");

        // Botones
        dialog.setCancelable(false);
        dialog.setPositiveButton("AÑADIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Asociamos los objetos a la variables
                producto = layout_dialogo.findViewById(R.id.producto_editText);
                cantidad = layout_dialogo.findViewById(R.id.cantidad_editText);
                precio = layout_dialogo.findViewById(R.id.precio_editText);

                basedatos = new BDAccesoDatos(getApplicationContext());
                try {
                    Producto p = new Producto(producto.getText().toString(), Integer.parseInt(cantidad.getText().toString()) , Float.parseFloat(precio.getText().toString()));
                    long id = basedatos.insertar(p);
                    Toast.makeText(getApplicationContext(),"Se ha creado el registro con id "+id, Toast.LENGTH_LONG).show();
                    // Actualizamos
                    basedatos.consultar(productos);
                    adaptador.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"El producto no ha sido añadido.\nTodos los campos deben ser rellenados correctamente. ", Toast.LENGTH_LONG).show();

                }




            }
        });
        dialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Registro Cancelado", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }





}
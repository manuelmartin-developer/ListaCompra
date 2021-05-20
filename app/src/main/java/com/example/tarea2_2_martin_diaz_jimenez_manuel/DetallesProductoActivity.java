package com.example.tarea2_2_martin_diaz_jimenez_manuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetallesProductoActivity extends AppCompatActivity {

    // Objetos a enlazar
    String id, producto, cantidad, precio;
    EditText id_et, producto_et, cantidad_et, precio_et;
    Button atras;

    // Enlazar variables a objetos



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_producto);

        /***************** DATOS DEL PRODUCTO***************/
        // Recuperamos los datos exportados por el otro activity y los asociamos a los editText
        id = getIntent().getExtras().getString("id");
        producto = getIntent().getExtras().getString("producto");
        cantidad = getIntent().getExtras().getString("cantidad");
        precio = getIntent().getExtras().getString("precio");

        id_et = findViewById(R.id.id_editText2);
        producto_et = findViewById(R.id.producto_editText2);
        cantidad_et = findViewById(R.id.cantidad_editText2);
        precio_et = findViewById(R.id.precio_editText2);

        id_et.setText(id);
        producto_et.setText(producto);
        cantidad_et.setText(cantidad);
        precio_et.setText(precio);

        /***************** BOTÓN ATRAS ***************/

        atras = findViewById(R.id.atras_bt);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetallesProductoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



}
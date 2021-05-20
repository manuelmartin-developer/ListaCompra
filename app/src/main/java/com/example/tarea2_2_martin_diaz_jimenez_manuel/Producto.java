package com.example.tarea2_2_martin_diaz_jimenez_manuel;

import java.io.Serializable;

public class Producto implements Serializable {

    // Declaración de variables
    private int id, cantidad;
    private String producto;
    private float precio;

    // Métodos constructores

    public Producto (int id, String producto, int cantidad, float precio){
        this.id=id;
        this.producto=producto;
        this.cantidad=cantidad;
        this.precio=precio;
    }
    public Producto (String producto, int cantidad, float precio){
        this.producto=producto;
        this.cantidad=cantidad;
        this.precio=precio;
    }

    // Getters

    public int getID(){
        return id;
    }

    public String getProducto(){
        return producto;
    }

    public int getCantidad(){
        return cantidad;
    }

    public float getPrecio(){
        return precio;
    }

    // Setters

    public void setId(int id){
        this.id=id;
    }
    public void setProducto(String producto){
        this.producto=producto;
    }
    public void setCantidad(int cantidad){
        this.cantidad=cantidad;
    }
    public void setPrecio(float precio){
        this.precio=precio;
    }

    // ToString


    @Override
    public String toString()  {
        return producto+ " \nCantidad: \t" + cantidad+"\nPrecio:\t"+precio;
    }
}

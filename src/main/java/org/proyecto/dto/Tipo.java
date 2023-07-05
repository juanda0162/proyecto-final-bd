package org.proyecto.dto;

public class Tipo {
    private int idTipo;
    private String nombre;
    private String comodidades;
    private float precio;

    public Tipo(int idTipo, String nombre, String comodidades,float precio) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.comodidades = comodidades;
        this.precio = precio;
    }

    public Tipo() {
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComodidades() {
        return comodidades;
    }

    public void setComodidades(String comodidades) {
        this.comodidades = comodidades;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

package org.proyecto.dto;

public class ServicioAdicional {
    private int idServicioAdicional;
    private String nombre;
    private float precio;

    public ServicioAdicional(String nombre, float precio) {
        this.idServicioAdicional = idServicioAdicional;
        this.nombre = nombre;
        this.precio = precio;
    }

    public ServicioAdicional() {
    }

    public int getIdServicioAdicional() {
        return idServicioAdicional;
    }

    public void setIdServicioAdicional(int idServicioAdicional) {
        this.idServicioAdicional = idServicioAdicional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

package org.proyecto.dto;

public class Huesped {
    private int idHuesped;
    private int ci;
    private String nombre;
    private int telefono;

    public Huesped(int ci, String nombre, int telefono) {
        this.idHuesped = idHuesped;
        this.ci = ci;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Huesped() {
    }

    public int getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}

package org.proyecto.dto;

public class Sucursal {
    private int idSucursal;
    private String nombre;
    private String direccion;
    private String clasificacion;
    private int telefono;
    private int idCadenaHotelera;

    public Sucursal(String nombre, String direccion, String clasificacion, int telefono, int idCadenaHotelera) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.clasificacion = clasificacion;
        this.telefono = telefono;
        this.idCadenaHotelera = idCadenaHotelera;
    }

    public Sucursal() {
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getIdCadenaHotelera() {
        return idCadenaHotelera;
    }

    public void setIdCadenaHotelera(int idCadenaHotelera) {
        this.idCadenaHotelera = idCadenaHotelera;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idSucursal\":" + idSucursal +
                ", \"nombre\":\"" + nombre + "\"" +
                ", \"direccion\":\"" + direccion + "\"" +
                ", \"clasificacion\":\"" + clasificacion + "\"" +
                ", \"telefono\":" + telefono +
                ", \"idCadenaHotelera\":" + idCadenaHotelera +
                '}';
    }
}

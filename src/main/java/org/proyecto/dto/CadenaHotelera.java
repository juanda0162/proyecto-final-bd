package org.proyecto.dto;

public class CadenaHotelera {
    private int idCadenaHotelera;
    private String nombre;

    public CadenaHotelera(String nombre) {
        this.nombre = nombre;
    }

    public CadenaHotelera() {
    }

    public int getIdCadenaHotelera() {
        return idCadenaHotelera;
    }

    public void setIdCadenaHotelera(int idCadenaHotelera) {
        this.idCadenaHotelera = idCadenaHotelera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idCadenaHotelera\":" + idCadenaHotelera +
                ", \"nombre\":\"" + nombre + "\"" +
                '}';
    }
}

package org.proyecto.dto;

public class Habitacion {
    private int idHabitacion;
    private String estado;
    private int idSucursal;
    private int idTipo;

    public Habitacion(int idHabitacion, String estado, int idSucursal, int idTipo) {
        this.idHabitacion = idHabitacion;
        this.estado = estado;
        this.idSucursal = idSucursal;
        this.idTipo = idTipo;
    }

    public Habitacion() {
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
}

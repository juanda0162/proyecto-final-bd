package org.proyecto.dto;

public class ReservaHasHabitacion {
    private int idReserva;
    private int idHabitacion;
    private int idReserva_has_habitacion;
    private float precio;

    public ReservaHasHabitacion(int idHabitaciones, int idReservas_has_Habitaciones, float precio) {
        this.idReserva = idReserva;
        this.idHabitacion = idHabitaciones;
        this.idReserva_has_habitacion = idReservas_has_Habitaciones;
        this.precio = precio;
    }

    public ReservaHasHabitacion() {
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getIdReserva_has_habitacion() {
        return idReserva_has_habitacion;
    }

    public void setIdReserva_has_habitacion(int idReserva_has_habitacion) {
        this.idReserva_has_habitacion = idReserva_has_habitacion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

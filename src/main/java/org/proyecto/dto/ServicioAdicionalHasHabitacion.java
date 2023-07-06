package org.proyecto.dto;

public class ServicioAdicionalHasHabitacion {
    private int idServicioAdicional;
    private int idHabitacion;
    private int idServicioAdicional_has_Habitacion;

    public ServicioAdicionalHasHabitacion(int idHabitaciones, int idServicioAdicional_has_Habitaciones) {
        this.idHabitacion = idHabitaciones;
        this.idServicioAdicional_has_Habitacion = idServicioAdicional_has_Habitaciones;
    }

    public ServicioAdicionalHasHabitacion() {
    }

    public int getIdServicioAdicional() {
        return idServicioAdicional;
    }

    public void setIdServicioAdicional(int idServicioAdicional) {
        this.idServicioAdicional = idServicioAdicional;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getIdServicioAdicional_has_Habitacion() {
        return idServicioAdicional_has_Habitacion;
    }

    public void setIdServicioAdicional_has_Habitacion(int idServicioAdicional_has_Habitacion) {
        this.idServicioAdicional_has_Habitacion = idServicioAdicional_has_Habitacion;
    }
}

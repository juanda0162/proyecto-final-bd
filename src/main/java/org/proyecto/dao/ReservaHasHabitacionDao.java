package org.proyecto.dao;

import org.proyecto.dto.Habitacion;
import org.proyecto.dto.Reserva;
import org.proyecto.dto.ReservaHasHabitacion;

import java.util.ArrayList;

public abstract class ReservaHasHabitacionDao {
    public abstract int insert(ReservaHasHabitacion obj) throws Exception;
    public abstract void update(ReservaHasHabitacion obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract ReservaHasHabitacion get(int id) throws Exception;
    public abstract ArrayList<ReservaHasHabitacion> getListPorReserva(Reserva reserva) throws Exception;
    public abstract ArrayList<ReservaHasHabitacion> getListPorHabitacion(Habitacion habitacion) throws Exception;
    public abstract ArrayList<ReservaHasHabitacion> getList() throws Exception;
}

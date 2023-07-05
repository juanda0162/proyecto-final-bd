package org.proyecto.dao;

import org.proyecto.dto.*;

import java.util.ArrayList;

public abstract class ServicioAdicionalHasHabitacionDao {
    public abstract int insert(ServicioAdicionalHasHabitacion obj) throws Exception;
    public abstract void update(ServicioAdicionalHasHabitacion obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract ServicioAdicionalHasHabitacion get(int id) throws Exception;
    public abstract ArrayList<ServicioAdicionalHasHabitacion> getListPorServicioAdicional(ServicioAdicional servicioAdicional) throws Exception;
    public abstract ArrayList<ServicioAdicionalHasHabitacion> getListPorHabitacion(Habitacion habitacion) throws Exception;
    public abstract ArrayList<ServicioAdicionalHasHabitacion> getList() throws Exception;
}

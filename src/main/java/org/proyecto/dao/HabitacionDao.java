package org.proyecto.dao;

import org.proyecto.dto.Habitacion;
import org.proyecto.dto.Tipo;

import java.util.ArrayList;

public abstract class HabitacionDao {
    public abstract int insert(Habitacion obj) throws Exception;
    public abstract void update(Habitacion obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Habitacion get(int id) throws Exception;
    public abstract ArrayList<Habitacion> getListPorTipo(Tipo tipo) throws Exception;
    public abstract ArrayList<Habitacion> getList() throws Exception;
}

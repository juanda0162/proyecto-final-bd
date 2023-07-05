package org.proyecto.dao;

import org.proyecto.dto.Deuda;
import org.proyecto.dto.Reserva;

import java.util.ArrayList;

public abstract class DeudaDao {
    public abstract int insert(Deuda obj) throws Exception;
    public abstract void update(Deuda obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Deuda get(int id) throws Exception;
    public abstract Deuda getListPorReserva(Reserva reserva) throws Exception;
    public abstract ArrayList<Deuda> getList() throws Exception;
}

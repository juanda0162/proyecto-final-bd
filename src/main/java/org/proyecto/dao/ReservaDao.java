package org.proyecto.dao;

import org.proyecto.dto.Huesped;
import org.proyecto.dto.MetodoDePago;
import org.proyecto.dto.Reserva;

import java.util.ArrayList;

public abstract class ReservaDao {
    public abstract int insert(Reserva obj) throws Exception;
    public abstract void update(Reserva obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Reserva get(int id) throws Exception;
    public abstract ArrayList<Reserva> getListPorHuesped(Huesped huesped) throws Exception;
    public abstract ArrayList<Reserva> getListPorMetodoDePago(MetodoDePago metodoDePago) throws Exception;

    public abstract ArrayList<Reserva> getList() throws Exception;
}

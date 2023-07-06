package org.proyecto.dao;

import org.proyecto.dto.Checkin;

import java.util.ArrayList;

public abstract class CheckinDao {
    public abstract int insert(Checkin obj) throws Exception;
    public abstract void update(Checkin obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Checkin get(int id) throws Exception;
    public abstract Checkin getPorReserva(int id) throws Exception;
    public abstract ArrayList<Checkin> getList() throws Exception;
}
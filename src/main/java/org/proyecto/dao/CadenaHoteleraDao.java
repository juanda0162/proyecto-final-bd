package org.proyecto.dao;

import org.proyecto.dto.CadenaHotelera;

import java.util.ArrayList;

public abstract class CadenaHoteleraDao {
    public abstract int insert(CadenaHotelera obj) throws Exception;
    public abstract void update(CadenaHotelera obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract CadenaHotelera get(int id) throws Exception;
    public abstract ArrayList<CadenaHotelera> getList() throws Exception;
}
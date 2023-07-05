package org.proyecto.dao;

import org.proyecto.dto.CadenaHotelera;
import org.proyecto.dto.Sucursal;

import java.util.ArrayList;

public abstract class SucursalDao {
    public abstract int insert(Sucursal obj) throws Exception;
    public abstract void update(Sucursal obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Sucursal get(int id) throws Exception;
    public abstract ArrayList<Sucursal> getListPorCadenaHotelera(CadenaHotelera cadenaHotelera) throws Exception;
    public abstract ArrayList<Sucursal> getList() throws Exception;
}

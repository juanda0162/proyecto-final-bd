package org.proyecto.dao;

import org.proyecto.dto.Huesped;

import java.util.ArrayList;

public abstract class HuespedDao {
    public abstract int insert(Huesped obj) throws Exception;
    public abstract void update(Huesped obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Huesped get(int id) throws Exception;
    public abstract ArrayList<Huesped> getList() throws Exception;
}

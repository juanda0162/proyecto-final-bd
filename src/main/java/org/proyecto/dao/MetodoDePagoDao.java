package org.proyecto.dao;

import org.proyecto.dto.MetodoDePago;

import java.util.ArrayList;

public abstract class MetodoDePagoDao {
    public abstract int insert(MetodoDePago obj) throws Exception;
    public abstract MetodoDePago update(MetodoDePago obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract MetodoDePago get(int id) throws Exception;
    public abstract ArrayList<MetodoDePago> getList() throws Exception;
}

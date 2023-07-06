package org.proyecto.dao;

import org.proyecto.dto.ServicioAdicional;

import java.util.ArrayList;

public abstract class ServicioAdicionalDao {
    public abstract int insert(ServicioAdicional obj) throws Exception;
    public abstract void update(ServicioAdicional obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract ServicioAdicional get(int id) throws Exception;
    public abstract ArrayList<ServicioAdicional> getList() throws Exception;
}
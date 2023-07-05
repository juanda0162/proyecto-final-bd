package org.proyecto.dao;

import org.proyecto.dto.Tipo;

import java.util.ArrayList;

public abstract class TipoDao {
    public abstract int insert(Tipo obj) throws Exception;
    public abstract Tipo update(Tipo obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Tipo get(int id) throws Exception;
    public abstract ArrayList<Tipo> getList() throws Exception;
}

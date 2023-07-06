package org.proyecto.dao;

import org.proyecto.dto.Checkin;
import org.proyecto.dto.Comentario;

import java.util.ArrayList;

public abstract class ComentarioDao {
    public abstract int insert(Comentario obj) throws Exception;
    public abstract Comentario update(Comentario obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Comentario get(int id) throws Exception;
    public abstract ArrayList<Comentario> getListPorChekin(Checkin checkin) throws Exception;
    public abstract ArrayList<Comentario> getList() throws Exception;
}

package org.proyecto.dto;

import java.util.Date;

public class Comentario {
    private int idComentario;
    private String descripcion;
    private String calificacion;
    private Date fecha;
    private int idChekin;

    public Comentario(int idComentario, String descripcion, String calificacion, Date fecha, int idChekin) {
        this.idComentario = idComentario;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.idChekin = idChekin;
    }

    public Comentario() {
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdChekin() {
        return idChekin;
    }

    public void setIdChekin(int idChekin) {
        this.idChekin = idChekin;
    }
}

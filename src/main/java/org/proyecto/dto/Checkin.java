package org.proyecto.dto;

import java.util.Date;

public class Checkin {
    private int idChekin;
    private Date registroLlegada;
    private Date registroSalida;
    private int idReservas;

    public Checkin(int idChekin, Date registroLlegada, Date registroSalida, int idReservas) {
        this.idChekin = idChekin;
        this.registroLlegada = registroLlegada;
        this.registroSalida = registroSalida;
        this.idReservas = idReservas;
    }

    public Checkin() {
    }

    public int getIdChekin() {
        return idChekin;
    }

    public void setIdChekin(int idChekin) {
        this.idChekin = idChekin;
    }

    public Date getRegistroLlegada() {
        return registroLlegada;
    }

    public void setRegistroLlegada(Date registroLlegada) {
        this.registroLlegada = registroLlegada;
    }

    public Date getRegistroSalida() {
        return registroSalida;
    }

    public void setRegistroSalida(Date registroSalida) {
        this.registroSalida = registroSalida;
    }

    public int getIdReservas() {
        return idReservas;
    }

    public void setIdReservas(int idReservas) {
        this.idReservas = idReservas;
    }
}

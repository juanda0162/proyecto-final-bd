package org.proyecto.dto;

public class Deuda {
    private int idDeuda;
    private float montoTotal;
    private int idReserva;

    public Deuda(int idDeuda, float montoTotal, int idReserva) {
        this.idDeuda = idDeuda;
        this.montoTotal = montoTotal;
        this.idReserva = idReserva;
    }

    public Deuda() {
    }

    public int getIdDeuda() {
        return idDeuda;
    }

    public void setIdDeuda(int idDeuda) {
        this.idDeuda = idDeuda;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
}

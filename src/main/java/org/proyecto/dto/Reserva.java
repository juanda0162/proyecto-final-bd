package org.proyecto.dto;


import java.sql.Date;

public class Reserva {
    private int idReserva;
    private String estado;
    private Date fechaEntrada;
    private Date fechaSalida;
    private String peticion;
    private int cantidadPersonas;
    private int idHuesped;
    private int idMetodoDePago;

    public Reserva(int idReserva, String estado, Date fechaEntrada, Date fechaSalida, String peticion, int cantidadPersonas, int huespedCi, int idMetodoDePago) {
        this.idReserva = idReserva;
        this.estado = estado;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.peticion = peticion;
        this.cantidadPersonas = cantidadPersonas;
        this.idHuesped = huespedCi;
        this.idMetodoDePago = idMetodoDePago;
    }

    public Reserva() {
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getPeticion() {
        return peticion;
    }

    public void setPeticion(String peticion) {
        this.peticion = peticion;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public int getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
    }

    public int getIdMetodoDePago() {
        return idMetodoDePago;
    }

    public void setIdMetodoDePago(int idMetodoDePago) {
        this.idMetodoDePago = idMetodoDePago;
    }
}

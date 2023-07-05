package org.proyecto.dto;

public class MetodoDePago {
    private int idMetodoDePago;
    private String tipo;
    private String descripcion;

    public MetodoDePago(int idMetodoDePago, String tipo, String descripcion) {
        this.idMetodoDePago = idMetodoDePago;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public MetodoDePago(){

    }

    public int getIdMetodoDePago() {
        return idMetodoDePago;
    }

    public void setIdMetodoDePago(int idMetodoDePago) {
        this.idMetodoDePago = idMetodoDePago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

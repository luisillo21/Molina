package com.example.pagoa.model;

import java.io.Serializable;

public class Pago implements Serializable {
    private int idpago;
    private String codigo;
    private String descripcion;
    private double monto;
    private String estado;
    private String tipo;
    private int idusuario;
    private int idbase;


    public Pago(String codigo, String descripcion, double monto, String estado, String tipo, int idusuario) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.monto = monto;
        this.estado = estado;
        this.tipo = tipo;
        this.idusuario = idusuario;
    }

    public Pago() {
    }

    public int getIdbase() {
        return idbase;
    }

    public void setIdbase(int idbase) {
        this.idbase = idbase;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
}

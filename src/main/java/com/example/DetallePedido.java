package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  idDetalle;

    private int Cantidad;
    private Double precioUnitario;

    public DetallePedido() {
    }

    public DetallePedido(int cantidad, Double precioUnitario) {
        this.Cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        this.Cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "DetallePedido [id=" + idDetalle + ", cantidad=" + Cantidad + ", precioUnitario=" + precioUnitario + "]";
    }
}

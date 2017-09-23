/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author mj.jaime10
 */
@Entity
public class PedidoClienteEntity extends BaseEntity implements Serializable{
    
    /*
    * Precio de la compra
    */
    private double precio;
    
    /*
    * Estado del pedido. Puede ser ACEPTADO o RECHAZADO
    */
    private String estado;
    
     /*
    * Fecha estimada de entrega
    */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEstimada;
    
    /**
     * Método que retorna la fecha estimada de entrega del pedido
     * @return fechaEstimada
     */
    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    /**
     * Método que retorna el precio del pedido
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }
    
    /**
     * Método que retorna el estado del pedido
     * @return estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método que modifica la fecha estimada de entrega del pedido
     * @param fechaEstimada de entrega
     */
    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    /**
     * Método que modifica el precio del pedido
     * @param precio del pedido
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * Método que modifica el estado del pedido
     * @param estado del pedido
     */
    public void setEstado( String estado )
    {
        this.estado = estado;
    }
    
}

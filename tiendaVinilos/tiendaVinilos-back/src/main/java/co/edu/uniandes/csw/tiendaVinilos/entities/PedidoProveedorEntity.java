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
 * @author s.saenz11
 */

@Entity
public class PedidoProveedorEntity extends BaseEntity implements Serializable {
    
     /*
    * Fecha estimada de entrega
    */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEstimada;
    
    /*
    * Precio de la compra
    */
    private double precio;

    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    public double getPrecio() {
        return precio;
    }

    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author mj.jaime10
 */
@Entity
public class PagoClienteEntity extends BaseEntity implements Serializable{

    /**
     * Valor total del pago
     */
    private double valor;
    
    /**
     * Fecha de realización del pago
     */
    private Date fechaPago;
    
    /**
     * Método que retorna la fecha del pago
     * @return fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * Método que retorna el valor del pedido
     * @return valor
     */
    public double getValor() {
        return valor;
    }
    
    /**
     * Método que modifica la fecha de pago
     * @param fechaPago del pago
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Método que modifica el valor del pago
     * @param valor del pedido
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
}

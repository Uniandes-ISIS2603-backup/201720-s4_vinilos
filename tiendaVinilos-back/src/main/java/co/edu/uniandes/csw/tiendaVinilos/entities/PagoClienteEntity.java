/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mj.jaime10
 */
@Entity
public class PagoClienteEntity implements Serializable{

     /*
    * nombre
    */
    private String name;
    
    /*
    * Id de la compra
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Valor total del pago
     */
    private double valor;
    
    /**
     * Fecha de realización del pago
     */
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    
    @OneToOne
    private PedidoClienteEntity pedido;
    
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
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the telefono to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public PedidoClienteEntity getPedido( )
    {
        return pedido;
    }
    
    public void setPedido( PedidoClienteEntity pedido )
    {
        this.pedido = pedido;
    }
    
    public boolean equals(Object obj) {
        if (this.getId() != null && ((PedidoClienteEntity) obj).getId() != null) {
            return this.getId().equals(((PedidoClienteEntity) obj).getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}

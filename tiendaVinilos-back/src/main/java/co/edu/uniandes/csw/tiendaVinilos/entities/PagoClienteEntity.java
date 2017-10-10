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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mj.jaime10
 */
@Entity
public class PagoClienteEntity implements Serializable{
    @PodamExclude
    @OneToOne
    private PedidoClienteEntity pedido;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
     private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Valor total del pago
     */
    private double valor;
    
    /**
     * Fecha de realización del pago
     */
    @Temporal(javax.persistence.TemporalType.DATE)
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
    
    public void setPedido( PedidoClienteEntity pedido )
    {
        this.pedido = pedido;
    }
    
    public PedidoClienteEntity getPedido( )
    {
        return pedido;
    }
    
}

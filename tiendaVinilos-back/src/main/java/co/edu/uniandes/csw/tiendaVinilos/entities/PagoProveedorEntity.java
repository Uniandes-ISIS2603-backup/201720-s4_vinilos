/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.saenz11
 */
@Entity
public class PagoProveedorEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @OneToOne
    private PedidoProveedorEntity pagoPedido ;

    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;
    
     public ProveedorEntity getProveedor()
    {
        return proveedor;
    }
    
    public void setProveedor(ProveedorEntity proveedor)
    {
        this.proveedor = proveedor;
    }
    
    public PedidoProveedorEntity getPagoPedido() {
        return pagoPedido;
    }

    public void setPagoPedido(PedidoProveedorEntity pago) {
        this.pagoPedido = pago;
    }
    
    
    
     /*
    * Fecha estimada de entrega
    */
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

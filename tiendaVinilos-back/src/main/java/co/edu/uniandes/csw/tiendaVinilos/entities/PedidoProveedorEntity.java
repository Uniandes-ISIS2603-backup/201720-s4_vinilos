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
import javax.persistence.OneToMany;
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
public class PedidoProveedorEntity extends BaseEntity implements Serializable {
    @PodamExclude
    @OneToOne(mappedBy= "pagoPedido")
    private PagoProveedorEntity pagoProveedor ;

    
    public PagoProveedorEntity getPagoP(){
        
        return pagoProveedor;
    }
    
    public void setPagoProveedor(PagoProveedorEntity pp){
        this.pagoProveedor = pp;
    }
    
    
    @PodamExclude
    @ManyToOne
    private PedidoClienteEntity pedidoCliente;
    
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
    
//    @PodamExclude
//    @ManyToOne()
//    private ProveedorEntity proveedor ;
    
    //@PodamExclude
    //@OneToOne(mappedBy ="pagoProveedor")
     //private PagoProveedorEntity pagoProveedorEntity;
    
    //@PodamExclude
    //@OneToMany(mappedBy ="vinilo")
    //private ViniloEntity viniloEntity;
    
//    @PodamExclude
//    @ManyToOne()
//    private PedidoClienteEntity pedidoCLiente;

//    public ProveedorEntity getProveedorEntity() {
//        return proveedor;
//    }
//
//    public void setProveedorEntity(ProveedorEntity proveedor) {
//        this.proveedor = proveedor;
//    }

//    public PagoProveedorEntity getPagoProveedorEntity() {
//        return pagoProveedorEntity;
//    }
//
//    public void setPagoProveedorEntity(PagoProveedorEntity pagoProveedorEntity) {
//        this.pagoProveedorEntity = pagoProveedorEntity;
//    }
/*
    public ViniloEntity getViniloEntity() {
        return viniloEntity;
    }

    public void setViniloEntity(ViniloEntity viniloEntity) {
        this.viniloEntity = viniloEntity;
    }
*/
//    public PedidoClienteEntity getPedidoCLiente() {
//        return pedidoCLiente;
//    }
//
//    public void setPedidoCLiente(PedidoClienteEntity pedidoCLiente) {
//        this.pedidoCLiente = pedidoCLiente;
//    }
    
    
    

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

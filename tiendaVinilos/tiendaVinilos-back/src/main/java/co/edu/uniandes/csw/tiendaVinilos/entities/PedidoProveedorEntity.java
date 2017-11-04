/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

public class PedidoProveedorEntity implements Serializable{

    @PodamExclude
    @OneToOne
    private PagoProveedorEntity pagoProveedor ;

    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
     private String name;

     public ProveedorEntity getProveedor()
     {
         return proveedor;
     }
     
     public void setProveedor (ProveedorEntity proveedor)
     {
         this.proveedor = proveedor;
     }
     
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoClienteEntity getPedidoCliente() {
        return pedidoCliente;
    }

    public void setPedidoCliente(PedidoClienteEntity pedidoCliente) {
        this.pedidoCliente = pedidoCliente;
    }
    
    
    
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
  
    @PodamExclude
    @OneToMany(mappedBy ="pedidoProveedor")
    private List<ViniloEntity> viniloEntity;


    public ProveedorEntity getProveedorEntity() {
        return proveedor;
    }
    public void setProveedorEntity(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }
    
    public List<ViniloEntity> getViniloEntity() {
        return viniloEntity;
    }

    public void setViniloEntity(List<ViniloEntity> viniloEntity) {
        this.viniloEntity = viniloEntity;
    }

    public PagoProveedorEntity getPagoProveedor() {
        return pagoProveedor;
    }
    
    
    
    

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

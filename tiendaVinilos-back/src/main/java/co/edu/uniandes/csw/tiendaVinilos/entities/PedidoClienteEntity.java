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
 * @author mj.jaime10
 */
@Entity
public class PedidoClienteEntity implements Serializable{
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;
    @PodamExclude
    @OneToOne
    private PagoClienteEntity pago;
    
    @PodamExclude
    @OneToMany(mappedBy="pedidoC")
    private List<PedidoProveedorEntity> pedidoP;

    public List<PedidoProveedorEntity> getPedidoP() {
        return pedidoP;
    }

    public void setPedidoP(List<PedidoProveedorEntity> pedidoP) {
        this.pedidoP = pedidoP;
    }

   
    
    
    
    
    
    /*
    * nombre
    */
    private String name;

    public List<PedidoProveedorEntity> getPedidoProveedor() {
        return pedidoProveedor;
    }

    public void setPedidoProveedor(List<PedidoProveedorEntity> pedidoProveedor) {
        this.pedidoProveedor = pedidoProveedor;
    }
    
    
    
    @PodamExclude
    @OneToMany(mappedBy ="pedidoCliente")
    private List<PedidoProveedorEntity> pedidoProveedor;
    /*
    * Id de la compra
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(style = "dd/mm/yyyy")
    private Date fechaEstimada;
    
    /*
    * Dirección de entrega
    */
    private String direccion;
    
    /*
    * Fecha estimada de entrega
    */
    private int telefono;
    
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
    
    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * @return the telefono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
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
    
   
 
    public void setUsuario( UsuarioEntity usuario )
    {
        this.usuario = usuario;
    }
    
    public UsuarioEntity getUsuario( )
    {
        return usuario;
    }
    
    public void setPago( PagoClienteEntity pago )
    {
        this.pago = pago;
    }
    
    public PagoClienteEntity getPago( )
    {
        return pago;
    }
}

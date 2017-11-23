/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author s.saenz11
 */
public class PedidoProveedorDTO {
    
    private Long id;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private double precio;
    
    private String estado;

    public String getEsatdo() {
        return estado;
    }

    public void setEsatdo(String esatdo) {
        this.estado = esatdo;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    //Constructor por defecto
   public PedidoProveedorDTO(){
       //Constructor por defecto
       
   }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param pedido
     */
    public PedidoProveedorDTO(PedidoProveedorEntity pedido) {
        this.id = pedido.getId();
        this.fecha = pedido.getFechaEstimada();
        this.precio = pedido.getPrecio();
        this.estado = pedido.getEstado();
        
    }
    
     public PedidoProveedorEntity toEntity() {
        PedidoProveedorEntity entity = new PedidoProveedorEntity();
        entity.setId(this.id);
        entity.setFechaEstimada(this.fecha);
        entity.setPrecio(this.precio);
        entity.setEstado(this.estado);
        
        
        return entity;
    }
    
}

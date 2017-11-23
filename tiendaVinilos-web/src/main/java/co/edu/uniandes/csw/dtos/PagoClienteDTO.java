/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoClienteEntity;
import java.util.Date;

/**
 *
 * @author mj.jaime10
 */
public class PagoClienteDTO {
    
    private Long id;
    private double valor;
    private Date fechaPago;
    
    public Date getFechaPago( )
    {
        return fechaPago;
    }
    
    public void setFechaPago( Date fechaPago )
    {
        this.fechaPago = fechaPago;
    }
    
    public double getValor( )
    {
        return valor;
    }
    
    public void setValor( double valor )
    {
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
    
    //Constructor por defecto
    public PagoClienteDTO( ) {
        //Constructor por defecto
    }
    
    
    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param pago: Es la entidad que se va a convertir a DTO 
     */
    public PagoClienteDTO(PagoClienteEntity pago) {
        this.id = pago.getId();
        this.valor = pago.getValor();
        this.fechaPago = pago.getFechaPago();
    }
    
    /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public PagoClienteEntity toEntity() {
        PagoClienteEntity entity = new PagoClienteEntity();
        entity.setId(this.id);
        entity.setFechaPago(this.fechaPago);
        entity.setValor(this.valor);
        return entity;
    }
}


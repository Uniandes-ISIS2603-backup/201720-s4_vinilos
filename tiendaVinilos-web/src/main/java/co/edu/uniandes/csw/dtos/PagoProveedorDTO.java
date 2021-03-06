
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;

import org.springframework.format.annotation.DateTimeFormat;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author s.saenz11
 */
public class PagoProveedorDTO {
    private double valor;
    private Long   id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date   fecha;

    // Constructor por defecto
    public PagoProveedorDTO() {

        // Constructor por defecto
    }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param pedido: Es la entidad que se va a convertir a DTO
     */
    public PagoProveedorDTO(PagoProveedorEntity pedido) {
        this.id    = pedido.getId();
        this.fecha = pedido.getFechaEstimada();
        this.valor = pedido.getPrecio();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public PagoProveedorEntity toEntity() {
        PagoProveedorEntity entity = new PagoProveedorEntity();

        entity.setId(this.id);
        entity.setFechaEstimada(this.fecha);
        entity.setPrecio(this.valor);

        return entity;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

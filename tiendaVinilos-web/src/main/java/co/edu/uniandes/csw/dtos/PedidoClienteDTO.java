
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;

/**
 *
 * @author mj.jaime10
 */
public class PedidoClienteDTO {
    private Long   id;
    private String estado;
    private double precio;
    private String direccion;
    private int    telefono;
    private Date   fechaEstimada;

    // Constructor por defecto
    public PedidoClienteDTO() {

        // Constructor por defecto
    }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param pedido: Es la entidad que se va a convertir a DTO
     */
    public PedidoClienteDTO(PedidoClienteEntity pedido) {
        this.id            = pedido.getId();
        this.estado        = pedido.getEstado();
        this.precio        = pedido.getPrecio();
        this.fechaEstimada = pedido.getFechaEstimada();
        this.direccion     = pedido.getDireccion();
        this.telefono      = pedido.getTelefono();
    }

    /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO
     */
    public PedidoClienteEntity toEntity() {
        PedidoClienteEntity entity = new PedidoClienteEntity();

        entity.setId(this.id);
        entity.setEstado(this.estado);
        entity.setPrecio(this.precio);
        entity.setFechaEstimada(this.fechaEstimada);
        entity.setTelefono(this.telefono);
        entity.setDireccion(this.direccion);

        return entity;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the fecha estimada
     */
    public Date getfechaEstimada() {
        return fechaEstimada;
    }

    /**
     * @param fechaEstimada the fecha estimada to set
     */
    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
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
}


//~ Formatted by Jindent --- http://www.jindent.com

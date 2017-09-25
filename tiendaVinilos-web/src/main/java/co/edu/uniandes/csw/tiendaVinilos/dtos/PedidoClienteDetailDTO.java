/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;

/**
 *
 * @author mj.jaime10
 */
public class PedidoClienteDetailDTO extends PedidoClienteDTO{
    
    /**
     * Constructor por defecto
     */
    public PedidoClienteDetailDTO() {
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public PedidoClienteDetailDTO(PedidoClienteEntity entity) {
        super(entity);
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public PedidoClienteEntity toEntity() {
        PedidoClienteEntity pedidoE = super.toEntity();
        return pedidoE;
    }
}

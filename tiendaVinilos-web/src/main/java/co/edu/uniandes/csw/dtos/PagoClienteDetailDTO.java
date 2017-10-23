/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoClienteEntity;

/**
 *
 * @author mj.jaime10
 */
public class PagoClienteDetailDTO extends PagoClienteDTO{
    
    
    private PedidoClienteDTO pedido;
    /**
     * Constructor por defecto
     */
    public PagoClienteDetailDTO() {
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public PagoClienteDetailDTO(PagoClienteEntity entity) {
        super(entity);
        
        if( entity != null )
        {
            this.pedido = new PedidoClienteDTO(entity.getPedido());
        }
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    public PagoClienteEntity toEntity() {
        PagoClienteEntity pago = super.toEntity();
        pago.setPedido(pedido.toEntity());
        return pago;
    }
    
    
    public void setPedido( PedidoClienteDTO pedido )
    {
        this.pedido = pedido;
    }
    
    public PedidoClienteDTO getPedido( )
    {
        return pedido;
    }
    
}

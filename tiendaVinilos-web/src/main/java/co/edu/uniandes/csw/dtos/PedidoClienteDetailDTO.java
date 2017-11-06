/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;

/**
 *
 * @author mj.jaime10
 */
public class PedidoClienteDetailDTO extends PedidoClienteDTO{
    
    private PagoClienteDTO pago;
    private UsuarioDTO usuario;
    
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
        
        if( entity != null )
        {
            this.pago = new PagoClienteDTO(entity.getPago());
            this.usuario = new UsuarioDTO(entity.getUsuario());
        }
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public PedidoClienteEntity toEntity() {
        PedidoClienteEntity pedidoE = super.toEntity();
        pedidoE.setPago(pago.toEntity());
        pedidoE.setUsuario(usuario.toEntity());
        return pedidoE;
    }
    
    public void setUsuario( UsuarioDTO usuario )
    {
        this.usuario = usuario;
    }
    
    public UsuarioDTO getUsuario( )
    {
        return usuario;
    }
    
    public void setPago( PagoClienteDTO pago )
    {
        this.pago = pago;
    }
    
    public PagoClienteDTO getPago( )
    {
        return pago;
    }
    
}

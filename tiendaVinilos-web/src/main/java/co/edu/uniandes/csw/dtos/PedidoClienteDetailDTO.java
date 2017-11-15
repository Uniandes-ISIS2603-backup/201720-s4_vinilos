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
    private PedidoProveedorDTO pedidoP;
    private CarroComprasDetailDTO carrito;
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
            if(pago != null){
                 this.pago = new PagoClienteDetailDTO(entity.getPago());
            }
          
            this.usuario = new UsuarioDetailDTO(entity.getUsuario());
            if(pedidoP != null){
                this.pedidoP = new PedidoProveedorDetailDTO(entity.getPedidoP());
            }
            carrito= new CarroComprasDetailDTO(entity.getUsuario().getCarrito());
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
        
        if (pago != null){
            
            pedidoE.setPago(pago.toEntity());
        }
        
        
        if(usuario != null){
            
            pedidoE.setUsuario(usuario.toEntity());
        }
        
        if(pedidoP != null){
            pedidoE.setPedidoP(pedidoP.toEntity());
        }
        if(carrito!=null){
        pedidoE.getUsuario().setCarrito(carrito.toEntity());
        }
        
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

    public PedidoProveedorDTO getPedidoC() {
        return pedidoP;
    }

    public void setPedidoC(PedidoProveedorDTO pedidoC) {
        this.pedidoP = pedidoC;
    }

    public PedidoProveedorDTO getPedidoP() {
        return pedidoP;
    }

    public void setPedidoP(PedidoProveedorDTO pedidoP) {
        this.pedidoP = pedidoP;
    }

    public CarroComprasDetailDTO getCarrito() {
        return carrito;
    }

    public void setCarrito(CarroComprasDetailDTO carrito) {
        this.carrito = carrito;
    }
    
    
}

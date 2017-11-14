/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;

/**
 *
 * @author s.saenz11
 */
public class PedidoProveedorDetailDTO extends PedidoProveedorDTO{
    
    private PagoProveedorDTO pago;
    
    private ProveedorDTO proveedor;
    
    private PedidoClienteDTO pedidoC;
    
     public PedidoProveedorDetailDTO(){
         
         
     }
   
    /**
     * Constructor por defecto
   
     * @param entity
     */
    public PedidoProveedorDetailDTO(PedidoProveedorEntity entity) {
        super(entity);
        
        if(pago != null){
            this.pago = new PagoProveedorDetailDTO(entity.getPagoProveedor());
        }
        if(proveedor != null){
            this.proveedor = new ProveedorDetailDTO(entity.getProveedor());
        }
        if(pedidoC != null){
            this.pedidoC = new PedidoClienteDetailDTO(entity.getPedidoCliente());
        }
        
    }

    

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    public PedidoProveedorEntity toEntity() {
        PedidoProveedorEntity pedidoP = super.toEntity();
        
        
         if(pago != null){
            pedidoP.setPagoProveedor(pago.toEntity());
        }
        if(proveedor != null){
            pedidoP.setProveedor(proveedor.toEntity());
        }
        if(pedidoC != null){
            pedidoP.setPedidoCliente(pedidoC.toEntity());
        }
        
        return pedidoP;
    }

    public PagoProveedorDTO getPago() {
        return pago;
    }

    public void setPago(PagoProveedorDTO pago) {
        this.pago = pago;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public PedidoClienteDTO getPedidoC() {
        return pedidoC;
    }

    public void setPedidoC(PedidoClienteDTO pedidoC) {
        this.pedidoC = pedidoC;
    }

    
    
}

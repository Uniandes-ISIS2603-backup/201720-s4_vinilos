/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s.saenz11
 */
public class PedidoProveedorDetailDTO extends PedidoProveedorDTO{
    
    private PagoProveedorDTO pago;
    
    private ProveedorDTO proveedor;
    
    private PedidoClienteDTO pedidoC;
    
    private List<ViniloDTO> vinilo;
    
    //Constructor por defecto
     public PedidoProveedorDetailDTO(){
         //Constructor por defecto
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
        
         List<ViniloEntity> lista= entity.getViniloEntity();
        List<ViniloDTO> list= new ArrayList<>();
        for (ViniloEntity viniloEntity : lista) {
            ViniloDTO dto= new ViniloDTO(viniloEntity);
            list.add(dto);
        }
        vinilo = (ArrayList<ViniloDTO>)list;
        
    }

    

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public PedidoProveedorEntity toEntity() {
        PedidoProveedorEntity pedidoP = super.toEntity();
        
        
        ArrayList<ViniloEntity> cards=new ArrayList<>();
        if(vinilo!=null)
        {
        for (ViniloDTO vinilos: vinilo) {
            cards.add(vinilos.toEntity());
        }
        }
        
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

    public List<ViniloDTO> getVinilo() {
        return vinilo;
    }

    public void setVinilo(List<ViniloDTO> vinilo) {
        this.vinilo = vinilo;
    }

    
    
}

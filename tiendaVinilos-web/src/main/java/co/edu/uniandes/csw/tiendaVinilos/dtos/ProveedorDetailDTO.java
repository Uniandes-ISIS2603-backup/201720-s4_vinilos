/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc.ruiz
 */
public class ProveedorDetailDTO extends ProveedorDTO {

    private List<FeedBackDTO> feedBack;

    private List<PagoProveedorDTO> pagos;
    
    private List<PedidoProveedorDTO> pedidos;
    
    public void setPagos(List<PagoProveedorDTO> pagos)
    {
        this.pagos = pagos;
    }
    
    public List<PagoProveedorDTO> getPagos()
    {
        return pagos;
    }
    
    public void setFeedBacks(List<FeedBackDTO> feedBacks) {
        this.feedBack = feedBacks;
    }

    public List<FeedBackDTO> getFeedBacks() {
        return feedBack;
    }

    public void setPedidos(List<PedidoProveedorDTO> pedidos)
    {
        this.pedidos = pedidos;
    }
    
    public List<PedidoProveedorDTO> getPedidos()
    {
        return pedidos;
    }
    public ProveedorDetailDTO() {
    }

    public ProveedorDetailDTO(ProveedorEntity entity) {
        super(entity);
        if (entity != null) {
            feedBack = new ArrayList();
            for (FeedBackEntity fb : entity.getFeedBacks()) {
                feedBack.add(new FeedBackDTO(fb));
            }
        }
        
        if (entity != null) {
            pagos = new ArrayList();
            for (PagoProveedorEntity ent : entity.getPagos()) {
                pagos.add(new PagoProveedorDTO(ent));
            }
        }
        
         if (entity != null)
        {
            pedidos = new ArrayList();
            for (PedidoProveedorEntity pedidoEnt : entity.getPedidos())
                pedidos.add(new PedidoProveedorDTO(pedidoEnt));
        }
    }

    @Override
    public ProveedorEntity toEntity() {
        ProveedorEntity entity = super.toEntity();
        if (entity != null) {
            List<FeedBackEntity> listFB = null;
            if (getFeedBacks() != null) {
                listFB = new ArrayList();
                for (FeedBackDTO fb : getFeedBacks()) {
                    listFB.add(fb.toEntity());
                }
            } 
            entity.setFeedBacks(listFB);
        }
        
        if (entity != null) {
            List<PagoProveedorEntity> listPp = null;
            if (getPagos() != null) {
                listPp = new ArrayList();
                for (PagoProveedorDTO ppDTO : getPagos()) {
                    listPp.add(ppDTO.toEntity());
                }
            } 
            entity.setPagos(listPp);
        }
        if (entity != null)
       {
           List<PedidoProveedorEntity> listPp = null;
           if (getPedidos() != null) {
               listPp = new ArrayList();
           for (PedidoProveedorDTO ppDTO : getPedidos()){
               listPp.add(ppDTO.toEntity());
           }
           }
           entity.setPedidos(listPp);
       }
        return entity;
    }

}

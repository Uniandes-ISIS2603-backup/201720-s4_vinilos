/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.ProveedorPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.ruiz
 */
@Stateless
public class ProveedorLogic {
    private static final Logger LOGGER = Logger.getLogger(ProveedorLogic.class.getName());

    @Inject ProveedorPersistence persistence;
    
//    public List<PedidoProveedorEntity> getPedidos(Long id)
//    {
//        ProveedorEntity ent = persistence.find(id);
//        return ent.getPedidos();
//    }
//    
//    public PedidoProveedorEntity getPedido(Long idProv, Long idPed)
//    {
//        ProveedorEntity ent = persistence.find(idProv);
//        List <PedidoProveedorEntity> list = ent.getPedidos();
//        PedidoProveedorEntity pedido = new PedidoProveedorEntity();
//        pedido.setId(idPed);
//        int index = list.indexOf(pedido);
//        if (index >= 0) return list.get(index);
//        return null;
//    }
    
    public List<FeedBackEntity> getFeedBacks(Long id)
    {
        ProveedorEntity ent = persistence.find(id);
        return ent.getFeedBacks();
    }
    /**
     * @TODO  Es mejor hacer un método de la BD con un select con los dos ids 
     * 
     **/
    public FeedBackEntity getFeedBack(Long idProv, Long idFB)
    {
        ProveedorEntity ent = persistence.find(idProv);
        List<FeedBackEntity> list = ent.getFeedBacks();
        FeedBackEntity fbEntity = new FeedBackEntity();
        fbEntity.setId(idFB);
        if (list != null) {
            int index = list.indexOf(fbEntity);      
        if (index>=0) return list.get(index);
        }
        return null;
    }
    
    
    public ProveedorEntity createProveedor(ProveedorEntity entity) throws BusinessLogicException
    {
        if (persistence.findByEmail(entity.getEmail()) != null)
            throw new BusinessLogicException("Ya existe un proveedor con el email \"" + entity.getEmail() + "\"");
        persistence.create(entity);
        return entity;
    }
    
   public List<ProveedorEntity> getAll()
   {
       return persistence.findAll();
   }
   
   public ProveedorEntity getProveedor(Long id)
   {
       return persistence.find(id);
   }
   
   public ProveedorEntity updateProveedor(ProveedorEntity entity)
   {
       return persistence.update(entity);
   }
   
   public void deleteProveedor(Long id)
   {
       persistence.delete(id);
   }
}



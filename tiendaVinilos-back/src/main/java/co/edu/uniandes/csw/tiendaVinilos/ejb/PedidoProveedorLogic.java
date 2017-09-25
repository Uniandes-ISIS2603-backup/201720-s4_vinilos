/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.PedidoProveedorPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author s.saenz11
 */
public class PedidoProveedorLogic {
     @Inject PedidoProveedorPersistence persistence;
    
    
    public PedidoProveedorEntity createProveedor(PedidoProveedorEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
   public List<PedidoProveedorEntity> getAll()
   {
       return persistence.findAll();
   }
   
   public PedidoProveedorEntity getProveedor(Long id)
   {
       return persistence.find(id);
   }
   
   public PedidoProveedorEntity updateProveedor(PedidoProveedorEntity entity)
   {
       return persistence.update(entity);
   }
   
   public void deleteProveedor(Long id)
   {
       persistence.delete(id);
   }
}

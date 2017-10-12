/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.PagoProveedorPersistennce;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.saenz11
 */
@Stateless
public class PagoProveedorLogic {
    
     @Inject PagoProveedorPersistennce persistence;
    
    
    public PagoProveedorEntity createProveedor(PagoProveedorEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
   public List<PagoProveedorEntity> getAll()
   {
       return persistence.findAll();
   }
   
   public PagoProveedorEntity getProveedor(Long id)
   {
       return persistence.find(id);
   }
   
   public PagoProveedorEntity updateProveedor(PagoProveedorEntity entity)
   {
       return persistence.update(entity);
   }
   
   public void deleteProveedor(Long id)
   {
       persistence.delete(id);
   }
   public void agregarPagoProveedor(PagoProveedorEntity pago, PedidoProveedorEntity pedido){
       
       
       pago.setPedidoProveedor(pedido);
       createProveedor(pago);
   }
   
   public void deletePago (PagoProveedorEntity entity){
       
       entity.setPedidoProveedor(null);
       deleteProveedor(entity.getId());
   }
   
   
   
   
    
}

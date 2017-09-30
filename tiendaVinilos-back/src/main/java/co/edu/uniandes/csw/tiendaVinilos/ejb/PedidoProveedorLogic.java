/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.PedidoProveedorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.saenz11
 */
@Stateless
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
   
   public void agregarPedidoProveedor(PedidoClienteEntity pedidoCliente , PedidoProveedorEntity pedidoProveedor)
   {
       pedidoProveedor.setPedidoCliente(pedidoCliente);
       createProveedor(pedidoProveedor);
   }
   
   public void deletePedidoProveedor(PedidoClienteEntity pedidoCliente , PedidoProveedorEntity pedidoProveedor)
   {
       pedidoProveedor.setPedidoCliente(null);
       deleteProveedor(pedidoProveedor.getId());
   }
   
   public PagoProveedorEntity getPagoProveedor(Long id)
   {
       PedidoProveedorEntity ent = getProveedor(id);
       
       return ent.getPagoProveedor();
   }
           }

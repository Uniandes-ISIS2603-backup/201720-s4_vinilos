/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.PedidoProveedorPersistence;
import java.util.LinkedList;
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
   
   public PedidoProveedorEntity getPedidoProveedor(Long id)
   {
       return persistence.find(id);
   }
   
   public PedidoProveedorEntity updatePedidoProveedor(PedidoProveedorEntity entity)
   {
       return persistence.update(entity);
   }
   
   public void deletePedidoProveedor(Long id)
   {
       persistence.delete(id);
   }
   
   public void agregarPedidoProveedor(PedidoClienteEntity pedidoCliente , PedidoProveedorEntity pedidoProveedor, ViniloEntity vinilo)
   {
       pedidoProveedor.setPedidoCliente(pedidoCliente);
       pedidoProveedor.setProveedor(vinilo.getProveedor());
       
       List<ViniloEntity> lista = new LinkedList<>();
   
               lista.add(vinilo);

       pedidoProveedor.setViniloEntity(lista);
       createProveedor(pedidoProveedor);
        vinilo.getProveedor().getPedidos().add(pedidoProveedor);
       vinilo.getProveedor().setPedidos( vinilo.getProveedor().getPedidos());
        
   }
   
   public void deletePedidoProveedor(PedidoClienteEntity pedidoCliente , PedidoProveedorEntity pedidoProveedor)
   {
       pedidoProveedor.setPedidoCliente(null);
       deletePedidoProveedor(pedidoProveedor.getId());
   }
   
   public PagoProveedorEntity getPagoProveedor(Long id)
   {
       PedidoProveedorEntity ent = getPedidoProveedor(id);
       
       return ent.getPagoProveedor();
   }
           }

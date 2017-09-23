/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.ProveedorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.ruiz
 */
@Stateless
public class ProveedorLogic {
    
    @Inject ProveedorPersistence persistence;
    
    
    public ProveedorEntity createProveedor(ProveedorEntity entity)
    {
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



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.FeedBackPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.ruiz
 */
@Stateless
public class FeedBackLogic {
    
     @Inject FeedBackPersistence persistence;
     
     public ProveedorEntity getProveedor(Long id)
     {
         return persistence.find(id).getProveedor();
     }
     
      public FeedBackEntity createFeedBack(FeedBackEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
   public List<FeedBackEntity> getAll()
   {
       return persistence.findAll();
   }
   
   public FeedBackEntity getFeedBack(Long id)
   {
       return persistence.find(id);
   }
   
   public FeedBackEntity updateFeedBack(FeedBackEntity entity)
   {
       return persistence.update(entity);
   }
   
   public void deleteFeedBack(Long id)
   {
       persistence.delete(id);
   }
   
}

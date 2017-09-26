/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.CarroComprasPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author cs.gomez
 */
public class CarroComprasLogic {
    
     @Inject 
     CarroComprasPersistence persistence;
     
     
     
    public CarroComprasEntity createCarroCompras(CarroComprasEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
   public List<CarroComprasEntity> getAllCarroCompras()
   {
       List<CarroComprasEntity> carro = persistence.findAll();
       return carro;
   }
   
   public CarroComprasEntity getCarroCompras(Long id)
   {
       return persistence.find(id);
   }
   
   public CarroComprasEntity updateCarroCompras(Long id, CarroComprasEntity entity)
   {
       return persistence.update(entity);
   }
   
   public void deleteCarroCompras(Long id)
   {
       persistence.delete(id);
   
   }
   
}

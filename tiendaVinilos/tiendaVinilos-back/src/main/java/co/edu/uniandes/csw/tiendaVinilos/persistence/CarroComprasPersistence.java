/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import java.util.List;
import javax.ejb.Stateless;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author cs.gomez
 */
@Stateless
public class CarroComprasPersistence {
    
    private static final Logger 
    LOGGER = Logger.getLogger(DefaultPersistence.class.getName());
    @PersistenceContext(unitName = "tiendaVinilosPU")
    protected EntityManager em;
    
    public CarroComprasEntity create(CarroComprasEntity entity) {
        
        LOGGER.info("Creando un CarroComprasEntity nuevo");
        em.persist(entity);
        LOGGER.info("Creando un CarroComprasEntity nuevo");
        return entity;
    }
    
    public CarroComprasEntity uptade(CarroComprasEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long id){
        em.remove(id);
    }
    
    public CarroComprasEntity find(Long id){
        return em.find(CarroComprasEntity.class, id);
    }
    
    
    public List<CarroComprasEntity> findAll() {
        TypedQuery query = em.createQuery("select u from DefaultEntity u", CarroComprasEntity.class);
        return query.getResultList();
    }

    
    
}

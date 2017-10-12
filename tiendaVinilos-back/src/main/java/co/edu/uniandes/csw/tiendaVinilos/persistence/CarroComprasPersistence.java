/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jc.ruiz
 */
@Stateless
public class CarroComprasPersistence {
    
    @PersistenceContext(unitName = "tiendaVinilosPU")
    protected EntityManager em;

    public CarroComprasEntity create(CarroComprasEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public CarroComprasEntity update(CarroComprasEntity entity)
    {
       return em.merge(entity);
    }
    
    public CarroComprasEntity find (Long id)
    {
        return em.find(CarroComprasEntity.class, id);
    }        
    
    public void delete(Long id)
    {
        em.remove(find(id));
    }
    
    public List<CarroComprasEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from CarroComprasEntity u", CarroComprasEntity.class);
        return query.getResultList();
    }
}

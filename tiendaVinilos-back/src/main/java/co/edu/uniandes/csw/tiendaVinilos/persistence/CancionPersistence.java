/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
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
public class CancionPersistence {
    
    @PersistenceContext(unitName = "tiendaVinilosPU")
    protected EntityManager em;

    public CancionEntity create(CancionEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public CancionEntity update(CancionEntity entity)
    {
       return em.merge(entity);
    }
    
    public CancionEntity find (Long id)
    {
        return em.find(CancionEntity.class, id);
    }        
    
    public void delete(Long id)
    {
        em.remove(find(id));
    }
    
    public List<CancionEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from CancionEntity u", CancionEntity.class);
        return query.getResultList();
    }
}

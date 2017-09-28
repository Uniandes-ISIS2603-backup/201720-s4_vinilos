/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
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
public class ArtistaPersistence {
    
    @PersistenceContext(unitName = "tiendaVinilosPU")
    protected EntityManager em;

    public ArtistaEntity create(ArtistaEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public ArtistaEntity update(ArtistaEntity entity)
    {
       return em.merge(entity);
    }
    
    public ArtistaEntity find (Long id)
    {
        return em.find(ArtistaEntity.class, id);
    }        
    
    public void delete(Long id)
    {
        em.remove(find(id));
    }
    
    public List<ArtistaEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from ArtistaEntity u", ArtistaEntity.class);
        return query.getResultList();
    }
}

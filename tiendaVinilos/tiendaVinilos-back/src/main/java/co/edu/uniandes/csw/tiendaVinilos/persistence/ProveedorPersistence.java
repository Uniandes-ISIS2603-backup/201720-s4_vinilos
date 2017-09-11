/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
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
public class ProveedorPersistence {
    
    @PersistenceContext(unitName = "tiendaVinilosPU")
    protected EntityManager em;

    public ProveedorEntity create(ProveedorEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public ProveedorEntity update(ProveedorEntity entity)
    {
       return em.merge(entity);
    }
    
    public ProveedorEntity find (Long id)
    {
        return em.find(ProveedorEntity.class, id);
    }        
    
    public void delete(Long id)
    {
        em.remove(find(id));
    }
    
    public List<ProveedorEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from ProveedorEntity u", ProveedorEntity.class);
        return query.getResultList();
    }
    
    public ProveedorEntity findByEmail(String email) {
       
        TypedQuery<ProveedorEntity> q
                = em.createQuery("select u from ProveedorEntity u where u.email = :email", ProveedorEntity.class);
        q = q.setParameter("email", email);
        
       List<ProveedorEntity> proveedoresEmail = q.getResultList();
        if (proveedoresEmail.isEmpty() ) {
            return null; 
        } else {
            return proveedoresEmail.get(0);
        }
    }
    
 }

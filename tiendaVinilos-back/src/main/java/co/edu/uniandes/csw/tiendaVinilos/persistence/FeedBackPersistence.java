
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;

//~--- JDK imports ------------------------------------------------------------

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
public class FeedBackPersistence {
    @PersistenceContext(unitName = "tiendaVinilosPU")
    protected EntityManager em;

    public FeedBackEntity create(FeedBackEntity entity) {
        em.persist(entity);

        return entity;
    }

    public FeedBackEntity update(FeedBackEntity entity) {
        return em.merge(entity);
    }

    public FeedBackEntity find(Long id) {
        return em.find(FeedBackEntity.class, id);
    }

    public void delete(Long id) {
        em.remove(find(id));
    }

    public List<FeedBackEntity> findAll() {
        TypedQuery query = em.createQuery("select u from FeedBackEntity u", FeedBackEntity.class);

        return query.getResultList();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

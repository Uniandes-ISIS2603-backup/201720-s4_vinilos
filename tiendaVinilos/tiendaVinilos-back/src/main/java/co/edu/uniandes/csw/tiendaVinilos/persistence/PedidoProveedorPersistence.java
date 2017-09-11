/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author s.saenz11
 */

@Stateless
public class PedidoProveedorPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(PedidoProveedorPersistence.class.getName());

    @PersistenceContext(unitName = "tiendaVinilosPU")
    protected EntityManager em;

    public PedidoProveedorEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando el pedido con id={0}", id);
        return em.find(PedidoProveedorEntity.class, id);
    }

   

    public List<PedidoProveedorEntity> findAll() {
        LOGGER.info("Consultando todos los pedidos");
        Query q = em.createQuery("select u from PedidoProveedorEntity u");
        return q.getResultList();
    }

    public PedidoProveedorEntity create(PedidoProveedorEntity entity) {
        LOGGER.info("Creando un employee nuevo");
        em.persist(entity);
        LOGGER.info("Employee creado");
        return entity;
    }

    public PedidoProveedorEntity update(PedidoProveedorEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando el pedido con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando pedido con id={0}", id);
        PedidoProveedorEntity entity = em.find(PedidoProveedorEntity.class, id);
        em.remove(entity);
    }
}


/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;

//~--- JDK imports ------------------------------------------------------------

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
public class PagoProveedorPersistennce {
    private static final Logger LOGGER = Logger.getLogger(PedidoProveedorPersistence.class.getName());
    @PersistenceContext(unitName = "tiendaVinilosPU")
    protected EntityManager     em;

    public PagoProveedorEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando el pago con id={0}", id);

        return em.find(PagoProveedorEntity.class, id);
    }

    public List<PagoProveedorEntity> findAll() {
        LOGGER.info("Consultando todos los pagos");

        Query q = em.createQuery("select u from PagoProveedorEntity u");

        return q.getResultList();
    }

    public PagoProveedorEntity create(PagoProveedorEntity entity) {
        LOGGER.info("Creando un pago nuevo");
        em.persist(entity);
        LOGGER.info("pago creado");

        return entity;
    }

    public PagoProveedorEntity update(PagoProveedorEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando el pago con id={0}", entity.getId());

        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando pago con id={0}", id);

        PagoProveedorEntity entity = em.find(PagoProveedorEntity.class, id);

        em.remove(entity);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

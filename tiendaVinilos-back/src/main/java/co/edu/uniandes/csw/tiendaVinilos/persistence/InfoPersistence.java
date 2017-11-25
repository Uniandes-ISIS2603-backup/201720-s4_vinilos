package co.edu.uniandes.csw.tiendaVinilos.persistence;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.InfoEntity;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class InfoPersistence {
    private static final Logger LOGGER = Logger.getLogger(InfoPersistence.class.getName());
    @PersistenceContext(unitName = "tiendaVinilosPU")
    protected EntityManager     em;

    public InfoEntity create(InfoEntity entity) {
        LOGGER.info("Creando un info nuevo");
        em.persist(entity);
        LOGGER.info("Info creado");

        return entity;
    }

    public InfoEntity update(InfoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando info con id={0}", entity.getId());

        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando info con id={0}", id);

        InfoEntity entity = em.find(InfoEntity.class, id);

        em.remove(entity);
    }

    public InfoEntity find(Long viniloid, Long infoid) {
        TypedQuery<InfoEntity> q =
            em.createQuery("select p from InfoEntity p where (p.vinilo.id = :viniloid) and (p.id = :infoid)",
                           InfoEntity.class);

        q.setParameter("viniloid", viniloid);
        q.setParameter("infoid", infoid);

        List<InfoEntity> results = q.getResultList();
        InfoEntity       info    = null;

        if (results == null) {
            info = null;
        } else if (results.isEmpty()) {
            info = null;
        } else if (results.size() >= 1) {
            info = results.get(0);
        }

        return info;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

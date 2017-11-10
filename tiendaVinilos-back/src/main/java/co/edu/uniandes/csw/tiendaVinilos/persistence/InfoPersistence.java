/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.InfoEntity;
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
    protected EntityManager em;

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
        TypedQuery<InfoEntity> q = em.createQuery("select p from InfoEntity p where (p.vinilo.id = :viniloid) and (p.id = :infoid)", InfoEntity.class);
        q.setParameter("viniloid", viniloid);
        q.setParameter("infoid", infoid);
        List<InfoEntity> results = q.getResultList();
        InfoEntity review = null;
        if (results == null) {
            review = null;
        } else if (results.isEmpty()) {
            review = null;
        } else if (results.size() >= 1) {
            review = results.get(0);
        }

        return review;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jp.monsalvo
 */
@Stateless
public class ViniloPersistence {
    private static final Logger LOGGER = Logger.getLogger(ViniloPersistence.class.getName());

    @PersistenceContext(unitName = "tiendaVinilosPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Vinilo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ViniloEntity create(ViniloEntity entity) {
        LOGGER.info("Creando un vinilo nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir el vinilo en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un vinilo nuevo");
        return entity;
    }

    /**
     * Actualiza un Vinilo.
     *
     * @param entity: el Vinilo que viene con los nuevos cambios. Por
     * ejemplo el codigo pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un Vinilo con los cambios aplicados.
     */
    public ViniloEntity update(ViniloEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Vinilo con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Vinilo con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un Vinilo de la base de datos recibiendo como argumento el id
     * del Vinilo
     *
     * @param id: id correspondiente al Vinilo a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Vinilo con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public ViniloEntity find(Long id) para obtener el Vinilo a borrar.
        ViniloEntity entity = em.find(ViniloEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from viniloEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun Vinilo con el id que se envía de argumento
     *
     * @param id: id correspondiente al Vinilo buscado.
     * @return un Vinilo.
     */
    public ViniloEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Vinilo con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from ViniloEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(ViniloEntity.class, id);
    }

    /**
     * Devuelve todas los Vinilos de la base de datos.
     *
     * @return una lista con todos los Vinilos que encuentre en la base de
     * datos, "select u from ViniloEntity u" es como un "select * from
     * ViniloEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<ViniloEntity> findAll() {
        LOGGER.info("Consultando todas las Vinilos");
        // Se crea un query para buscar todos los Vinilos en la base de datos.
        TypedQuery query = em.createQuery("select u from ViniloEntity u", ViniloEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Vinilos.
        return query.getResultList();
    }

    /**
     * Busca si hay algun Vinilo con el nombre que se envía de argumento
     *
     * @param name: nombre del Vinilo que se está buscando
     * @return null si no existe ningun Vinilo con el nombre del
     * argumento. Si existe alguno devuelve el primero.
     */
    public ViniloEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando Vinilo por nombre ", name);

        // Se crea un query para buscar Vinilos con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ViniloEntity e where e.name = :name", ViniloEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<ViniloEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
}

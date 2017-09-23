/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author jp.monsalvo
 */
@Stateless
public class InfoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(InfoPersistence.class.getName());

    @PersistenceContext(unitName = "tiendaVinilosPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Info que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public InfoEntity create(InfoEntity entity) {
        LOGGER.info("Creando un Info nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir el Info en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un Info nuevo");
        return entity;
    }

    /**
     * Actualiza un Info.
     *
     * @param entity: el Info que viene con los nuevos cambios. Por
     * ejemplo el codigo pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un Info con los cambios aplicados.
     */
    public InfoEntity update(InfoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Info con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Info con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un Info de la base de datos recibiendo como argumento el id
     * del Info
     *
     * @param id: id correspondiente al Info a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Info con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public InfoEntity find(Long id) para obtener el Info a borrar.
        InfoEntity entity = em.find(InfoEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from InfoEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun Info con el id que se envía de argumento
     *
     * @param id: id correspondiente al Info buscado.
     * @return un Info.
     */
    public InfoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Info con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from InfoEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(InfoEntity.class, id);
    }

    /**
     * Devuelve todas los Infos de la base de datos.
     *
     * @return una lista con todos los Infos que encuentre en la base de
     * datos, "select u from InfoEntity u" es como un "select * from
     * InfoEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<InfoEntity> findAll() {
        LOGGER.info("Consultando todas las Infos");
        // Se crea un query para buscar todos los Infos en la base de datos.
        TypedQuery query = em.createQuery("select u from InfoEntity u", InfoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Infos.
        return query.getResultList();
    }

    /**
     * Busca si hay algun Info con el nombre que se envía de argumento
     *
     * @param name: nombre del Info que se está buscando
     * @return null si no existe ningun Info con el nombre del
     * argumento. Si existe alguno devuelve el primero.
     */
    public InfoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando Info por nombre ", name);

        // Se crea un query para buscar Infos con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From InfoEntity e where e.name = :name", InfoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<InfoEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

}

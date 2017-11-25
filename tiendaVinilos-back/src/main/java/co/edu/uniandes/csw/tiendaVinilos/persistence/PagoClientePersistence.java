
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoClienteEntity;

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
 * @author mj.jaime10
 */
@Stateless
public class PagoClientePersistence {
    private static final Logger LOGGER = Logger.getLogger(PagoClientePersistence.class.getName());
    @PersistenceContext(unitName = "tiendaVinilosPU")
    protected EntityManager     em;

    /**
     * Crear un PagoCliente
     *
     * @param entity objeto PagoClienteEntity que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PagoClienteEntity create(PagoClienteEntity entity) {
        LOGGER.info("Creando un PagoCliente nuevo");

        /*
         *  Note que hacemos uso de un método propio de EntityManager para persistir el PagoCliente en la base de datos.
         * Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un PagoCliente nuevo");

        return entity;
    }

    /**
     * Actualiza un PagoCliente.
     *
     * @param entity: el PagoCliente que viene con los nuevos cambios. Por
     * ejemplo el codigo pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un PagoCliente con los cambios aplicados.
     */
    public PagoClienteEntity update(PagoClienteEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando PagoCliente con id={0}", entity.getId());

        /*
         *  Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
         * la PagoCliente con los cambios, esto es similar a
         * "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un PagoCliente de la base de datos recibiendo como argumento el id
     * del PagoCliente
     *
     * @param id: id correspondiente al PagoCliente a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando PagoCliente con id={0}", id);

        // Se hace uso de mismo método que esta explicado en public PagoClienteEntity find(Long id) para obtener el PagoCliente a borrar.
        PagoClienteEntity entity = em.find(PagoClienteEntity.class, id);

        /*
         *  Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         * EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         * Es similar a "delete from PedidoClienteEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.
         */
        em.remove(entity);
    }

    /**
     * Busca si hay algun PagoCliente con el id que se envía de argumento
     *
     * @param id: id correspondiente al PagoCliente buscado.
     * @return un PagoCliente.
     */
    public PagoClienteEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando PagoCliente con id={0}", id);

        /*
         *  Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento
         * el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
         * Suponga que es algo similar a "select * from PagoClienteEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(PagoClienteEntity.class, id);
    }

    /**
     * Devuelve todas los PagoCliente de la base de datos.
     *
     * @return una lista con todos los PagoCliente que encuentre en la base de
     * datos, "select u from PagoClienteEntity u" es como un "select * from
     * PagoClienteEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<PagoClienteEntity> findAll() {
        LOGGER.info("Consultando todas las PagoCliente");

        // Se crea un query para buscar todos los PagoCliente en la base de datos.
        TypedQuery query = em.createQuery("select u from PagoClienteEntity u", PagoClienteEntity.class);

        // Note que en el query se hace uso del método getResultList() que obtiene una lista de PagoCliente.
        return query.getResultList();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

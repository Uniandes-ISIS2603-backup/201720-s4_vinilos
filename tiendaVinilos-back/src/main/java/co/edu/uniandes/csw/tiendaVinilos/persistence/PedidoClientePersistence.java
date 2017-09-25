/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
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
public class PedidoClientePersistence 
{
    private static final Logger LOGGER = Logger.getLogger(PedidoClientePersistence.class.getName());
    
    @PersistenceContext(unitName="tiendaVinilosPU")
    protected EntityManager em;   
    
    /** Crear un PedidoCliente
     *
     * @param entity objeto PedidoClienteEntity que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PedidoClienteEntity create(PedidoClienteEntity entity) {
        LOGGER.info("Creando un pedidoCliente nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir el PedidoCliente en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un PedidoCliente nuevo");
        return entity;
    }

    /**
     * Actualiza un PedidoCliente.
     *
     * @param entity: el PedidoCliente que viene con los nuevos cambios. Por
     * ejemplo el codigo pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un PedidoCliente con los cambios aplicados.
     */
    public PedidoClienteEntity update(PedidoClienteEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando PedidoCliente con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la PedidoCliente con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un PedidoCliente de la base de datos recibiendo como argumento el id
     * del PedidoCliente
     *
     * @param id: id correspondiente al PedidoCliente a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando PedidoCliente con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public PedidoClienteEntity find(Long id) para obtener el PedidoCliente a borrar.
        PedidoClienteEntity entity = em.find(PedidoClienteEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from PedidoClienteEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun PedidoCliente con el id que se envía de argumento
     *
     * @param id: id correspondiente al PedidoCliente buscado.
     * @return un PedidoCliente.
     */
    public PedidoClienteEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando PedidoCliente con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from PedidoClienteEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(PedidoClienteEntity.class, id);
    }

    /**
     * Devuelve todas los PedidoCliente de la base de datos.
     *
     * @return una lista con todos los PedidoCliente que encuentre en la base de
     * datos, "select u from PedidoClienteEntity u" es como un "select * from
     * PedidoClienteEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<PedidoClienteEntity> findAll() {
        LOGGER.info("Consultando todas las PedidoCliente");
        // Se crea un query para buscar todos los PedidoCliente en la base de datos.
        TypedQuery query = em.createQuery("select u from PedidoClienteEntity u", PedidoClienteEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de PedidoCliente.
        return query.getResultList();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.PagoClientePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author mj.jaime10
 */
@Stateless
public class PagoClienteLogic {
    
    private static final Logger LOGGER = Logger.getLogger(PagoClienteLogic.class.getName());
    
     @Inject
    private PagoClientePersistence persistence;
     
     /**
     *
     * @param entity
     * @return entity creada
     * @throws BusinessLogicException
     */
    public PagoClienteEntity createPedido(PagoClienteEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de pago");
        // Verifica la regla de negocio que dice (asociasiones)
        //FALTA: excepcion
        // Invoca la persistencia para crear la editorial
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de pago");
        return entity;
    }
    
    /**
     *
     * Obtener un pedido por medio de su id.
     * 
     * @param id: id del pedido para ser buscado.
     * @return el pedido solicitado por medio de su id.
     */
    public PagoClienteEntity getPago(Long id) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar pago con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        PagoClienteEntity pago = persistence.find(id);
        if (pago == null) {
            throw new BusinessLogicException( "El pago con el id " + id +" no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar pago con id={0}", id);
        return pago;
    }
    
    /**
     *
     * Actualizar un pedido.
     *
     * @param id: id del pedido para buscarlo en la base de datos.
     * @param entity: pedido con los cambios para ser actualizado, puede ser la direccion o el telefono de contacto.
     * @return el pedido con los cambios actualizados en la base de datos.
     */
    public PagoClienteEntity updatePago(Long id, PagoClienteEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar pago con id={0}", id);
        PagoClienteEntity pago = persistence.find(id);
        if (pago == null) {
            throw new BusinessLogicException( "El pago con el id " + id +" no existe");
        }
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        PagoClienteEntity newEntity = persistence.update(entity);
        
        LOGGER.log(Level.INFO, "Termina proceso de actualizar pago con id={0}", entity.getId());
        return newEntity;
    }
   
    
    /**
     * Borrar un pedido
     *
     * @param id: id del pedido a borrar
     */
    public void deletePago(Long id) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de borrar pago con id={0}", id);
        PagoClienteEntity pago = persistence.find(id);
        if (pago == null) {
            throw new BusinessLogicException( "El pago con el id " + id +" no existe");
        }
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        pago.setPedido(null);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar pago con id={0}", id);
    }
 
    /**
     * 
     * Obtener todos los pedidos existentes en la base de datos.
     *
     * @return una lista de pedidos.
     */
    public List<PagoClienteEntity> getPagos() {
        LOGGER.info("Inicia proceso de consultar todos los pagos");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<PagoClienteEntity> pagos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los pagos");
        return pagos;
    }
    
}
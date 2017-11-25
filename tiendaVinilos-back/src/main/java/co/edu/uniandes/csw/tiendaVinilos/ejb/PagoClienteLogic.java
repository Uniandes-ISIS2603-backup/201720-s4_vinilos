
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.PagoClientePersistence;
import co.edu.uniandes.csw.tiendaVinilos.persistence.PedidoClientePersistence;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import javax.inject.Inject;

import javax.ws.rs.WebApplicationException;

/**
 *
 * @author mj.jaime10
 */
@Stateless
public class PagoClienteLogic {
    private static final Logger      LOGGER = Logger.getLogger(PagoClienteLogic.class.getName());
    @Inject
    private PagoClientePersistence   persistence;
    @Inject
    private PedidoClientePersistence persistencePedido;

    /**
     *
     * @param entity
     * @param idPedido
     * @return entity creada
     * @throws BusinessLogicException
     */
    public PagoClienteEntity createPago(Long idPedido, PagoClienteEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de pago");

        // Verifica la regla de negocio que dice (asociasiones)
        // FALTA: excepcion
        // Invoca la persistencia para crear la editorial
        PedidoClienteEntity pedido = persistencePedido.find(idPedido);

        pedido.setPago(entity);
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
    public PagoClienteEntity getPago(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar pago con id=", id);

        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        PagoClienteEntity pago = persistence.find(id);

        LOGGER.log(Level.INFO, "Termina proceso de consultar pago con id=", id);

        return pago;
    }

    /**
     *
     * Actualizar un pago.
     *
     * @param idPedido: id del pedido para buscarlo en la base de datos.
     * @param entity: pedido con los cambios para ser actualizado, puede ser la direccion o el telefono de contacto.
     * @param idPago
     * @return el pedido con los cambios actualizados en la base de datos.
     */
    public PagoClienteEntity updatePago(Long idPedido, Long idPago, PagoClienteEntity entity)
            throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar pago con id={0}", idPedido);

        PedidoClienteEntity pedido = persistencePedido.find(idPedido);
        PagoClienteEntity   pago   = persistence.find(idPago);

        if (pago == null) {
            throw new BusinessLogicException("El pago con el id " + idPago + " no existe");
        }

        pedido.setPago(entity);

        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        PagoClienteEntity newEntity = persistence.update(entity);

        LOGGER.log(Level.INFO, "Termina proceso de actualizar pago con id=", entity.getId());

        return newEntity;
    }

    /**
     * Borrar un pedido
     *
     * @param idPago: id del pedido a borrar
     * @param idPedido
     */
    public void deletePago(Long idPedido, Long idPago) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar pago con id=", idPago);

        PedidoClienteEntity pedido = persistencePedido.find(idPedido);

        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        pedido.setPago(null);
        persistence.delete(idPago);
        LOGGER.log(Level.INFO, "Termina proceso de borrar pago con id=", idPago);
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


//~ Formatted by Jindent --- http://www.jindent.com

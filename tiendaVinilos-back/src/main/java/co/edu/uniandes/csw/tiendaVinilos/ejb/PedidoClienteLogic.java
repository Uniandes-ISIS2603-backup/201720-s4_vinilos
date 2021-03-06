
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.PedidoClientePersistence;
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
public class PedidoClienteLogic 
{
    private static final Logger LOGGER = Logger.getLogger(PedidoClienteLogic.class.getName());
    
    @Inject
    private PedidoClientePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

     
    /**
     *
     * @param entity
     * @param usuario
     * @return entity creada
     * @throws BusinessLogicException
     */
    public PedidoClienteEntity createPedido(PedidoClienteEntity entity, UsuarioEntity usuario) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de pedido");
        // Verifica la regla de negocio que dice (asociasiones)
        //FALTA: excepcion
        // Invoca la persistencia para crear la editorial
        PedidoClienteEntity otherEntity = persistence.create(entity);
        entity.setUsuario(usuario);
        LOGGER.info("Termina proceso de creación de pedido");
        return otherEntity;
    }
    
    /**
     *
     * Obtener un pedido por medio de su id.
     * 
     * @param id: id del pedido para ser buscado.
     * @return el pedido solicitado por medio de su id.
     */
    public PedidoClienteEntity getPedido(Long id) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar pedido con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        PedidoClienteEntity pedido = persistence.find(id);
        
        if (pedido == null) {
            throw new BusinessLogicException( "El Pedido con el id " + id +" no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar editorial con id={0}", id);
        return pedido;
    }
    
    /**
     *
     * Actualizar un pedido.
     *
     * @param id: id del pedido para buscarlo en la base de datos.
     * @param entity: pedido con los cambios para ser actualizado, puede ser la direccion o el telefono de contacto.
     * @throws BusinessLogicException
     * @param usuario
     * @return el pedido con los cambios actualizados en la base de datos.
     */
    public PedidoClienteEntity updatePedido(Long id, PedidoClienteEntity entity, UsuarioEntity usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar pedido con id={0}", id);
        PedidoClienteEntity pedido = persistence.find(id);
        if (pedido == null) {
            throw new BusinessLogicException( "El Pedido con el id " + id +" no existe");
        }
        if(!((pedido.getEstado()).equals("Aceptado")) && !((pedido.getEstado()).equals("Por Confirmar")) 
                && ( !((entity.getDireccion()).equals(pedido.getDireccion())) || 
                entity.getTelefono()!= pedido.getTelefono() ) )
        {
            throw new BusinessLogicException("La información del pedido no puede ser modificado. El estado del pedido deber ser 'Aceptado' "
                    + "o 'Por Confirmar', de lo contrario es posible modificar el pedido.");
        }
        
        if(!((pedido.getEstado()).equals("Aceptado")) && !((pedido.getEstado()).equals("Por Confirmar")) 
                && entity.getEstado().equals("Cancelado") )
        {
            throw new BusinessLogicException("El pedido no puede ser cancelado. El estado del pedido deber ser 'Aceptado' "
                    + "o 'Por Confirmar', de lo contrario es posible cancelar el pedido.");
        }
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        PedidoClienteEntity newEntity = persistence.update(entity);
        newEntity.setUsuario(usuario);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar pedido con id={0}", entity.getId());
        return newEntity;
    }
    
    /**
     *
     * Cancelar un pedido.
     *
     * @param id: id del pedido para buscarlo en la base de datos.
     * @param entity: pedido con los cambios para ser actualizado, puede ser la direccion o el telefono de contacto.
     * @throws BusinessLogicException
     * @param usuario
     * @return el pedido con los cambios actualizados en la base de datos.
     */
    public PedidoClienteEntity cancelarPedido(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar pedido con id={0}", id);
        PedidoClienteEntity pedido = persistence.find(id);
        System.out.print("-------------------------- HOOLAAAAAAAA -----");
        if (pedido == null) {
            throw new BusinessLogicException( "El Pedido con el id " + id +" no existe");
        }
            System.out.print("-------------------------- HOOLAAAAAAAA XXX2222222 -----");
        UsuarioEntity usuario = pedido.getUsuario();
         
        if(!((pedido.getEstado()).equals("Aceptado")) && !((pedido.getEstado()).equals("Por Confirmar")))
        {
            throw new BusinessLogicException("El pedido no puede ser cancelado. El estado del pedido deber ser 'Rechazado' "
                    + "'Cancelado' o 'Entregado', de lo contrario es posible cancelar el pedido.");
        }
        System.out.print("-------------------------- HOOLAAAAAAAA XXX3333333 -----");
        pedido.setEstado("CANCELADO");
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        PedidoClienteEntity newEntity = persistence.update(pedido);
        newEntity.setUsuario(usuario);
        System.out.print("-------------------------- HOOLAAAAAAAA XXX444444 -----");
        LOGGER.log(Level.INFO, "Termina proceso de actualizar pedido con id={0}", pedido.getId());
        return newEntity;
    }
   
    
    /**
     * Borrar un pedido
     *
     * @param id: id del pedido a borrar
     */
    public void deletePedido(Long id) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de borrar pedido con id={0}", id);
        PedidoClienteEntity pedido = persistence.find(id);
        if (pedido == null) {
            throw new BusinessLogicException( "El Pedido con el id " + id +" no existe");
        }
         if(!((pedido.getEstado()).equals("Rechazado")) && !((pedido.getEstado()).equals("CANCELADO")) && !((pedido.getEstado()).equals("Entregado")) && !((pedido.getEstado()).equals("Por Confirmar")))
        {
            throw new BusinessLogicException("El pedido no puede ser eliminado. El estado del pedido deber ser 'Rechazado' "
                    + "'CANCELADO', 'Entregado' o 'Por Confirmar', de lo contrario es posible eliminar el pedido.");
        }
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        pedido.setUsuario(null);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar pedido con id={0}", id);
    }
 
    /**
     * 
     * Obtener todos los pedidos existentes en la base de datos.
     *
     * @return una lista de pedidos.
     */
    public List<PedidoClienteEntity> getPedidos() {
        LOGGER.info("Inicia proceso de consultar todos los pedidos");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<PedidoClienteEntity> pedidos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los pedidos");
        return pedidos;
    }
    
    public List<PedidoProveedorEntity>getPedidoProveedor(Long id) throws BusinessLogicException
    {
        PedidoClienteEntity cli = getPedido(id);
        System.out.print("---------------------------------------- EL PEDIDO TIENE " + cli.getPedidoProveedor().size() + " pedidos de proveedor ");
        return cli.getPedidoProveedor();
    }
    
    public UsuarioEntity getUsuario(Long id) throws BusinessLogicException
    {
        PedidoClienteEntity cli = getPedido(id);
        return cli.getUsuario();
    }
    
}
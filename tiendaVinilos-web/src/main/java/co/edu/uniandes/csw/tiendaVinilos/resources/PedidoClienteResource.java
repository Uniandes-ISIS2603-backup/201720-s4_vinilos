/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.PedidoClienteDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoClienteLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.PedidoClientePersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author mj.jaime10
 */
@Path("pedidocliente")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PedidoClienteResource {
    
    @Inject
    PedidoClienteLogic pedidoLogic;// Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    private static final Logger LOGGER = Logger.getLogger(PedidoClientePersistence.class.getName());
    
    /**
     * POST http://localhost:8080/tiendaVinilos-web/api/pedidocliente Ejemplo
     * json: { "precio": 10000.00 } 
     *       { "estado": "Aceptado"} 
     *       { "fechaEstimada": 27/07/2017 }
     *       { "direccion": "14th st"}
     *       { "telefono": 2685578 }
     * 
     * @param pedido correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "pedidoClienteDetailDTO", "id": 1, "precio": 10000.00, "estado": "Aceptado", "fechaEstimada": 27/07/2017, 
     *   ["usuario": {"email": "correo@correo.org, "cantCompras": 2, "nombre": "Juan Pérez", "id": 2 }] 
     *   ["carroCompras": {"precioTotal": 10000.00, "id": 3}]
     *   ["pedidoProveedor": {"precio": 10000.00, "fechaEstimada": 27/07/2017, "id":4}] }
     * @throws BusinessLogicException
     */
    @POST
    public PedidoClienteDetailDTO createPedido(PedidoClienteDetailDTO pedido) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        PedidoClienteEntity pedidoEntity = pedido.toEntity();
        // Invoca la lógica para crear la editorial nueva
        PedidoClienteEntity nuevoPedido = pedidoLogic.createPedido(pedidoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new PedidoClienteDetailDTO(nuevoPedido);
    }
    
    /**
     * PUT http://localhost:8080/tiendaVinilos-web/api/pedidocliente/1 Ejemplo
     * json {"id": 1}
     *      { "precio": 10000.00 } 
     *      { "estado": "Aceptado"} 
     *      { "fechaEstimada": 27/07/2017 }
     *      [ "usuario":
     *        { "email": "correo@correo.org"}
     *        { "nombre": "Juan Pérez"}
     *        { "id": 1}
     *      ]
     *
     * @param id corresponde a la pedido a actualizar.
     * @param pedido corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return El pedido actualizado.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del pedido a actualizar se retorna un
     * 404 con el mensaje.
     *  En caso de que el estadod el pedido sea diferente de 'Aceptado', 
     * o 'Por Confirmar' se retorna un 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public PedidoClienteDetailDTO updatePedido(@PathParam("id") Long id, PedidoClienteDetailDTO pedido) throws BusinessLogicException {
        
        pedido.setId(id);
        return new PedidoClienteDetailDTO(pedidoLogic.updatePedido(id, pedido.toEntity()));
    }
  
    
    /**
     * DELETE http://localhost:8080/tiendaVinilos-web/api/pedidocliente/1
     *
     * @param id corresponde al pedido a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del pedido a actualizar se retorna un
     * 404 con el mensaje.
     * En caso de que el estadod el pedido sea diferente de 'Rechazado', 'Cancelado'
     * o 'Entregado' se retorna un 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePedido(@PathParam("id") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un pedido con id {0}", id);
        pedidoLogic.deletePedido(id);
    }
    
    /**
     * GET para un pedido
     * http://localhost:8080/tiendaVinilos-web/api/pedidocliente/1
     *
     * @param id corresponde al id del pedido buscado.
     * @return El pedido encontrado. Ejemplo: { "type":
     * "pedidoClienteDetailDTO", "id": 1, "precio": 10000.00, "estado": "Aceptado", "fechaEstimada": 27/07/2017, 
     *   ["usuario": {"email": "correo@correo.org, "cantCompras": 2, "nombre": "Juan Pérez", "id": 2 }] 
     *   ["carroCompras": {"precioTotal": 10000.00, "id": 3}]
     *   ["pedidoProveedor": {"precio": 10000.00, "fechaEstimada": 27/07/2017, "id":4}] }
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del pedido buscado se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public PedidoClienteDetailDTO getEditorial(@PathParam("id") Long id) throws BusinessLogicException {
        
        return new PedidoClienteDetailDTO(pedidoLogic.getPedido(id));
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.PagoClienteDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PagoClienteLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoClienteLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PagoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author mj.jaime10
 */
@Path("pedidocliente/{id: \\d+}/pagocliente")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PagoPedidoClienteResource {
    
     @Inject
    PagoClienteLogic pagoLogic;// Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
     @Inject
     PedidoClienteLogic pedidoLogic;
    
    
    /**
     * 
     * @param pago correponde a la representación java del objeto json
     * @param id
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. E
     * @throws BusinessLogicException
     */
    @POST
    public PagoClienteDetailDTO createPago(@PathParam("id") Long id, PagoClienteDetailDTO pago) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        PagoClienteEntity pagoEntity = pago.toEntity();
        // Invoca la lógica para crear la editorial nueva
        pedidoLogic.getPedido(id);
        PagoClienteEntity nuevoPedido = pagoLogic.createPedido(pagoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new PagoClienteDetailDTO(nuevoPedido);
    }
    
   
  
    
    /**
     * DELETE http://localhost:8080/tiendaVinilos-web/api/pagocliente/1
     *
     * @param id corresponde al pedido al cual pertenece el pago
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del pedido a actualizar se retorna un
     * 404 con el mensaje.
     * En caso de que el estadod el pedido sea diferente de 'Rechazado', 'Cancelado'
     * o 'Entregado' se retorna un 404 con el mensaje.
     *
     */
    @DELETE
    public void deletePago(@PathParam("id") Long id) throws BusinessLogicException {
        pagoLogic.deletePago(id);
    }
    
    /**
     * GET para un pago
     * http://localhost:8080/tiendaVinilos-web/api/pagocliente/1
     *
     * @param id corresponde al id del pedido buscado.
     * @return El pago encontrado.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del pedido buscado se retorna un 404 con
     * el mensaje.
     */
    @GET
    public PagoClienteDetailDTO getPago(@PathParam("id") Long id) throws BusinessLogicException {
        
        return new PagoClienteDetailDTO(pedidoLogic.getPedido(id).getPago());
    }
    
    /**
     * GET para un pago
     * http://localhost:8080/tiendaVinilos-web/api/pagocliente/1
     *
     *
     * @return Los pedidos 
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del pedido buscado se retorna un 404 con
     * el mensaje.
     */
   
    
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos EditorialDetailDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de editoriales en forma DTO (json)
     */
    private List<PagoClienteDetailDTO> listEntity2DetailDTO(List<PagoClienteEntity> entityList) {
        List<PagoClienteDetailDTO> list = new ArrayList<>();
        for(PagoClienteEntity entity : entityList) {
            list.add(new PagoClienteDetailDTO(entity));
        }
        return list;
    }
}

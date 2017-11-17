/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

import co.edu.uniandes.csw.dtos.PagoClienteDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PagoClienteLogic;
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
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author mj.jaime10
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PagoClienteResource {
    
     @Inject
    PagoClienteLogic pagoLogic;// Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    
    /**
     * 
     * @param idPedido 
     * @param pago correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. E
     * @throws BusinessLogicException
     */
     @POST
    public PagoClienteDetailDTO createPago(@PathParam("idPedido") Long idPedido, PagoClienteDetailDTO pago) throws BusinessLogicException {
        return new PagoClienteDetailDTO(pagoLogic.createPago(idPedido, pago.toEntity()));
    }
   
  
    
    /**
     * DELETE http://localhost:8080/tiendaVinilos-web/api/pagocliente/1
     *
     * @param idPedido corresponde al pedido a borrar.
     * @param idPago
     * @throws WebApplicationException
     *
     * En caso de no existir el id del pedido a actualizar se retorna un
     * 404 con el mensaje.
     * En caso de que el estadod el pedido sea diferente de 'Rechazado', 'Cancelado'
     * o 'Entregado' se retorna un 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{idPago: \\d+}")
    public void deletePago(@PathParam("idPedido") Long idPedido, @PathParam("idPago") Long idPago) throws WebApplicationException 
    {
        PagoClienteEntity entity = pagoLogic.getPago(idPago);
        if (entity == null) {
            throw new WebApplicationException("El recurso /pedido/" + idPedido + "/pago/" + idPago + " no existe.", 404);
        }
        pagoLogic.deletePago(idPedido, idPago);
    }
   
    
    /**
     * GET para un pago
     * http://localhost:8080/tiendaVinilos-web/api/pagocliente/1
     *
     * @param idPago corresponde al id del pedido buscado.
     * @param idPedido
     * @return El pago encontrado.
     * @throws WebApplicationException
     *
     * En caso de no existir el id del pedido buscado se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{idPago: \\d+}")
    public PagoClienteDetailDTO getPago(@PathParam("idPedido") Long idPedido, @PathParam("idPago") Long idPago) throws WebApplicationException {
        
        PagoClienteEntity entity = pagoLogic.getPago(idPago);
        if (entity == null) {
            throw new WebApplicationException("El recurso /pedido/" + idPedido + "/pago/" + idPago + " no existe.", 404);
        }
        return new PagoClienteDetailDTO(entity);
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
    @GET
    public List<PagoClienteDetailDTO> getPagos() throws BusinessLogicException {
        return listEntity2DetailDTO(pagoLogic.getPagos());
    }
    
    
    
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



//NOTA: Tuve que hacer la clase de esta forma, ya que de la forma correcta no logré hacerlo funcionar


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

import co.edu.uniandes.csw.dtos.PagoClienteDetailDTO;
import co.edu.uniandes.csw.dtos.PedidoClienteDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PagoClienteLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoClienteLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
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
@Path("pedido/{id: \\d+}/pago")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PedidoClientePagoClienteResource 
{
    @Inject
    PedidoClienteLogic pedidoLogic;
    
    @Inject
    PagoClienteLogic pagoLogic;
    
    @GET
    public PagoClienteDetailDTO getPagoDePedido(@PathParam("id") Long id) throws BusinessLogicException {
        
        PedidoClienteEntity pedido = pedidoLogic.getPedido(id);
        if(pedido == null )
        {
            throw new BusinessLogicException("El pedido con el id: " + id + " no existe.");
        }
        return new PagoClienteDetailDTO(pedido.getPago());
    }
    
    @POST
    public PedidoClienteDetailDTO createPagoCliente(PagoClienteDetailDTO pagoCliente,@PathParam("id") Long id) throws WebApplicationException, BusinessLogicException {
         if(pedidoLogic.getPedido(id) == null )
        {
            throw new WebApplicationException("El pedido con el id: " + id + " no existe.", 404);
        }
        pagoLogic.createPago(id, pagoCliente.toEntity());
        return new PedidoClienteDetailDTO(pedidoLogic.getPedido(id));
    }
    
    @PUT
    @Path("{idPago: \\d+}")
    public PedidoClienteDetailDTO updatePagoCliente(PagoClienteDetailDTO pagoCliente,@PathParam("id") Long id, @PathParam("idPago") Long idPago) throws BusinessLogicException {
        
         
        if(pedidoLogic.getPedido(id) == null )
        {
            throw new BusinessLogicException("El pedido con el id: " + id + " no existe.");
        }
        pagoLogic.updatePago(id, idPago, pagoCliente.toEntity());
        return new PedidoClienteDetailDTO(pedidoLogic.getPedido(id));
    }
    
    @DELETE
    @Path("{idPago: \\d+}")
    public void deletePagoCliente(PagoClienteDetailDTO pagoCliente,@PathParam("id") Long id, @PathParam("idPago") Long idPago) throws BusinessLogicException {
        
        if(pedidoLogic.getPedido(id) == null )
        {
            throw new BusinessLogicException("El pedido con el id: " + id + " no existe.");
        }
        pagoLogic.deletePago(idPago, id);
    }
    
}

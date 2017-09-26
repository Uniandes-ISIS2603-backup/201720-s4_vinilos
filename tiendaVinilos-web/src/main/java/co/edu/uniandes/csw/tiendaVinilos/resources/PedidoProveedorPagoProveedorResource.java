/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.PagoProveedorDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.dtos.PedidoProveedorDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PagoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author s.saenz11
 */

@Path("pedidoProveedor/{idPedidoProveedor: \\d+}/pagoProveedor")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PedidoProveedorPagoProveedorResource {
    
     @Inject PedidoProveedorLogic logic;
     @Inject PagoProveedorLogic logic1;
    
   @POST
   public PagoProveedorDetailDTO creatPagoProveedores(@PathParam("idPedidoProveedor")Long idPedidoProveedor,PagoProveedorDetailDTO  provee)throws BusinessLogicException
   {
       PedidoProveedorEntity ent = logic.getProveedor(idPedidoProveedor);
       
       if(ent == null)throw new BusinessLogicException("No existe el proveedor con id "+ idPedidoProveedor);
       
       PagoProveedorEntity pago =logic1.createWithPedido(provee.toEntity(),logic.getProveedor(idPedidoProveedor));
      
       return provee;
   }
   
    
    @GET
     @Path("/{id2:\\d+}")
    public PagoProveedorDetailDTO getPagoProveedor(@PathParam("idPedidoProveedor")Long idPedidoProveedor,@PathParam("id2")Long id2 )
            

            
    {
       
        
        PedidoProveedorEntity ent =logic.getProveedor(idPedidoProveedor);
             PagoProveedorDetailDTO dto = new PagoProveedorDetailDTO(logic1.getProveedor(id2));
       
       
        
        return dto;
        
    }
    
  
    
}

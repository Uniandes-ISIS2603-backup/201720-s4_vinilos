/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.PedidoProveedorDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PagoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoProveedorLogic;
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

@Path("/pedidoProveedor(idPedidoProveedor: \\d+)/pagoProveedor")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PedidoProveedorPagoProveedorResource {
    
     @Inject PedidoProveedorLogic logic;
     @Inject PagoProveedorLogic logic1;
    
   @GET
   public List<PedidoProveedorDetailDTO> getProveedores(@PathParam("idPedidoProveedor")Long idPedidoProveedor)throws BusinessLogicException
   {
       PedidoProveedorEntity ent = logic.getProveedor(idPedidoProveedor);
       
       if(ent == null)throw new BusinessLogicException("No existe el proveedor con id "+ idPedidoProveedor);
       List<PedidoProveedorDetailDTO> retList = new ArrayList<PedidoProveedorDetailDTO>();
       List<PedidoProveedorEntity> lista = logic.getAll();
       for(PedidoProveedorEntity en : lista)
           retList.add(new PedidoProveedorDetailDTO(en));
       
       return retList;
   }
   
    @GET
    @Path(("{id:\\d+}"))
    public PedidoProveedorDetailDTO getProveedor( @PathParam("idPagoProveedor") Long idPagoProveedor, @PathParam("idPagoProvvedor") Long idPedidoProveedor ) throws BusinessLogicException
    {
        
        
//       PedidoProveedorEntity ent = logic.getProveedor(idPedidoProveedor);
//       if(ent== null) throw new BusinessLogicException("No se encuentra");
//       PedidoProveedorDetailDTO detail = null;
//       for(PagoProveedorEnti)
//       
//        return (new  PedidoProveedorDetailDTO(ent));
        return null;
    }
    
  
    
}

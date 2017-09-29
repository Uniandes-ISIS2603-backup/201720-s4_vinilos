/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.ProveedorDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
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
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jc.ruiz
 */
@Path("proveedores")
@Produces("application/json")
@Consumes("application/json")
 @RequestScoped
public class ProveedorResource {
    
    @Inject ProveedorLogic logic;
    
   @GET
   public List<ProveedorDetailDTO> getProveedores()
   {
       List<ProveedorDetailDTO> retList = new ArrayList<>();
       List<ProveedorEntity> lista = logic.getAll();
       for(ProveedorEntity en : lista)
           retList.add(new ProveedorDetailDTO(en));
       
       return retList;
   }
   
    @GET
    @Path(("{id:\\d+}"))
    public ProveedorDetailDTO getProveedor( @PathParam("id") Long id)
    {
        ProveedorEntity en = logic.getProveedor(id);
        if (en == null)
            throw new WebApplicationException("El proveedor con el id " + id + " no existe ", 404);
        return (new ProveedorDetailDTO(en));
    }
    
    @POST
    public ProveedorDetailDTO createProveedor(ProveedorDetailDTO prov) throws BusinessLogicException
    {
        ProveedorEntity ent = logic.createProveedor(prov.toEntity());
        return (new ProveedorDetailDTO(ent));
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public ProveedorDetailDTO updateProveedor(@PathParam("id") Long id, ProveedorDetailDTO prov)
    {
        ProveedorEntity entity = prov.toEntity();
        entity.setId(id);
        ProveedorEntity oldEnt = logic.getProveedor(id);
        if(oldEnt == null)
            throw new WebApplicationException("El proveedor con el id " + id + " no existe ", 404); 
        ProveedorEntity ent = logic.updateProveedor(entity);
        return (new ProveedorDetailDTO(ent));
    }
    
    @DELETE
    @Path("{id:\\d+}")
    public void deleteProveedor(@PathParam("id")Long id)
    {
        ProveedorEntity ent = logic.getProveedor(id);
        if (ent == null)
             throw new WebApplicationException("El proveedor con el id " + id + " no existe ", 404);
        logic.deleteProveedor(id);
    }
  
    @Path("{proveedorId: \\d+}/pedidos")
    public Class<ProveedorPedidosResource> getProveedorPedidos(@PathParam("proveedorId") Long idProv)
    {
        ProveedorEntity ent = logic.getProveedor(idProv);
         if (ent == null)
             throw new WebApplicationException("El proveedor con el id " + idProv + " no existe ", 404);
         return ProveedorPedidosResource.class;
    }
    
    @Path("{proveedorId: \\d+}/pagos")
    public Class<ProveedorPagoResource> getProveedorsPedidos(@PathParam("proveedorId") Long idProv)
    {
        ProveedorEntity ent = logic.getProveedor(idProv);
         if (ent == null)
             throw new WebApplicationException("El proveedor con el id " + idProv + " no existe ", 404);
         return ProveedorPagoResource.class;
    }
    
    @Path("{proveedorId: \\d+}/feedbacks")
    public Class<ProveedorFeedBackResource> getProveedorsFeedBacks(@PathParam("proveedorId") Long idProv)
    {
        ProveedorEntity ent = logic.getProveedor(idProv);
         if (ent == null)
             throw new WebApplicationException("El proveedor con el id " + idProv + " no existe ", 404);
         return ProveedorFeedBackResource.class;
    }
    
}

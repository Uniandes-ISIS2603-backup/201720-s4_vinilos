/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.ProveedorDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import java.util.ArrayList;
import java.util.List;
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
 * @author jc.ruiz
 */
@Path("proveedores")
@Produces("application/json") 
@Consumes("application/json")
public class ProveedorResource {
    
    @Inject ProveedorLogic logic;
    
   @GET
   public List<ProveedorDTO> getProveedores()
   {
       List<ProveedorDTO> retList = new ArrayList<ProveedorDTO>();
       List<ProveedorEntity> lista = logic.getAll();
       for(ProveedorEntity en : lista)
           retList.add(new ProveedorDTO(en));
       
       return retList;
   }
   
    @GET
    @Path(("{id:\\d+}"))
    public ProveedorDTO getProveedor( @PathParam("id") Long id)
    {
       ProveedorEntity ent = logic.getProveedor(id);
        return (new ProveedorDTO(ent));
    }
    
    @POST
    public ProveedorDTO createProveedor(ProveedorDTO prov)
    {
        ProveedorEntity ent = logic.createProveedor(prov.toEntity());
        return (new ProveedorDTO(ent));
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public ProveedorDTO updateProveedor(@PathParam("id") Long id, ProveedorDTO prov)
    {
        ProveedorEntity ent = logic.updateProveedor(prov.toEntity());
        return (new ProveedorDTO(ent));
    }
    
    @DELETE
    @Path("{id:\\d+}")
    public void deleteProveedor(@PathParam("id")Long id)
    {
        logic.deleteProveedor(id);
    }
   
    
}

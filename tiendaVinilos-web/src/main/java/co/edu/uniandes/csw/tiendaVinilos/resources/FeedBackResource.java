/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.FeedBackDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.FeedBackLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
@Path("feedbacks")
@Produces("application/json")
@Consumes("application/json")
public class FeedBackResource {
  
      private static final Logger LOGGER = Logger.getLogger(FeedBackResource.class.getName());
    
    @Inject FeedBackLogic logic;
    
    @GET
    @Path("{id:\\d+}/proveedor")
   public ProveedorEntity getProveedor(Long id)
   {
       return logic.getProveedor(id);
   }
   @GET
   public List<FeedBackDetailDTO> getFeedBacks()
   {
       List<FeedBackDetailDTO> retList = new ArrayList<>();
       List<FeedBackEntity> lista = logic.getAll();
       for(FeedBackEntity en : lista)
           retList.add(new FeedBackDetailDTO(en));
       return retList;
   }
   
    @GET
    @Path(("{id:\\d+}"))
    public FeedBackDetailDTO getFeedBack( @PathParam("id") Long id)
    {
        FeedBackEntity en = logic.getFeedBack(id);
        if (en == null)
            throw new WebApplicationException("El proveedor con el id " + id + " no existe ", 404);
        return (new FeedBackDetailDTO(en));
    }
    
    @POST
    public FeedBackDetailDTO createFeedBack(FeedBackDetailDTO feed)
    {
        FeedBackEntity ent = logic.createFeedBack(feed.toEntity());
        return (new FeedBackDetailDTO(ent));
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public FeedBackDetailDTO updateFeedBack(@PathParam("id") Long id, FeedBackDetailDTO feed)
    {
        FeedBackEntity entity = feed.toEntity();
        entity.setId(id);
        FeedBackEntity oldEnt = logic.getFeedBack(id);
        if(oldEnt == null)
            throw new WebApplicationException("El feed back con el id " + id + " no existe ", 404); 
        FeedBackEntity ent = logic.updateFeedBack(entity);
        return (new FeedBackDetailDTO(ent));
    }
    
    @DELETE
    @Path("{id:\\d+}")
    public void deleteFeedBack(@PathParam("id")Long id)
    {
        FeedBackEntity ent = logic.getFeedBack(id);
        if (ent == null)
             throw new WebApplicationException("El feed back con el id " + id + " no existe ", 404);
        logic.deleteFeedBack(id);
    }

}

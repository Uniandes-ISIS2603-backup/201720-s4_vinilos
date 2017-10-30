/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.FeedBackDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.dtos.ProveedorDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.FeedBackLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
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
@Path("feedbacks")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FeedBackResource {

    
    @Inject FeedBackLogic feedbackLogic;
   @GET
   public List<FeedBackDetailDTO> getFeedBacks()
   {
       List<FeedBackDetailDTO> retList = new ArrayList<>();
       List<FeedBackEntity> lista = feedbackLogic.getAll();
       for(FeedBackEntity en : lista)
       {
           System.out.println("EL ID DEL FEEDBACK QUE LLEGO ES --------------------------- " + en.getId());
           retList.add(new FeedBackDetailDTO(en));
       }
       for (FeedBackDetailDTO fbDetail : retList)
           System.out.println("EL ID DEL DETAIl POR RETORNAR ES --------------------------- " + fbDetail.getId());
           
       return retList;
   }
   
    @GET
    @Path(("{id:\\d+}"))
    public FeedBackDetailDTO getFeedBack( @PathParam("id") Long id)
    {
        FeedBackEntity en = feedbackLogic.getFeedBack(id);
        if (en == null)
            throw new WebApplicationException("El proveedor con el id " + id + " no existe ", 404);
        FeedBackDetailDTO fbDTO = new FeedBackDetailDTO(en);
        System.out.println("HEEEEYYYYYYYYY llego el id " + id + " y sale el id dto " + fbDTO.getId() );
        return (fbDTO);
    }

    
    @POST
    public FeedBackDetailDTO createFeedBack(FeedBackDetailDTO feed)
    {
        FeedBackEntity ent = feedbackLogic.createFeedBack(feed.toEntity());
        return (new FeedBackDetailDTO(ent));
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public FeedBackDetailDTO updateFeedBack(@PathParam("id") Long id, FeedBackDetailDTO feed)
    {
        FeedBackEntity entity = feed.toEntity();
        entity.setId(id);
        FeedBackEntity oldEnt = feedbackLogic.getFeedBack(id);
        if(oldEnt == null)
            throw new WebApplicationException("El feed back con el id " + id + " no existe ", 404); 
        FeedBackEntity ent = feedbackLogic.updateFeedBack(entity);
        return (new FeedBackDetailDTO(ent));
    }
    
    @DELETE
    @Path("{id:\\d+}")
    public void deleteFeedBack(@PathParam("id")Long id)
    {
        FeedBackEntity ent = feedbackLogic.getFeedBack(id);
        if (ent == null)
             throw new WebApplicationException("El feed back con el id " + id + " no existe ", 404);
        feedbackLogic.deleteFeedBack(id);
    }
}

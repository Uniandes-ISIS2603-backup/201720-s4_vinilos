/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

import co.edu.uniandes.csw.dtos.CancionDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.CancionLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author cs.gomez
 */
@Path("vinilos/{vinilosId: \\d+}/cancion")
@Consumes("application/json")
@Produces("application/json")
public class ViniloCancionResource {
    
    @Inject CancionLogic canLogic;
    @Inject ViniloLogic  vinLogic;
    
    
    @GET
    public List<CancionDetailDTO> getCanciones(@PathParam("vinilosId") Long vinId)
    {
        ViniloEntity vinEnt = vinLogic.getVinilo(vinId);
        if(vinEnt == null)
        {
            throw new WebApplicationException("El recurso /Vinilo/" + vinId + " no existe.", 404);
        }
        List<CancionDetailDTO> canDto = new ArrayList<>();
        for (CancionEntity canEnt : vinLogic.getCanciones(vinId))
            canDto.add(new CancionDetailDTO(canEnt));
        return canDto;
    }
    
    
    @POST
    @Path(("/{idCan:\\d+}"))
     public CancionDetailDTO addCancion(@PathParam("vinilosId") Long vinId, @PathParam("idCan") Long canId)
    {
//        ViniloEntity ent = vinLogic.getVinilo(vinId);
//        if (ent == null) {
//            throw new WebApplicationException("El recurso /Vinilo/" + vinId + " no existe.", 404);
//        }
        CancionEntity canEnt = canLogic.getCancion(canId);
//        if (canEnt == null) {
//            throw new WebApplicationException("El recurso /Vinilo/" + canId + " no existe.", 404);
//        }
        canLogic.addVinilo(vinLogic.getVinilo(vinId), canEnt);
        return new CancionDetailDTO(canEnt);
    }
}

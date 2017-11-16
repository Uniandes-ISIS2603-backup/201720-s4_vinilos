/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

import co.edu.uniandes.csw.dtos.CancionDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ArtistaLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.CancionLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
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
@Path("artista/{artistaId: \\d+}/cancion")
@Consumes("application/json")
@Produces("application/json")
public class ArtistaCancionResource {
    
    @Inject ArtistaLogic artLogic;
    @Inject CancionLogic canLogic;
    
    
    @GET
    public List<CancionDetailDTO> getCanciones(@PathParam("artistaId") Long artId)
    {
        ArtistaEntity artEnt = artLogic.getArtista(artId);
        if(artEnt == null)
        {
            throw new WebApplicationException("El recurso /Artista/" + artId + " no existe.", 404);
        }
        List<CancionDetailDTO> canDto = new ArrayList<>();
        for (CancionEntity canEnt : artLogic.getCanciones(artId))
            canDto.add(new CancionDetailDTO(canEnt));
        return canDto;
    }
    
    
    @POST
    @Path(("/{idCan:\\d+}"))
     public CancionDetailDTO addCancion(@PathParam("artistaId") Long artId, @PathParam("idCan") Long canId)
    {
//        ArtistaEntity ent = artLogic.getArtista(artId);
//        if (ent == null) {
//            throw new WebApplicationException("El recurso /Artista/" + artId + " no existe.", 404);
//        }
        CancionEntity canEnt = canLogic.getCancion(canId);
//        if (canEnt == null) {
//            throw new WebApplicationException("El recurso /Artista/" + canId + " no existe.", 404);
//        }
        canLogic.addArtista(artLogic.getArtista(artId), canEnt);
        return new CancionDetailDTO(canEnt);
    }
}
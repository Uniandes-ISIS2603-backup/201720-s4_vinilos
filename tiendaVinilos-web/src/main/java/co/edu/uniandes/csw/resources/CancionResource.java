
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.CancionDTO;
import co.edu.uniandes.csw.dtos.CancionDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.CancionLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;

//~--- JDK imports ------------------------------------------------------------

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
 * @author cs.gomez
 */
@Path("cancion")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CancionResource {
    private static final String NOEXISTE = " no existe.";
    @Inject
    CancionLogic                cancionLogic;

    @POST
    public CancionDetailDTO createCancion(CancionDTO cancion) throws BusinessLogicException {
        CancionEntity cancionEntity = cancion.toEntity();
        CancionEntity nuevaCancion  = cancionLogic.createCancion(cancionEntity);

        return new CancionDetailDTO(nuevaCancion);
    }

    @GET
    public List<CancionDetailDTO> getCanciones() throws BusinessLogicException {
        return listEntity2DTO(cancionLogic.getCanciones());
    }

    private List<CancionDetailDTO> listEntity2DTO(List<CancionEntity> entityList) throws BusinessLogicException {
        List<CancionDetailDTO> list = new ArrayList<>();

        for (CancionEntity entity : entityList) {
            list.add(new CancionDetailDTO(entity));
        }

        return list;
    }

    @GET
    @Path("{id: \\d+}")
    public CancionDetailDTO getCancion(@PathParam("id") Long id) throws BusinessLogicException {
        CancionEntity entity = cancionLogic.getCancion(id);

        if (entity == null) {
            throw new WebApplicationException("El recurso /cancion/" + id + NOEXISTE, 404);
        }

        return new CancionDetailDTO(entity);
    }

    @PUT
    @Path("{id: \\d+}")
    public CancionDetailDTO updateCancion(@PathParam("id") Long id, CancionDTO cancion) throws BusinessLogicException {
        cancion.setId(id);

        CancionEntity entity = cancion.toEntity();

        if (entity == null) {
            throw new WebApplicationException("El recurso /cancion/" + id + NOEXISTE, 404);
        }

        return new CancionDetailDTO(cancionLogic.updateCancion(id, entity));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteCancion(@PathParam("id") Long id) throws BusinessLogicException {
        CancionEntity entity = cancionLogic.getCancion(id);

        if (entity == null) {
            throw new WebApplicationException("El recurso /Cancion/" + id + NOEXISTE, 404);
        }

        cancionLogic.deleteCancion(id);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

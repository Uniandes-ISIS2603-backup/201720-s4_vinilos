/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.ArtistaDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ArtistaLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
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
 * @author cs.gomez
 */
@Path("artista")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ArtistaResource {
    
    @Inject
    ArtistaLogic logic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    @POST
    public ArtistaDetailDTO createArtista(ArtistaDetailDTO artista) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ArtistaEntity entity = artista.toEntity();
        
        // Invoca la lógica para crear la Vinilo nueva
        ArtistaEntity nuevoArtista = logic.createArtista(artista.toEntity());
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ArtistaDetailDTO(nuevoArtista);
    }

    /**
     * GET para todas las Artistas.
     * http://localhost:8080/tiendaVinilos-web/api/artista     *
     * @return la lista de todos los Artistas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<ArtistaDetailDTO> getArtistas() throws BusinessLogicException {
        return listEntity2DetailDTO(logic.getArtistas());
    }

    
    @GET
    @Path("{id: \\d+}")
    public ArtistaDetailDTO getArtista(@PathParam("id") Long id) throws BusinessLogicException {
        ArtistaEntity entity = logic.getArtista(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Artista/" + id + " no existe.", 404);
        }
        return new ArtistaDetailDTO(logic.getArtista(id));
    }

    /**
     * PUT http://localhost:8080/tiendaVinilos-web/api/Artista/1 Ejemplo
     * json { "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde al Artista a actualizar.
     * @param artista corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return  Artista actualizado.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del Artista a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public ArtistaDetailDTO updateArtista(@PathParam("id") Long id, ArtistaDetailDTO artista) throws BusinessLogicException {
        artista.setId(id);
        ArtistaEntity entity = logic.getArtista(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Artista/" + id + " no existe.", 404);
        }
        return new ArtistaDetailDTO(logic.updateArtista(id, artista.toEntity()));
    }

    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteArtista(@PathParam("id") Long id) throws BusinessLogicException {
        ArtistaEntity entity = logic.getArtista(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /artista/" + id + " no existe.", 404);
        }
        logic.deleteArtista(id);
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ViniloEntity a una lista de
     * objetos ViniloDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Viniloes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Viniloes en forma DTO (json)
     */
    private List<ArtistaDetailDTO> listEntity2DetailDTO(List<ArtistaEntity> entityList) {
        List<ArtistaDetailDTO> list = new ArrayList<>();
        for (ArtistaEntity entity : entityList) {
            list.add(new ArtistaDetailDTO(entity));
        }
        return list;
    }
}

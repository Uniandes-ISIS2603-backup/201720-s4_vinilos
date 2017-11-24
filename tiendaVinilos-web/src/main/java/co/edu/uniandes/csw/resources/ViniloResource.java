package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
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
 * @author jp.monsalvo
 */
@Path("vinilos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ViniloResource {
    @Inject
    ViniloLogic viniloLogic;    // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * GET para todas las Viniloes.
     * http://localhost:8080/tiendaVinilos-web/api/Vinilos
     *
     *
     * @return la lista de todas los Vinilos en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<ViniloDetailDTO> getVinilos() throws BusinessLogicException {
        return listEntity2DetailDTO(viniloLogic.getVinilos());
    }

    /**
     * GET para un Vinilo
     * http://localhost:8080/tiendaVinilos-web/api/Vinilos     
     * @param id corresponde al id de la Vinilo buscada.
     * @return La Vinilo encontrada. Ejemplo: { "type": "ViniloDetailDTO",
     * "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del Vinilo buscado se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public ViniloDetailDTO getVinilo(@PathParam("id") Long id) throws BusinessLogicException {
        ViniloEntity entity = viniloLogic.getVinilo(id);

        if (entity == null) {
            throw new WebApplicationException("El recurso /Vinilos/" + id + " no existe.", 404);
        }

        return new ViniloDetailDTO(entity);
    }

    /**
     * POST http://localhost:8080/tiendaVinilos-web/api/Vinilos Ejemplo json: {
     * "name":"Norma" }
     *
     * @param Vinilo correponde a la representación java del objeto json enviado
     * en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "ViniloDetailDTO", "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     */
    @POST
    public ViniloDetailDTO createVinilo(ViniloDetailDTO vinilo) throws BusinessLogicException {

        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ViniloEntity viniloEntity = vinilo.toEntity();

        // Invoca la lógica para crear la Vinilo nueva
        ViniloEntity nuevoVinilo = viniloLogic.createVinilo(viniloEntity);

        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ViniloDetailDTO(nuevoVinilo);
    }

    /**
     * PUT http://localhost:8080/tiendaVinilos-web/api/Vinilos/1 Ejemplo json {
     * "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde a la Vinilo a actualizar.
     * @param Vinilo corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La Vinilo actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Vinilo a actualizar se retorna un 404
     * con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public ViniloDetailDTO updateVinilo(@PathParam("id") Long id, ViniloDetailDTO vinilo)
            throws BusinessLogicException {
        vinilo.setId(id);

        ViniloEntity entity = viniloLogic.getVinilo(id);

        if (entity == null) {
            throw new WebApplicationException("El recurso /Vinilos/" + id + " no existe.", 404);
        }

        return new ViniloDetailDTO(viniloLogic.updateVinilo(id, vinilo.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/tiendaVinilos-web/api/Vinilos/1
     *
     * @param id corresponde a la Vinilo a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Vinilo a actualizar se retorna un 404
     * con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteVinilo(@PathParam("id") Long id) throws BusinessLogicException {
        ViniloEntity entity = viniloLogic.getVinilo(id);

        if (entity == null) {
            throw new WebApplicationException("El recurso /Vinilos/" + id + " no existe.", 404);
        }

        viniloLogic.deleteVinilo(id);
    }

    @Path("{idVinilo: \\d+}/infos")
    public Class<InfoResource> getInfoResource(@PathParam("idVinilo") Long viniloId) {
        ViniloEntity entity = viniloLogic.getVinilo(viniloId);

        if (entity == null) {
            throw new WebApplicationException("El recurso /vinilos/" + viniloId + "/infos no existe.", 404);
        }

        return InfoResource.class;
    }

    @Path("{vinilosId: \\d+}/artistas")
    public Class<ViniloArtistasResource> getViniloArtistasResource(@PathParam("vinilosId") Long vinilosId) {
        ViniloEntity entity = viniloLogic.getVinilo(vinilosId);

        if (entity == null) {
            throw new WebApplicationException("El recurso /vinilos/" + vinilosId + " no existe.", 404);
        }

        return ViniloArtistasResource.class;
    }

    @Path("{vinilosId: \\d+}/canciones")
    public Class<ViniloCancionesResource> getViniloCancionesResource(@PathParam("vinilosId") Long vinilosId) {
        ViniloEntity entity = viniloLogic.getVinilo(vinilosId);

        if (entity == null) {
            throw new WebApplicationException("El recurso /vinilos/" + vinilosId + " no existe.", 404);
        }

        return ViniloCancionesResource.class;
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ViniloEntity a una lista de
     * objetos ViniloDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Viniloes de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de Viniloes en forma DTO (json)
     */
    private List<ViniloDetailDTO> listEntity2DetailDTO(List<ViniloEntity> entityList) {
        List<ViniloDetailDTO> list = new ArrayList<>();

        for (ViniloEntity entity : entityList) {
            list.add(new ViniloDetailDTO(entity));
        }

        return list;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.InfoDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.InfoLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.InfoEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.InfoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("infos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class InfoResource {
        @Inject
    InfoLogic InfoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(InfoPersistence.class.getName());

    /**
     * POST http://localhost:8080/tiendaVinilos-web/api/Infos Ejemplo
     * json: { "name":"Norma" }
     *
     * @param Info correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "InfoDetailDTO", "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     */
    @POST
    public InfoDetailDTO createInfo(InfoDetailDTO Info) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        InfoEntity InfoEntity = Info.toEntity();
        // Invoca la lógica para crear la Info nueva
        InfoEntity nuevoInfo = InfoLogic.createInfo(Info.toEntity());
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new InfoDetailDTO(nuevoInfo);
    }

    /**
     * GET para todas las Infoes.
     * http://localhost:8080/tiendaVinilos-web/api/Infos     *
     * @return la lista de todas los Infos en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<InfoDetailDTO> getInfos() throws BusinessLogicException {
        return listEntity2DetailDTO(InfoLogic.getInfos());
    }

    /**
     * GET para un Info
     * http://localhost:8080/tiendaVinilos-web/api/Infos     *
     * @param id corresponde al id de la Info buscada.
     * @return La Info encontrada. Ejemplo: { "type": "InfoDetailDTO",
     * "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del Info buscado se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public InfoDetailDTO getInfo(@PathParam("id") Long id) throws BusinessLogicException {
        InfoEntity entity = InfoLogic.getInfo(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Infos/" + id + " no existe.", 404);
        }
        return new InfoDetailDTO(InfoLogic.getInfo(id));
    }

    /**
     * PUT http://localhost:8080/tiendaVinilos-web/api/Infos/1 Ejemplo
     * json { "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde a la Info a actualizar.
     * @param Info corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La Info actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Info a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public InfoDetailDTO updateInfo(@PathParam("id") Long id, InfoDetailDTO Info) throws BusinessLogicException {
        Info.setId(id);
        InfoEntity entity = InfoLogic.getInfo(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Infos/" + id + " no existe.", 404);
        }
        return new InfoDetailDTO(InfoLogic.updateInfo(id, Info.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/tiendaVinilos-web/api/Infos/1
     *
     * @param id corresponde a la Info a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Info a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteInfo(@PathParam("id") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una Info con id {0}", id);
        InfoEntity entity = InfoLogic.getInfo(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Infos/" + id + " no existe.", 404);
        }
        InfoLogic.deleteInfo(id);
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos InfoEntity a una lista de
     * objetos InfoDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Infoes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Infoes en forma DTO (json)
     */
    private List<InfoDetailDTO> listEntity2DetailDTO(List<InfoEntity> entityList) {
        List<InfoDetailDTO> list = new ArrayList<>();
        for (InfoEntity entity : entityList) {
            list.add(new InfoDetailDTO(entity));
        }
        return list;
    }

}

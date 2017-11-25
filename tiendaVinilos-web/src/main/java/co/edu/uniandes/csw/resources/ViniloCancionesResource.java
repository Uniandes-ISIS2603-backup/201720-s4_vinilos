/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;
import co.edu.uniandes.csw.dtos.CancionDTO;
import co.edu.uniandes.csw.dtos.CancionDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;

/**
 * URI: vinilos/{viniloId: \\d+}/cancions
 * 
 * @cancion jp.monsalvo
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViniloCancionesResource {
    
    @Inject
    private ViniloLogic viniloLogic;

    /**
     * Convierte una lista de CancionEntity a una lista de CancionDetailDTO.
     *
     * @param entityList Lista de CancionEntity a convertir.
     * @return Lista de CancionDetailDTO convertida.
     * 
     */
    private List<CancionDetailDTO> cancionsListEntity2DTO(List<CancionEntity> entityList) {
        List<CancionDetailDTO> list = new ArrayList<>();
        for (CancionEntity entity : entityList) {
            list.add(new CancionDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de CancionDetailDTO a una lista de CancionEntity.
     *
     * @param dtos Lista de CancionDetailDTO a convertir.
     * @return Lista de CancionEntity convertida.
     * 
     */
    private List<CancionEntity> cancionsListDTO2Entity(List<CancionDetailDTO> dtos) {
        List<CancionEntity> list = new ArrayList<>();
        for (CancionDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de CancionDetailDTO asociadas a una
     * instancia de Vinilo
     *
     * @param vinilosId Identificador de la instancia de Vinilo
     * @return Colección de instancias de CancionDetailDTO asociadas a la
     * instancia de Vinilo
     * 
     */
    @GET
    public List<CancionDetailDTO> listCanciones(@PathParam("vinilosId") Long vinilosId) {
        return cancionsListEntity2DTO(viniloLogic.listCanciones(vinilosId));
    }

    /**
     * Obtiene una instancia de Cancion asociada a una instancia de Vinilo
     *
     * @param vinilosId Identificador de la instancia de Vinilo
     * @param cancionsId Identificador de la instancia de Cancion
     * @return 
     * 
     */
    @GET
    @Path("{cancionsId: \\d+}")
    public CancionDetailDTO getCanciones(@PathParam("vinilosId") Long vinilosId, @PathParam("cancionsId") Long cancionsId) {
        return new CancionDetailDTO(viniloLogic.getCancion(vinilosId, cancionsId));
    }

    /**
     * Asocia un Cancion existente a un Vinilo
     *
     * @param vinilosId Identificador de la instancia de Vinilo
     * @param cancionsId Identificador de la instancia de Cancion
     * @return Instancia de CancionDetailDTO que fue asociada a Vinilo
     * 
     */
    @POST
    public CancionDetailDTO addCanciones(@PathParam("vinilosId") Long vinilosId, CancionDTO cancion) {
        return new CancionDetailDTO(viniloLogic.addCancion(vinilosId, cancion.toEntity(),cancion.getId()));
    }

    /**
     * Remplaza las instancias de Cancion asociadas a una instancia de Vinilo
     *
     * @param vinilosId Identificador de la instancia de Vinilo
     * @param cancions Colección de instancias de CancionDTO a asociar a instancia
     * de Vinilo
     * @return Nueva colección de CancionDTO asociada a la instancia de Vinilo
     * 
     */
    @PUT
    public List<CancionDetailDTO> replaceCanciones(@PathParam("vinilosId") Long vinilosId, List<CancionDetailDTO> cancions) {
        return cancionsListEntity2DTO(viniloLogic.replaceCanciones(vinilosId, cancionsListDTO2Entity(cancions)));
    }

    /**
     * Desasocia un Cancion existente de un Vinilo existente
     *
     * @param vinilosId Identificador de la instancia de Vinilo
     * @param cancionsId Identificador de la instancia de Cancion
     * 
     */
    @DELETE
    @Path("{cancionsId: \\d+}")
    public void removeCanciones(@PathParam("vinilosId") Long vinilosId, @PathParam("cancionsId") Long cancionsId) {
        viniloLogic.removeCancion(vinilosId, cancionsId);
    }
}

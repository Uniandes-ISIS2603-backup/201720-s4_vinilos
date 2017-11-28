/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;
import co.edu.uniandes.csw.dtos.ArtistaDTO;
import co.edu.uniandes.csw.dtos.ArtistaDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
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
 * URI: vinilos/{viniloId: \\d+}/artistas
 * 
 * @artista jp.monsalvo
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViniloArtistasResource {
    
    @Inject
    private ViniloLogic viniloLogic;

    /**
     * Convierte una lista de ArtistaEntity a una lista de ArtistaDetailDTO.
     *
     * @param entityList Lista de ArtistaEntity a convertir.
     * @return Lista de ArtistaDetailDTO convertida.
     * 
     */
    private List<ArtistaDetailDTO> artistasListEntity2DTO(List<ArtistaEntity> entityList) {
        List<ArtistaDetailDTO> list = new ArrayList<>();
        for (ArtistaEntity entity : entityList) {
            list.add(new ArtistaDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de ArtistaDetailDTO a una lista de ArtistaEntity.
     *
     * @param dtos Lista de ArtistaDetailDTO a convertir.
     * @return Lista de ArtistaEntity convertida.
     * 
     */
    private List<ArtistaEntity> artistasListDTO2Entity(List<ArtistaDetailDTO> dtos) {
        List<ArtistaEntity> list = new ArrayList<>();
        for (ArtistaDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de ArtistaDetailDTO asociadas a una
     * instancia de Vinilo
     *
     * @param vinilosId Identificador de la instancia de Vinilo
     * @return Colección de instancias de ArtistaDetailDTO asociadas a la
     * instancia de Vinilo
     * 
     */
    @GET
    public List<ArtistaDetailDTO> listArtistas(@PathParam("vinilosId") Long vinilosId) {
        return artistasListEntity2DTO(viniloLogic.listArtistas(vinilosId));
    }

    /**
     * Obtiene una instancia de Artista asociada a una instancia de Vinilo
     *
     * @param vinilosId Identificador de la instancia de Vinilo
     * @param artistasId Identificador de la instancia de Artista
     * @return 
     * 
     */
    @GET
    @Path("{artistasId: \\d+}")
    public ArtistaDetailDTO getArtistas(@PathParam("vinilosId") Long vinilosId, @PathParam("artistasId") Long artistasId) throws BusinessLogicException {
        return new ArtistaDetailDTO(viniloLogic.getArtista(vinilosId, artistasId));
    }

    /**
     * Asocia un Artista existente a un Vinilo
     *
     * @param vinilosId Identificador de la instancia de Vinilo
     * @param artistasId Identificador de la instancia de Artista
     * @return Instancia de ArtistaDetailDTO que fue asociada a Vinilo
     * 
     */
    @POST
    
    public ArtistaDetailDTO addArtistas(@PathParam("vinilosId") Long vinilosId, ArtistaDTO artista) {
        return new ArtistaDetailDTO(viniloLogic.addArtista(vinilosId, artista.toEntity(),artista.getId()));
        
    }

    /**
     * Remplaza las instancias de Artista asociadas a una instancia de Vinilo
     *
     * @param vinilosId Identificador de la instancia de Vinilo
     * @param artistas Colección de instancias de ArtistaDTO a asociar a instancia
     * de Vinilo
     * @return Nueva colección de ArtistaDTO asociada a la instancia de Vinilo
     * 
     */
    @PUT
    public List<ArtistaDetailDTO> replaceArtistas(@PathParam("vinilosId") Long vinilosId, List<ArtistaDetailDTO> artistas) {
        return artistasListEntity2DTO(viniloLogic.replaceArtistas(vinilosId, artistasListDTO2Entity(artistas)));
    }

    /**
     * Desasocia un Artista existente de un Vinilo existente
     *
     * @param vinilosId Identificador de la instancia de Vinilo
     * @param artistasId Identificador de la instancia de Artista
     * 
     */
    @DELETE
    @Path("{artistasId: \\d+}")
    public void removeArtistas(@PathParam("vinilosId") Long vinilosId, @PathParam("artistasId") Long artistasId) {
        viniloLogic.removeArtista(vinilosId, artistasId);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.FeedBackDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.FeedBackLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jd.arenas
 */
@Path("usuarios/{id: \\d+}/feedback")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioFeedBackResource {
    @Inject
    UsuarioLogic usuarioLogic;
    @Inject
    FeedBackLogic feedBackLogic;
    
     @GET
    public List<FeedBackDetailDTO> getFeedBacks(@PathParam("id") Long id) throws BusinessLogicException {
        
        return listEntity2DetailDTO(usuarioLogic.getFeedBacks(id));
    }
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos UsuarioEntity a una lista de
     * objetos UsuarioDetailDTO (json)
     *
     * @param entityList corresponde a la lista de usuarioes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de usuarioes en forma DTO (json)
     */
    private List<FeedBackDetailDTO> listEntity2DetailDTO(List<FeedBackEntity> entityList) {
        List<FeedBackDetailDTO> list = new ArrayList<>();
        for (FeedBackEntity entity : entityList) {
            FeedBackDetailDTO dto= new FeedBackDetailDTO(entity);
            list.add(dto);
        }
        return list;
    }
    @POST
    public FeedBackDetailDTO createUsuario(FeedBackDetailDTO feedBack,@PathParam("id") Long id) throws BusinessLogicException {
        return null;
    }
}

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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
     @GET
    @Path("/{id2:\\d+}")
    public FeedBackDetailDTO getFeedBack(@PathParam("id") Long id,@PathParam("id2")Long id2)
    {
        FeedBackEntity feed= feedBackLogic.getFeedBack(id2);
        feedBackLogic.getFeedBack(id2);
        return new FeedBackDetailDTO(feed);
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
    public FeedBackDetailDTO createFeedBack(FeedBackDetailDTO feedBack,@PathParam("id") Long id) throws BusinessLogicException {
        feedBackLogic.agregarFeedBack(usuarioLogic.getUsuario(id), feedBack.toEntity(), feedBack.getProveedor().toEntity());
        return feedBack;
    }
    @PUT
    @Path("/{id2:\\d+}")
    public FeedBackDetailDTO updateFeedBack(@PathParam("id") Long id,@PathParam("id2")Long id2)
    {
        FeedBackEntity feed= feedBackLogic.getFeedBack(id2);
        feedBackLogic.modificarFeedBack(usuarioLogic.getUsuario(0), feed,feed.getProveedor());
        return new FeedBackDetailDTO();
    }
     @DELETE
    @Path("/{id2:\\d+}")
    public void deleteFeedBack(@PathParam("id") Long id,@PathParam("id2")Long id2)
    {
        FeedBackEntity feed= feedBackLogic.getFeedBack(id2);
        feedBackLogic.deleteFB(feed);
    }
    
}
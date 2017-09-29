/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.FeedBackDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.FeedBackLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
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
 * @author jd.arenas
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioFeedBackResource {
    @Inject
    UsuarioLogic usuarioLogic;
    @Inject
    FeedBackLogic feedBackLogic;
    @Inject
    ProveedorLogic proveedorLogic;
    
     @GET
    public List<FeedBackDetailDTO> getFeedBacks(@PathParam("usuarioId") Long id) throws BusinessLogicException {
        
        return listEntity2DetailDTO(usuarioLogic.getFeedBacks(id));
    }
     @GET
    @Path("/{id2:\\d+}")
    public FeedBackDetailDTO getFeedBack(@PathParam("usuarioId") Long id,@PathParam("id2")Long id2)
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
    public FeedBackDetailDTO createFeedBack(FeedBackDetailDTO feedBack,@PathParam("usuarioId") Long id) throws BusinessLogicException {
        feedBackLogic.agregarFeedBack(usuarioLogic.getUsuario(id), feedBack.toEntity(), feedBack.getProveedor().toEntity());
        return feedBack;
    }
    @PUT
    @Path("/{id2:\\d+}")
    public FeedBackDetailDTO updateFeedBack(@PathParam("usuarioId") Long id,@PathParam("id2")Long id2,FeedBackDetailDTO nuevo)
    {
        nuevo.setId(id2);
        feedBackLogic.modificarFeedBack(usuarioLogic.getUsuario(id), nuevo.toEntity(),nuevo.getProveedor().toEntity());
        return nuevo;
    }
     @DELETE
    @Path("/{id2:\\d+}")
    public void deleteFeedBack(@PathParam("usuarioId") Long id,@PathParam("id2")Long id2)
    {
        FeedBackEntity feed= feedBackLogic.getFeedBack(id2);
        feedBackLogic.deleteFB(feed);
    }
    
    @GET
    @Path("/proveedores/{id:\\d+}")
    public List<FeedBackDetailDTO> getFeedbacksProveedor(@PathParam("id") Long id)
    {
         ProveedorEntity ent = proveedorLogic.getProveedor(id);
        if (ent == null)
             throw new WebApplicationException("El proveedor con el id " + id + " no existe ", 404);
        List<FeedBackDetailDTO> fbDto = new ArrayList<>();
        for (FeedBackEntity fbEnt : proveedorLogic.getFeedBacks(id))
            fbDto.add(new FeedBackDetailDTO(fbEnt));
        return fbDto;
    }
    
}

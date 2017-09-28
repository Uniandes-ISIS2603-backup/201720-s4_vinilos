/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.ViniloDTO;
import co.edu.uniandes.csw.tiendaVinilos.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
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
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioCarroComprasResource {
     @Inject
    UsuarioLogic usuarioLogic;
    @Inject
    ViniloLogic viniloLogic;
    
     @GET
    public List<ViniloDTO> getCarroComprass(@PathParam("id") Long id) throws BusinessLogicException {
        
        return listEntity2DetailDTO(usuarioLogic.getCarroCompras(id));
    }
     @GET
    @Path("/{id2:\\d+}")
    public ViniloDTO getCarroCompras(@PathParam("id") Long id,@PathParam("id2")Long id2)
    {
        ViniloEntity feed= viniloLogic.getVinilo(id2);
        return new ViniloDetailDTO(feed);
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
    private List<ViniloDTO> listEntity2DetailDTO(List<ViniloEntity> entityList) {
        List<ViniloDTO> list = new ArrayList<>();
        for (ViniloEntity entity : entityList) {
            ViniloDTO dto= new ViniloDTO(entity);
            list.add(dto);
        }
        return list;
    }
    @POST
    public ViniloDTO postViniloCarro(ViniloDetailDTO vinilo,@PathParam("id") Long id) throws BusinessLogicException {
        return new ViniloDTO(viniloLogic.agregarViniloCarro(vinilo.getUsuario().toEntity(), vinilo.toEntity()));
    }
    @PUT
    @Path("/{id2:\\d+}")
    public ViniloDTO updateCarroCompras(@PathParam("id") Long id,@PathParam("id2")Long id2,ViniloDetailDTO nuevo)
    {
        nuevo.setId(id2);
        viniloLogic.modificarCarrito(nuevo.getUsuario().toEntity(), nuevo.toEntity());
        return nuevo;
    }
     @DELETE
    @Path("/{id2:\\d+}")
    public void deleteCarroCompras(@PathParam("id") Long id,@PathParam("id2")Long id2)
    {
       ViniloEntity viniloEntity=viniloLogic.getVinilo(id2);
       viniloLogic.sacraDelCarrito(viniloEntity);
    }
    
}

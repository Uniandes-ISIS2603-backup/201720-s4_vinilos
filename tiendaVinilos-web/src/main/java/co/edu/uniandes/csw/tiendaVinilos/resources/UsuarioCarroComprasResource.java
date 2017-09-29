/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.CarroComprasDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.dtos.ViniloDTO;
import co.edu.uniandes.csw.tiendaVinilos.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.CarroComprasLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
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
@Path("usuarios/{usuarioId: \\d+}/carroCompras")
public class UsuarioCarroComprasResource {
    @Inject
    UsuarioLogic usuarioLogic;
    @Inject
    CarroComprasLogic carroComprasLogic;
    @Inject
    ViniloLogic viniloLogic;
    
     @GET
    public CarroComprasDetailDTO getCarroCompras(@PathParam("usuarioId") Long id) throws BusinessLogicException {
        
        return new CarroComprasDetailDTO(usuarioLogic.getCarrito(id));
    }
     @GET
    @Path("/{id3:\\d+}")
    public ViniloDTO getCarroCompras(@PathParam("usuarioId") Long id,@PathParam("id3")Long id3)
    {
        UsuarioEntity usu= usuarioLogic.getUsuario(id);
        CarroComprasEntity carro= usu.getCarrito();
        ViniloDTO vinilo=new ViniloDTO(carroComprasLogic.getViniloFromCarrito(carroComprasLogic.getCarroCompras(id), id3));
        return vinilo;
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
    @Path("{idVin:\\d+}")
    public ViniloDTO postViniloCarro(@PathParam("usuarioId") Long id,@PathParam("idVin") Long id2) throws BusinessLogicException {
        ViniloEntity ent= viniloLogic.getVinilo(id2);
        viniloLogic.addCarrito(usuarioLogic.getCarrito(id), ent);
        return new ViniloDTO(ent);
    }

//    @PUT
//    @Path("/{id2:\\d+}")
//    public ViniloDTO updateCarroCompras(@PathParam("id") Long id,@PathParam("id2")Long id2,ViniloDetailDTO nuevo)
//    {
//        nuevo.setId(id2);
//        viniloLogic.modificarCarrito(nuevo.getUsuario().toEntity(), nuevo.toEntity());
//        return nuevo;
    
    @DELETE
    @Path("/{id2:\\d+}")
    public void deleteCarroCompras(@PathParam("usuarioId") Long id,@PathParam("id2")Long id2)
    {
       viniloLogic.sacraDelCarrito(viniloLogic.getVinilo(id2));
    }
    
}

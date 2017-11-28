
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.TarjetaDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.TarjetaLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
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

/**
 *
 * @author jd.arenas
 */
@Path("usuarios/{usuarioId: \\d+}/tarjetas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetasUsuarioResource {
    @Inject
    UsuarioLogic usuarioLogic;
    @Inject
    TarjetaLogic tarjetaLogic;

    @GET
    public List<TarjetaDetailDTO> getTarjetas(@PathParam("usuarioId") Long id) throws BusinessLogicException {
        return listEntity2DetailDTO(tarjetaLogic.darTarjetasUsuario(id));
    }

    @GET
    @Path("/{id2:\\d+}")
    public TarjetaDetailDTO getTarjeta( @PathParam("id2") Long id2) {
        TarjetaEntity tarjeta = tarjetaLogic.getTarjeta(id2);

        tarjetaLogic.getTarjeta(id2);

        return new TarjetaDetailDTO(tarjeta);
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
    private List<TarjetaDetailDTO> listEntity2DetailDTO(List<TarjetaEntity> entityList) {
        List<TarjetaDetailDTO> list = new ArrayList<>();

        for (TarjetaEntity entity : entityList) {
            TarjetaDetailDTO dto = new TarjetaDetailDTO(entity);

            list.add(dto);
        }

        return list;
    }

    @POST
    public TarjetaDetailDTO createTarjeta(TarjetaDetailDTO tarjeta, @PathParam("usuarioId") Long id)
            throws BusinessLogicException {
        tarjetaLogic.agregarTarjeta(usuarioLogic.getUsuario(id), tarjeta.toEntity());

        return tarjeta;
    }

    @PUT
    @Path("/{id2:\\d+}")
    public TarjetaDetailDTO updateTarjeta(@PathParam("usuarioId") Long id, @PathParam("id2") Long id2, TarjetaDetailDTO card) {
        TarjetaEntity tarjeta = tarjetaLogic.getTarjeta(id2);
        tarjeta.setCvc(card.getCvc());
        tarjeta.setGoodThru(card.getGoodThru());
        tarjeta.setName(card.getName());
        tarjeta.setNombrePropietario(card.getNombrePropietario());
        tarjeta.setNumero(card.getNumero());

        tarjetaLogic.modificarTarjeta(usuarioLogic.getUsuario(id), tarjeta);

        return new TarjetaDetailDTO();
    }

    @DELETE
    @Path("/{id2:\\d+}")
    public void deleteTarjeta(@PathParam("id2") Long id2) {
        TarjetaEntity tarjeta = tarjetaLogic.getTarjeta(id2);

        tarjetaLogic.deleteT(tarjeta);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.CarroComprasDetailDTO;
import co.edu.uniandes.csw.dtos.ViniloDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.CarroComprasLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;

//~--- JDK imports ------------------------------------------------------------


import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    UsuarioLogic      usuarioLogic;
    @Inject
    CarroComprasLogic carroComprasLogic;
    @Inject
    ViniloLogic       viniloLogic;

    @GET
    public CarroComprasDetailDTO getCarroCompras(@PathParam("usuarioId") Long id) throws BusinessLogicException {
        return new CarroComprasDetailDTO(usuarioLogic.getCarrito(id));
    }

    @GET
    @Path("/{id3:\\d+}")
    public ViniloDTO getCarroCompras(@PathParam("usuarioId") Long id, @PathParam("id3") Long id3) {
        return new ViniloDTO(carroComprasLogic.getViniloFromCarrito(carroComprasLogic.getCarroCompras(id), id3));
    }

    @POST
    @Path("{idVin:\\d+}")
    public ViniloDTO postViniloCarro(@PathParam("usuarioId") Long id, @PathParam("idVin") Long id2)
            throws BusinessLogicException {
        ViniloEntity ent = viniloLogic.getVinilo(id2);

        viniloLogic.addCarrito(usuarioLogic.getCarrito(id), ent);

        return new ViniloDTO(ent);
    }

    @DELETE
    @Path("/{id2:\\d+}")
    public void deleteCarroCompras(@PathParam("id2") Long id2) {
        viniloLogic.sacraDelCarrito(viniloLogic.getVinilo(id2));
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

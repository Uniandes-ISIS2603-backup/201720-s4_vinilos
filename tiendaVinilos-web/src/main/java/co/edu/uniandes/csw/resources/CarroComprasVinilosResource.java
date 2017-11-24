
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.CarroComprasLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * @Path("{carroComprasId:\\d+}/vinilos")
 * @author jc.ruiz
 */
@Path("carroCompras/{carroComprasId: \\d+}/vinilos")
@Consumes("application/json")
@Produces("application/json")
public class CarroComprasVinilosResource {
    private static final String NOEXISTE = " no existe.";
    @Inject
    CarroComprasLogic           carLogic;
    @Inject
    ViniloLogic                 vinLogic;

    @GET
    public List<ViniloDetailDTO> getVinilos(@PathParam("carroComprasId") Long carId) {
        CarroComprasEntity ent = carLogic.getCarroCompras(carId);

        if (ent == null) {
            throw new WebApplicationException("El recurso /CarroCompra/" + carId + NOEXISTE, 404);
        }

        List<ViniloDetailDTO> vinDto = new ArrayList<>();

        for (ViniloEntity vinEnt : carLogic.getVinilos(carId)) {
            vinDto.add(new ViniloDetailDTO(vinEnt));
        }

        return vinDto;
    }

    @POST
    @Path(("/{idVin:\\d+}"))
    public ViniloDetailDTO addVinilo(@PathParam("carroComprasId") Long carId, @PathParam("idVin") Long vinId) {
        CarroComprasEntity ent = carLogic.getCarroCompras(carId);

        if (ent == null) {
            throw new WebApplicationException("El recurso /CarroCompra/" + carId + NOEXISTE, 404);
        }

        ViniloEntity vinEnt = vinLogic.getVinilo(vinId);

        if (vinEnt == null) {
            throw new WebApplicationException("El recurso /CarroCompra/" + vinId + NOEXISTE, 404);
        }

        vinLogic.addCarrito(carLogic.getCarroCompras(carId), vinEnt);

        return new ViniloDetailDTO(vinEnt);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

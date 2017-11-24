
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.FeedBackDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jc.ruiz
 */
@Consumes("application/json")
@Produces("application/json")
public class ProveedorFeedBackResource {
    @Inject
    ProveedorLogic proveedorLogic;

    /**
     * GET para todos los feedbacks pertenecientes a un proveedor especifico
     * @param idProveedor id del proveedor del que se quieren saber los feedbacks
     * @return una lista con todos los feedbacks
     * @throws BusinessLogicException  En caso de que no exista, lanza una excepcion
     */
    @GET
    public List<FeedBackDetailDTO> getFeedBacks(@PathParam("proveedorId") Long idProveedor)
            throws BusinessLogicException {
        ProveedorEntity ent = proveedorLogic.getProveedor(idProveedor);

        if (ent == null) {
            throw new BusinessLogicException("No existe el proveedor con id " + idProveedor);
        }

        List<FeedBackDetailDTO> list = new ArrayList();

        for (FeedBackEntity fbEntity : proveedorLogic.getFeedBacks(idProveedor)) {
            list.add(new FeedBackDetailDTO(fbEntity));
        }

        return list;
    }

    /**
     * GET un feedback especifico de un proveedor especifico
     * @param idProveedor id del proveedor del que se desea conocer el feedback
     * @param idFeedBack id del feedback que se desea conocer
     * @return el feedback detail
     * @throws BusinessLogicException lanza una excepcion cuando no existe el proveedor o el feedback
     */
    @GET
    @Path("/{idFB:\\d+}")
    public FeedBackDetailDTO getFeedBack(@PathParam("proveedorId") Long idProveedor, @PathParam("idFB") Long idFeedBack)
            throws BusinessLogicException {
        ProveedorEntity ent = proveedorLogic.getProveedor(idProveedor);

        if (ent == null) {
            throw new BusinessLogicException("No existe el proveedor con id " + idProveedor);
        }

        FeedBackDetailDTO fbDetail = null;

        for (FeedBackEntity fb : ent.getFeedBacks()) {
            if (fb.getId().equals(idFeedBack)) {
                fbDetail = new FeedBackDetailDTO(fb);
            }
        }

        if (fbDetail == null) {
            throw new BusinessLogicException("No existe el feedback con id " + idFeedBack + " del proveedor con id "
                                             + idProveedor);
        }

        return fbDetail;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

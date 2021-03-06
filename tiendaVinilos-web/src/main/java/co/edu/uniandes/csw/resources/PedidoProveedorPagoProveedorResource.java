
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.PagoProveedorDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PagoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;

//~--- JDK imports ------------------------------------------------------------

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
 * @author s.saenz11
 */
@Path("usuarios/{id0: \\d+}/pedidos/{id: \\d+}/pedidoProveedor/{id3: \\d+}/pagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PedidoProveedorPagoProveedorResource {
    @Inject
    PedidoProveedorLogic logic;
    @Inject
    PagoProveedorLogic   logic1;

    @POST
    public PagoProveedorDetailDTO creatPagoProveedores(@PathParam("id3") Long idPedidoProveedor,
            PagoProveedorDetailDTO provee)
            throws BusinessLogicException {
        logic1.agregarPagoProveedor(provee.toEntity(), logic.getPedidoProveedor(idPedidoProveedor));

        return provee;
    }

    @GET
    public PagoProveedorDetailDTO getPagoProveedor(@PathParam("id3") Long idPedidoProveedor) {
        return new PagoProveedorDetailDTO(logic.getPagoProveedor(idPedidoProveedor));
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

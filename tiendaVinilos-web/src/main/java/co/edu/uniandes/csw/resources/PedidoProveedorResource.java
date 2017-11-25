
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.PedidoProveedorDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;

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
 * @author s.saenz11
 */
@Path("pedidoProveedor")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PedidoProveedorResource {
    @Inject
    PedidoProveedorLogic logic;

    @GET
    public List<PedidoProveedorDetailDTO> getProveedores() {
        List<PedidoProveedorDetailDTO> retList = new ArrayList<>();
        List<PedidoProveedorEntity>    lista   = logic.getAll();

        for (PedidoProveedorEntity en : lista) {
            retList.add(new PedidoProveedorDetailDTO(en));
        }

        return retList;
    }

    @GET
    @Path("{id:\\d+}")
    public PedidoProveedorDetailDTO getProveedor(@PathParam("id") Long id) {
        PedidoProveedorEntity ent = logic.getPedidoProveedor(id);

        return new PedidoProveedorDetailDTO(ent);
    }

    @POST
    public PedidoProveedorDetailDTO createProveedor(PedidoProveedorDetailDTO prov) {
        PedidoProveedorEntity ent = logic.createProveedor(prov.toEntity());

        return new PedidoProveedorDetailDTO(ent);
    }

    @PUT
    @Path("{id: \\d+}")
    public PedidoProveedorDetailDTO updateProveedor(@PathParam("id") Long id, PedidoProveedorDetailDTO prov) {
        PedidoProveedorEntity ent = logic.updatePedidoProveedor(prov.toEntity());

        return new PedidoProveedorDetailDTO(ent);
    }

    @DELETE
    @Path("{id:\\d+}")
    public void deleteProveedor(@PathParam("id") Long id) {
        logic.deletePedidoProveedor(id);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.PagoProveedorDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PagoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;

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
@Path("pagoProveedor")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PagoProveedorResource {
    @Inject
    PagoProveedorLogic logic;

    @GET
    public List<PagoProveedorDetailDTO> getProveedores() {
        List<PagoProveedorDetailDTO> retList = new ArrayList<PagoProveedorDetailDTO>();
        List<PagoProveedorEntity>    lista   = logic.getAll();

        for (PagoProveedorEntity en : lista) {
            retList.add(new PagoProveedorDetailDTO(en));
        }

        return retList;
    }

    @GET
    @Path(("{id:\\d+}"))
    public PagoProveedorDetailDTO getProveedor(@PathParam("id") Long id) {
        PagoProveedorEntity ent = logic.getProveedor(id);

        return (new PagoProveedorDetailDTO(ent));
    }

    @POST
    public PagoProveedorDetailDTO createProveedor(PagoProveedorDetailDTO prov) {
        PagoProveedorEntity ent = logic.createProveedor(prov.toEntity());

        return (new PagoProveedorDetailDTO(ent));
    }

    @PUT
    @Path("{id: \\d+}")
    public PagoProveedorDetailDTO updateProveedor(@PathParam("id") Long id, PagoProveedorDetailDTO prov) {
        PagoProveedorEntity ent = logic.updateProveedor(prov.toEntity());

        return (new PagoProveedorDetailDTO(ent));
    }

    @DELETE
    @Path("{id:\\d+}")
    public void deleteProveedor(@PathParam("id") Long id) {
        logic.deleteProveedor(id);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

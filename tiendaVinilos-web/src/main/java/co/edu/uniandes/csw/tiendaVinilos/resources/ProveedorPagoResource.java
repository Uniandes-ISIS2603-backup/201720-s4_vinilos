/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.PagoProveedorDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PagoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
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
@Path("/proveedores/{idProveedor: \\d+}/pagos")
@Produces("application/json")
@Consumes("application/json")
public class ProveedorPagoResource {
    
    @Inject ProveedorLogic proveedorLogic;
    @Inject PagoProveedorLogic pagoLogic;
    
    @GET
    public List<PagoProveedorDetailDTO> getPagos(@PathParam("idProveedor") Long id) throws BusinessLogicException
    {
        ProveedorEntity ent = proveedorLogic.getProveedor(id);
        if (ent == null)  throw new BusinessLogicException("No existe el proveedor con id " + id);
        List<PagoProveedorDetailDTO> list = new ArrayList();
        for (PagoProveedorEntity entity : ent.getPagos())
            list.add(new PagoProveedorDetailDTO(entity));
        return list;
    }
    
    @GET
    @Path(("{idPP:\\d+}"))
    public PagoProveedorDetailDTO getPago(@PathParam("idProveedor") Long idProveedor, @PathParam("idPP") Long idPago) throws BusinessLogicException
    {
        ProveedorEntity ent = proveedorLogic.getProveedor(idProveedor);
        if (ent == null) throw new BusinessLogicException("No existe el proveedor con id " + idProveedor);
        PagoProveedorDetailDTO ppDto = null;
        for (PagoProveedorEntity ppEnt : ent.getPagos()) 
            if (ppEnt.getId().equals(idPago)) ppDto = new PagoProveedorDetailDTO(ppEnt);
        if (ppDto == null) throw new BusinessLogicException("No existe el pago con id " + idPago + " pertenecietne al proveedor " + idProveedor);
        return ppDto;
    }   
}

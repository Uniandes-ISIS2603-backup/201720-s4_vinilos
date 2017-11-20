/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

import co.edu.uniandes.csw.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
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
 * @author jc.ruiz
 */
 @Path("/proveedores/{idProveedor: \\d+}/vinilos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProveedorVinilosResource {
    
    @Inject ProveedorLogic proveedorLogic;
    @Inject ViniloLogic vinilosLogic;
    
    @GET
    public List<ViniloDetailDTO> getVinilos(@PathParam("idProveedor") Long id) throws BusinessLogicException
    {
        ProveedorEntity ent = proveedorLogic.getProveedor(id);
        List<ViniloDetailDTO> list = new ArrayList();
        for (ViniloEntity vinEnt : ent.getVinilos())
            list.add(new ViniloDetailDTO(vinEnt));
        return list;
    }
    
    
    @GET
    @Path(("/{idVin:\\d+}"))
    public ViniloDetailDTO getVinilo(@PathParam("idProveedor") Long idProv, @PathParam("idVin") Long idVin) throws BusinessLogicException
    {
        ProveedorEntity ent = proveedorLogic.getProveedor(idProv);
        if (ent == null) throw new BusinessLogicException("El proveedor con el id " +  idProv + " no existe");
        ViniloDetailDTO vinDTO = null;
        for (ViniloEntity vinEnt : ent.getVinilos())
            if (vinEnt.getId().equals(idVin)) vinDTO = new ViniloDetailDTO(vinEnt);
        if (vinDTO == null) throw new BusinessLogicException("No existe el vinilo con id " +  idVin + " perteneciente al proveedor con id " + idProv);
        return vinDTO;
    }
        
    @POST
    public ViniloDetailDTO createVinilo(ViniloDetailDTO vinilo, @PathParam("idProveedor") Long idProv) throws BusinessLogicException
    {
        if (idProv == null)  System.out.println("EL ID DEL PROVEDOR ES NULOOOOOOO");
        if (vinilo == null)  System.out.println("EL VINILO ES NULOOOOOOO");
        
        vinilosLogic.agregarVinilo(proveedorLogic.getProveedor(idProv), vinilo.toEntity());
        return vinilo;
    }
    
    @PUT
    @Path(("/{idVin:\\d+}"))
    public ViniloDetailDTO updateVinilo(@PathParam("idProveedor") Long idProv, @PathParam("idVin") Long idVin, ViniloDetailDTO vinDto)
    {
        vinDto.setId(idVin);
        return new ViniloDetailDTO(vinilosLogic.modificarVinilo(proveedorLogic.getProveedor(idProv), idVin, vinDto.toEntity()));
        
    }
   
    @DELETE
    @Path(("/{idVin:\\d+}"))
    public void deleteVinilo(@PathParam("idProveedor") Long idProv, @PathParam("idVin") Long idVin)
    {
        vinilosLogic.eliminarVinilo(idVin);
    }
}
















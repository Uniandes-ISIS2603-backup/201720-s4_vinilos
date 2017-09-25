/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.FeedBackDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.dtos.PedidoProveedorDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
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
@Path("/proveedores/{idProveedor: \\d+}/pedidos")
@Produces("application/json")
@Consumes("application/json")
public class ProveedorPedidoProveedorResource {
    
    @Inject ProveedorLogic proveedorLogic;
    @Inject PedidoProveedorLogic pedidoLogic;
    
    
    @GET
    public List<PedidoProveedorDTO> getPedidos(@PathParam("idProveedor")Long id) throws BusinessLogicException
    {
        ProveedorEntity ent = proveedorLogic.getProveedor(id);
        if (ent == null) throw new BusinessLogicException("No existe el proveedor con id " + id);
        List<PedidoProveedorDTO> list = new ArrayList();
        for (PedidoProveedorEntity PpEntity : proveedorLogic.getPedidos(id))
            list.add(new PedidoProveedorDTO(PpEntity));
        return list;
    }
    
    @GET
    @Path(("{idPedido:\\d+}"))
    public PedidoProveedorDTO getPedido (@PathParam("idProveedor")Long idProveedor, @PathParam("idPedido") Long idPedido) throws BusinessLogicException
    {
        ProveedorEntity ent = proveedorLogic.getProveedor(idProveedor);
        if (ent == null) throw new BusinessLogicException("No existe el proveedor con id " + idProveedor);
        PedidoProveedorDTO pedidoDTO = null;
        for (PedidoProveedorEntity pedidoEnt : ent.getPedidos())
            if (pedidoEnt.getId().equals(idPedido)) pedidoDTO = new PedidoProveedorDTO(pedidoEnt);
        if (pedidoDTO == null) throw new BusinessLogicException("No existe el pedido con id " + idProveedor+ " del proveedor con id " + idProveedor);
        return pedidoDTO;
    }
}

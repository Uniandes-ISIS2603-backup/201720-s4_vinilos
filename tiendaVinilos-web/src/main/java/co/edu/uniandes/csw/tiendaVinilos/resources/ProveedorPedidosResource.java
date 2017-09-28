/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.PedidoProveedorDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *@Path("{proveedorId: \\d+}/pedidos")
 * @author jc.ruiz
 */
@Produces("application/json")
@Consumes("application/json")
public class ProveedorPedidosResource {
 
    @Inject private ProveedorLogic proveedorLogic;
    
    
    
    /**
     * GET para todos los pedidos pertenecientes a un proveedor especifico
     * @param idProveedor id del proveedor del que se quieren saber los pedidos
     * @return una lista con todos los pedidos
     * @throws BusinessLogicException  En caso de que no exista, lanza una excepcion
     */
    @GET 
    public List<PedidoProveedorDTO> getPedidos (@PathParam("proveedorId") Long idProveedor) throws BusinessLogicException
    {
        ProveedorEntity ent = proveedorLogic.getProveedor(idProveedor);
        if (ent == null) throw new BusinessLogicException("No existe el proveedor con id " + idProveedor);
        List<PedidoProveedorDTO> list = new ArrayList();
        for (PedidoProveedorEntity ppEntity : proveedorLogic.getPedidos(idProveedor))
            list.add(new PedidoProveedorDTO(ppEntity));
        return list;
    }
    
    /**
     * GET un pedido especifico de un proveedor especifico
     * @param idProveedor id del proveedor del que se desea conocer el pedido
     * @param idFeedBack id del pedido que se desea conocer
     * @return el pedido detail 
     * @throws BusinessLogicException lanza una excepcion cuando no existe el proveedor o el feedback 
     */
    @GET
    @Path(("/{idFB:\\d+}"))
    public PedidoProveedorDTO getPedido ( @PathParam("proveedorId") Long idProveedor, @PathParam("idFB") Long idFeedBack) throws BusinessLogicException
    {
        ProveedorEntity ent = proveedorLogic.getProveedor(idProveedor);
        if (ent == null) throw new BusinessLogicException("No existe el proveedor con id " + idProveedor);
        PedidoProveedorDTO fbDetail = null;
        for (PedidoProveedorEntity pP : ent.getPedidos())
            if (pP.getId().equals(idFeedBack)) fbDetail = (new PedidoProveedorDTO(pP));
        if (fbDetail == null) throw new BusinessLogicException("No existe el feedback con id " + idFeedBack + " del proveedor con id " + idProveedor);
        return fbDetail;
    }
}


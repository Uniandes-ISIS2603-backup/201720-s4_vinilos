
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.PedidoProveedorDTO;
import co.edu.uniandes.csw.dtos.PedidoProveedorDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @Path("{proveedorId: \\d+}/pedidos")
 * @author jc.ruiz
 */
@Path("proveedores/{id: \\d+}/pedidos")
@Produces("application/json")
@Consumes("application/json")
public class ProveedorPedidosResource {
    @Inject
    private ProveedorLogic       proveedorLogic;
    @Inject
    private PedidoProveedorLogic pedidoProveedorLogic;

    /**
     * GET para todos los pedidos pertenecientes a un proveedor especifico
     * @param idProveedor id del proveedor del que se quieren saber los pedidos
     * @return una lista con todos los pedidos
     * @throws BusinessLogicException  En caso de que no exista, lanza una excepcion
     */
    @GET
    public List<PedidoProveedorDetailDTO> getPedidos(@PathParam("id") Long idProveedor) throws BusinessLogicException {
        return listEntity2DetailDTO(proveedorLogic.getPedidos(idProveedor));
    }

    /**
     * GET un pedido especifico de un proveedor especifico
     * @param id2
     * @return el pedido detail
     * @throws BusinessLogicException lanza una excepcion cuando no existe el proveedor o el feedback
     */
    @GET
    @Path("/{id2:\\d+}")
    public PedidoProveedorDTO getPedido( @PathParam("id2") Long id2)
            throws BusinessLogicException {
        PedidoProveedorEntity pedido = pedidoProveedorLogic.getPedidoProveedor(id2);

        return new PedidoProveedorDetailDTO(pedido);
    }
    
     @DELETE
    @Path("/{id2:\\d+}")
    public void deletePedido( @PathParam("id2") Long id2)
            throws BusinessLogicException {
         pedidoProveedorLogic.deletePedidoProveedor(id2);

       
    }
    
     @PUT
    @Path("/{id2:\\d+}")
    public void editPedido(PedidoProveedorDetailDTO prov)
            throws BusinessLogicException {
         pedidoProveedorLogic.updatePedidoProveedor(prov.toEntity());

       
    }

    private List<PedidoProveedorDetailDTO> listEntity2DetailDTO(List<PedidoProveedorEntity> entityList) {
        List<PedidoProveedorDetailDTO> list = new ArrayList<>();

        for (PedidoProveedorEntity entity : entityList) {
            PedidoProveedorDetailDTO dto = new PedidoProveedorDetailDTO(entity);

            list.add(dto);
        }

        return list;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
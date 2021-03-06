
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.CarroComprasDetailDTO;
import co.edu.uniandes.csw.dtos.PedidoProveedorDetailDTO;
import co.edu.uniandes.csw.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.CarroComprasLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoClienteLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author s.saenz11
 */
@Path("usuarios/{id0: \\d+}/pedidos/{id: \\d+}/pedidoProveedor")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PedidoClientePedidoProveedorResource {
    @Inject
    private PedidoClienteLogic   pedidoClienteLogic;
    @Inject
    private PedidoProveedorLogic pedidoProveedorLogic;
    @Inject
    CarroComprasLogic            carroComprasLogic;

    @GET
    @Path("/carroCompras")
    public CarroComprasDetailDTO getCarroCompras(@PathParam("id0") Long id) throws BusinessLogicException {
        return new CarroComprasDetailDTO(pedidoClienteLogic.getUsuario(id).getCarrito());
    }

    @GET
    public List<PedidoProveedorDetailDTO> getPedidoProveedor(@PathParam("id") Long id) throws BusinessLogicException {
        return listEntity2DetailDTO(pedidoClienteLogic.getPedidoProveedor(id));
    }

    @GET
    @Path("/{id2:\\d+}")
    public PedidoProveedorDetailDTO getIdPedidoProveedor(@PathParam("id") Long id) {
        PedidoProveedorEntity entity = pedidoProveedorLogic.getPedidoProveedor(id);

        pedidoProveedorLogic.getPedidoProveedor(id);

        return new PedidoProveedorDetailDTO(entity);
    }

    @GET
    @Path("/{id2:\\d+}/vinilos")
    public List<ViniloDetailDTO> getVinilosPedidoProveedor(@PathParam("id") Long id) {
        List<ViniloEntity> entity = pedidoProveedorLogic.getPedidoProveedor(id).getViniloEntity();

        pedidoProveedorLogic.getPedidoProveedor(id);

        return listEntity2DetailDTO2(entity);
    }

    @POST
    public void createPedidoProveedor(PedidoProveedorDetailDTO pedidoProveedor, @PathParam("id") Long id)
            throws BusinessLogicException {
        for (int i = 0; i < pedidoClienteLogic.getPedido(id).getUsuario().getCarrito().getVinilos().size(); i++) {
            pedidoProveedorLogic.agregarPedidoProveedor(pedidoClienteLogic.getPedido(id), pedidoProveedor.toEntity(),
                    pedidoClienteLogic.getPedido(id).getUsuario().getCarrito().getVinilos().get(i));
        }
    }

    @DELETE
    @Path("/{id2:\\d+}")
    public void deletePedidoProveedor(@PathParam("id2") Long id2) {
        pedidoProveedorLogic.deletePedidoProveedor(id2);
    }

    private List<PedidoProveedorDetailDTO> listEntity2DetailDTO(List<PedidoProveedorEntity> entityList) {
        List<PedidoProveedorDetailDTO> list = new ArrayList<>();

        for (PedidoProveedorEntity entity : entityList) {
            PedidoProveedorDetailDTO dto = new PedidoProveedorDetailDTO(entity);

            list.add(dto);
        }

        return list;
    }

    private List<ViniloDetailDTO> listEntity2DetailDTO2(List<ViniloEntity> entityList) {
        List<ViniloDetailDTO> list = new ArrayList<>();

        for (ViniloEntity entity : entityList) {
            ViniloDetailDTO dto = new ViniloDetailDTO(entity);

            list.add(dto);
        }

        return list;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

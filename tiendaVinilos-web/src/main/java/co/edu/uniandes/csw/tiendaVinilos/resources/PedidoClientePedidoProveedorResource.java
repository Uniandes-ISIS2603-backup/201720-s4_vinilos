/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.PedidoProveedorDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoClienteLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import static com.sun.tools.javac.code.Lint.LintCategory.PATH;
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

@Path("pedidocliente/{id: \\d+}/pedidoProveedor")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PedidoClientePedidoProveedorResource {
    
    @Inject
    private PedidoClienteLogic pedidoClienteLogic;
    
    @Inject
    private PedidoProveedorLogic pedidoProveedorLogic;
    
    @GET
    public List<PedidoProveedorDetailDTO> getPedidoProveedor(@PathParam("id")Long id )throws BusinessLogicException 
    {
        return listEntity2DetailDTO(pedidoClienteLogic.getPedidoProveedor(id));
    }
    
    @GET
    @Path("/{id2:\\d+}")
    public PedidoProveedorDetailDTO getIdPedidoProveedor(@PathParam("id") Long id,@PathParam("id2")Long id2)
    {
        PedidoProveedorEntity entity = pedidoProveedorLogic.getProveedor(id2);
        pedidoProveedorLogic.getProveedor(id);
        return new PedidoProveedorDetailDTO(entity);
    }
    
    @POST 
    public PedidoProveedorDetailDTO createPedidoProveedor(PedidoProveedorDetailDTO pedidoProveedor , @PathParam("id") Long id) throws BusinessLogicException
    {
        pedidoProveedorLogic.agregarPedidoProveedor(pedidoClienteLogic.getPedido(id), pedidoProveedor.toEntity());
        return pedidoProveedor;
    }
    
    @DELETE
    @Path("/{id2:\\d+}")
      public void deletePedidoProveedor(@PathParam("id") Long id,@PathParam("id2")Long id2) 
      {
          PedidoProveedorEntity entity = pedidoProveedorLogic.getProveedor(id2);
          pedidoProveedorLogic.deletePedidoProveedor(entity);
          
      }
     private List<PedidoProveedorDetailDTO> listEntity2DetailDTO(List<PedidoProveedorEntity> entityList) {
        List<PedidoProveedorDetailDTO> list = new ArrayList<>();
        for (PedidoProveedorEntity entity : entityList) {
            PedidoProveedorDetailDTO dto= new PedidoProveedorDetailDTO(entity);
            list.add(dto);
        }
        return list;
    }
}

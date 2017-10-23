/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.resources;

import co.edu.uniandes.csw.tiendaVinilos.dtos.PedidoClienteDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoClienteLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
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
 * @author jd.arenas
 */
 @Path("usuarios/{id: \\d+}/pedidos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioPedidoClienteResource {
    @Inject
    UsuarioLogic usuarioLogic;
    @Inject
    PedidoClienteLogic pedidoClienteLogic;
    
     @GET
    public List<PedidoClienteDetailDTO> getPedidoClientes(@PathParam("id") Long id) throws BusinessLogicException {
        
        return listEntity2DetailDTO(usuarioLogic.getPedidos(id));
    }
     @GET
    @Path("/{id2:\\d+}")
    public PedidoClienteDetailDTO getPedidoCliente(@PathParam("id") Long id,@PathParam("id2")Long id2) throws BusinessLogicException
    {
        PedidoClienteEntity pedido= pedidoClienteLogic.getPedido(id2);
        return new PedidoClienteDetailDTO(pedido);
    }
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos UsuarioEntity a una lista de
     * objetos UsuarioDetailDTO (json)
     *
     * @param entityList corresponde a la lista de usuarioes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de usuarioes en forma DTO (json)
     */
    private List<PedidoClienteDetailDTO> listEntity2DetailDTO(List<PedidoClienteEntity> entityList) {
        List<PedidoClienteDetailDTO> list = new ArrayList<>();
        for (PedidoClienteEntity entity : entityList) {
            PedidoClienteDetailDTO dto= new PedidoClienteDetailDTO(entity);
            list.add(dto);
        }
        return list;
    }
    @POST
    public PedidoClienteDetailDTO createPedidoCliente(PedidoClienteDetailDTO pedidoCliente,@PathParam("id") Long id) throws BusinessLogicException {
        pedidoClienteLogic.createPedido(pedidoCliente.toEntity(),usuarioLogic.getUsuario(id));
        return pedidoCliente;
    }
    @PUT
    @Path("/{id2:\\d+}")
    public PedidoClienteDetailDTO updatePedidoCliente(@PathParam("id") Long id,@PathParam("id2")Long id2,PedidoClienteDetailDTO nuevo) throws BusinessLogicException
    {
        nuevo.setId(id2);
        pedidoClienteLogic.updatePedido(id2, nuevo.toEntity(),nuevo.getUsuario().toEntity());
        return nuevo;
    }
     @DELETE
    @Path("/{id2:\\d+}")
    public void deletePedidoCliente(@PathParam("id") Long id,@PathParam("id2")Long id2) throws BusinessLogicException
    {
        pedidoClienteLogic.deletePedido(id2);
    }
    
}

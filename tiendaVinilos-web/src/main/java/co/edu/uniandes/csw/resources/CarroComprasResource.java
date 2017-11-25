
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.CarroComprasDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.CarroComprasLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author cs.gomez
 */
@Path("carroCompras")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CarroComprasResource {
    private static final String NOEXISTE = " no existe.";
    @Inject
    CarroComprasLogic           logic;    // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public CarroComprasDetailDTO createCarroCompras(CarroComprasDetailDTO carro) throws BusinessLogicException {

        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        CarroComprasEntity nuevoCarro = logic.createCarroCompras(carro.toEntity());

        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new CarroComprasDetailDTO(nuevoCarro);
    }

    @GET
    public List<CarroComprasDetailDTO> getAllCarroCompras() throws BusinessLogicException {
        return listEntity2DetailDTO(logic.getAllCarroCompras());
    }

    @GET
    @Path("{id: \\d+}")
    public CarroComprasDetailDTO getCarroCompras(@PathParam("id") Long id) throws BusinessLogicException {
        CarroComprasEntity entity = logic.getCarroCompras(id);

        if (entity == null) {
            throw new WebApplicationException("El recurso /CarroCompras/" + id + NOEXISTE, 404);
        }

        return new CarroComprasDetailDTO(entity);
    }

    @PUT
    @Path("{id: \\d+}")
    public CarroComprasDetailDTO updateCarroCompras(@PathParam("id") Long id, CarroComprasDetailDTO carro)
            throws BusinessLogicException {
        carro.setId(id);

        CarroComprasEntity entity = logic.getCarroCompras(id);

        if (entity == null) {
            throw new WebApplicationException("El recurso /CarroCompra/" + id + NOEXISTE, 404);
        }

        return new CarroComprasDetailDTO(logic.updateCarroCompras(id, carro.toEntity()));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteCarroCompras(@PathParam("id") Long id) throws BusinessLogicException {
        CarroComprasEntity entity = logic.getCarroCompras(id);

        if (entity == null) {
            throw new WebApplicationException("El recurso /CarroCompra/" + id + NOEXISTE, 404);
        }

        logic.deleteCarroCompras(id);
    }

    private List<CarroComprasDetailDTO> listEntity2DetailDTO(List<CarroComprasEntity> entityList) {
        List<CarroComprasDetailDTO> list = new ArrayList<>();

        for (CarroComprasEntity entity : entityList) {
            list.add(new CarroComprasDetailDTO(entity));
        }

        return list;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

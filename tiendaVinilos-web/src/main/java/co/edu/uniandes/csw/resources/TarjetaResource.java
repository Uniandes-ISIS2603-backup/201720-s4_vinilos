
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.dtos.TarjetaDetailDTO;

/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
import co.edu.uniandes.csw.tiendaVinilos.ejb.TarjetaLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
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
 * Clase que implementa el recurso REST correspondiente a "tarjetas".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "tarjetas". Al ejecutar la aplicación, el
 * recurso será accesibe a través de la ruta "/api/tarjetas"
 *
 * @author ISIS2603
 *
 */
@Path("tarjetas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetaResource {
    @Inject
    TarjetaLogic tarjetaLogic;    // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final String NOEXISTE = " no existe ";
    private static final String RESTARJETA =  "El recurso /tarjetas/";
    
    /**
     * POST http://localhost:8080/tiendaVinilos-web/api/tarjetas Ejemplo
     * json: { "name":"Norma" }
     *
     * @param tarjeta correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "tarjetaDetailDTO", "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     */
    @POST
    public TarjetaDetailDTO createTarjeta(TarjetaDetailDTO tarjeta) throws BusinessLogicException {

        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        // Invoca la lógica para crear la tarjeta nueva
        TarjetaEntity nuevoTarjeta = tarjetaLogic.createTarjeta(tarjeta.toEntity());

        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new TarjetaDetailDTO(nuevoTarjeta);
    }

    /**
     * GET para todas las tarjetaes.
     * http://localhost:8080/tiendaVinilos-web/api/tarjetas     
     * @return la lista de todas los tarjetas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<TarjetaDetailDTO> getTarjetas() throws BusinessLogicException {
        return listEntity2DetailDTO(tarjetaLogic.getTarjetas());
    }

    /**
     * GET para un tarjeta
     * http://localhost:8080/tiendaVinilos-web/api/tarjetas     
     * @param id corresponde al id de la tarjeta buscada.
     * @return La tarjeta encontrada. Ejemplo: { "type": "tarjetaDetailDTO",
     * "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del tarjeta buscado se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public TarjetaDetailDTO getTarjeta(@PathParam("id") Long id) throws BusinessLogicException {
        TarjetaEntity entity = tarjetaLogic.getTarjeta(id);

        if (entity == null) {
            throw new WebApplicationException(RESTARJETA + id + NOEXISTE, 404);
        }

        return new TarjetaDetailDTO(tarjetaLogic.getTarjeta(id));
    }

    /**
     * PUT http://localhost:8080/tiendaVinilos-web/api/tarjetas/1 Ejemplo
     * json { "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde a la tarjeta a actualizar.
     * @param tarjeta corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La tarjeta actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la tarjeta a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public TarjetaDetailDTO updateTarjeta(@PathParam("id") Long id, TarjetaDetailDTO tarjeta)
            throws BusinessLogicException {
        tarjeta.setId(id);

        TarjetaEntity entity = tarjetaLogic.getTarjeta(id);

        if (entity == null) {
            throw new WebApplicationException(RESTARJETA + id + NOEXISTE, 404);
        }

        return new TarjetaDetailDTO(tarjetaLogic.updateTarjeta(id, tarjeta.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/tiendaVinilos-web/api/tarjetas/1
     *
     * @param id corresponde a la tarjeta a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la tarjeta a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTarjeta(@PathParam("id") Long id) throws BusinessLogicException {
        TarjetaEntity entity = tarjetaLogic.getTarjeta(id);

        if (entity == null) {
            throw new WebApplicationException(RESTARJETA + id + NOEXISTE, 404);
        }

        tarjetaLogic.deleteTarjeta(id);
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos TarjetaEntity a una lista de
     * objetos TarjetaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de tarjetaes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de tarjetaes en forma DTO (json)
     */
    private List<TarjetaDetailDTO> listEntity2DetailDTO(List<TarjetaEntity> entityList) {
        List<TarjetaDetailDTO> list = new ArrayList<>();

        for (TarjetaEntity entity : entityList) {
            list.add(new TarjetaDetailDTO(entity));
        }

        return list;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

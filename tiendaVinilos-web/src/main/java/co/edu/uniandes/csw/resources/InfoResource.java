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
package co.edu.uniandes.csw.resources;

import co.edu.uniandes.csw.dtos.InfoDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.InfoLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.InfoEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;

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
 * Clase que implementa el recurso REST correspondiente a "infos".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "infos". Al ejecutar la aplicación, el recurse
 * será accesible a través de la ruta "/api/books/idBook/infos"
 *
 *
 * @author ISIS2603
 *
 */
@Produces("application/json")
@Consumes("application/json")
public class InfoResource {

    @Inject
    InfoLogic infoLogic;

    @GET
    public List<InfoDTO> getInfos(@PathParam("idVinilo") Long idVinilo) throws BusinessLogicException {
        return listEntity2DTO(infoLogic.getInfos(idVinilo));
    }

    @GET
    @Path("{id: \\d+}")
    public InfoDTO getInfo(@PathParam("idVinilo") Long idVinilo, @PathParam("id") Long id) throws BusinessLogicException {
        InfoEntity entity = infoLogic.getInfo(idVinilo, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /vinilos/" + idVinilo + "/infos/" + id + " no existe.", 404);
        }
        return new InfoDTO(entity);
    }

    @POST
    public InfoDTO createInfo(@PathParam("idVinilo") Long idVinilo, InfoDTO info) throws BusinessLogicException {
        return new InfoDTO(infoLogic.createInfo(idVinilo, info.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public InfoDTO updateInfo(@PathParam("idVinilo") Long idVinilo, @PathParam("id") Long id, InfoDTO info) throws BusinessLogicException {
        info.setId(id);
        InfoEntity entity = infoLogic.getInfo(idVinilo, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /vinilos/" + idVinilo + "/infos/" + id + " no existe.", 404);
        }
        return new InfoDTO(infoLogic.updateInfo(idVinilo, info.toEntity()));

    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteInfo(@PathParam("idVinilo") Long idVinilo, @PathParam("id") Long id) throws BusinessLogicException {
        InfoEntity entity = infoLogic.getInfo(idVinilo, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /vinilos/" + idVinilo+ "/infos/" + id + " no existe.", 404);
        }
        infoLogic.deleteInfo(idVinilo, id);
    }

    private List<InfoDTO> listEntity2DTO(List<InfoEntity> entityList) {
        List<InfoDTO> list = new ArrayList<>();
        for (InfoEntity entity : entityList) {
            list.add(new InfoDTO(entity));
        }
        return list;
    }

}

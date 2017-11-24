
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

//~--- non-JDK imports --------------------------------------------------------

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
import co.edu.uniandes.csw.dtos.TarjetaDTO;
import co.edu.uniandes.csw.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.tiendaVinilos.ejb.CarroComprasLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.TarjetaLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "usuarios".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "usuarios". Al ejecutar la aplicación, el recurso
 * será accesibe a través de la ruta "/api/usuarios"
 *
 * @author ISIS2603
 *
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    @Inject
    UsuarioLogic      usuarioLogic;    // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    @Inject
    CarroComprasLogic carroLogic;
    @Inject
    TarjetaLogic      tarjetaLogic;

    private static final String NOEXISTE = " no existe ";
    private static final String RESUSUARIO = "El recurso /usuarios/";
    
    /**
     * POST http://localhost:8080/tiendaVinilos-web/api/usuarios Ejemplo json: {
     * "name":"Norma" }
     *
     * @param usuario correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "usuarioDetailDTO", "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     */
    @POST
    public UsuarioDetailDTO createUsuario(UsuarioDetailDTO usuario) throws BusinessLogicException {

        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        UsuarioEntity usuarioEntity = usuario.toEntity();

        // Invoca la lógica para crear la usuario nueva
        CarroComprasEntity carro = new CarroComprasEntity();

        carro.setPrecioTotal(0);
        carro.setVinilos(new ArrayList<ViniloEntity>());
        usuarioEntity.setCarrito(carro);

        UsuarioEntity nuevoUsuario = usuarioLogic.createUsuario(usuario.toEntity());

        carro.setUsuario(nuevoUsuario);
        carroLogic.createCarroCompras(carro);

        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new UsuarioDetailDTO(nuevoUsuario);
    }

    /**
     * GET para todas las usuarioes.
     * http://localhost:8080/tiendaVinilos-web/api/usuarios
     *
     *
     * @return la lista de todas los usuarios en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios() throws BusinessLogicException {
        return listEntity2DetailDTO(usuarioLogic.getUsuarios());
    }

    /**
     * GET para un usuario http://localhost:8080/tiendaVinilos-web/api/usuarios
     *
     *
     * @param id corresponde al id de la usuario buscada.
     * @return La usuario encontrada. Ejemplo: { "type": "usuarioDetailDTO",
     * "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del usuario buscado se retorna un 404 con el
     * mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) throws BusinessLogicException {
        UsuarioEntity entity = usuarioLogic.getUsuario(id);

        if (entity == null) {
            throw new WebApplicationException(RESUSUARIO + id + NOEXISTE, 404);
        }

        UsuarioDetailDTO usu = new UsuarioDetailDTO(usuarioLogic.getUsuario(id));

        usu.setTarjetas((ArrayList<TarjetaDTO>) listEntity2DetailDTOTarjeta(tarjetaLogic.darTarjetasUsuario(id)));

        return usu;
    }

    /**
     * PUT http://localhost:8080/tiendaVinilos-web/api/usuarios/1 Ejemplo json {
     * "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde a la usuario a actualizar.
     * @param usuario corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La usuario actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la usuario a actualizar se retorna un 404
     * con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("id") Long id, UsuarioDetailDTO usuario)
            throws BusinessLogicException {
        usuario.setId(id);

        UsuarioEntity entity = usuarioLogic.getUsuario(id);

        if (entity == null) {
            throw new WebApplicationException(RESUSUARIO + id + NOEXISTE, 404);
        }

        return new UsuarioDetailDTO(usuarioLogic.updateUsuario(id, usuario.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/tiendaVinilos-web/api/usuarios/1
     *
     * @param id corresponde a la usuario a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la usuario a actualizar se retorna un 404
     * con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUsuario(@PathParam("id") Long id) throws BusinessLogicException {
        UsuarioEntity entity = usuarioLogic.getUsuario(id);

        if (entity == null) {
            throw new WebApplicationException(RESUSUARIO + id + NOEXISTE, 404);
        }

        usuarioLogic.deleteUsuario(id);
    }

    /*
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
    private List<UsuarioDetailDTO> listEntity2DetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();

        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }

        return list;
    }

    private List<TarjetaDTO> listEntity2DetailDTOTarjeta(List<TarjetaEntity> entityList) {
        List<TarjetaDTO> list = new ArrayList<>();

        for (TarjetaEntity entity : entityList) {
            list.add(new TarjetaDTO(entity));
        }

        return list;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

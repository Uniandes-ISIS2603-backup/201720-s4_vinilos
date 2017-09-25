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
package co.edu.uniandes.csw.tiendaVinilos.ejb;



import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;

import co.edu.uniandes.csw.tiendaVinilos.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class UsuarioLogic {

    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private UsuarioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Usuario");
        // Invoca la persistencia para crear la Usuario
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Usuario");
        return entity;
    }

    /**
     * 
     * Obtener todas las Usuarioes existentes en la base de datos.
     *
     * @return una lista de Usuarioes.
     */
    public List<UsuarioEntity> getUsuarios() {
        LOGGER.info("Inicia proceso de consultar todas las Usuarioes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<UsuarioEntity> Usuario = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Usuarioes");
        return Usuario;
    }
    public UsuarioEntity getUsuario(long id)
    {
        UsuarioEntity ent=persistence.find(id);
        return ent;
    }
    public UsuarioEntity updateUsuario(long id,UsuarioEntity us)
    {
        persistence.update(us);
        return us;
    }
    public void deleteUsuario(long id)
    {
        persistence.delete(id);
    }
    public List<FeedBackEntity> getFeedBacks(Long id)
    {
        UsuarioEntity usu=getUsuario(id);
        return usu.getFeedBacks();
    }
    public FeedBackEntity agregarFeedBack(Long idUsuario,Proveedorentity proveedor)
    {
        UsuarioEntity usu=getUsuario(idUsuario);
        
    }


}

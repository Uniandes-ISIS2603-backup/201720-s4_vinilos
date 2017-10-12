
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.ArtistaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author cs.gomez
 */
@Stateless
public class ArtistaLogic {
    
    

    @Inject
    private ArtistaPersistence persistence; 

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public ArtistaEntity createArtista(ArtistaEntity entity) throws BusinessLogicException {

        if(persistence.find(entity.getId())!=null){
            throw new BusinessLogicException("Ya existe un Artista con el id\"" + entity.getId() + "\"");
        }
        // Invoca la persistencia para crear el Artista
        persistence.create(entity);
        return entity;
    }

    /**
     * 
     * Obtener todas los Artistas existentes en la base de datos.
     *
     * @return una lista de Artistas.
     */
    public List<ArtistaEntity> getArtistas() {
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ArtistaEntity> artista = persistence.findAll();
        return artista;
    }
    public ArtistaEntity getArtista(long id)
    {
        ArtistaEntity ent=persistence.find(id);
        return ent;
    }
    public ArtistaEntity updateArtista(long id,ArtistaEntity us)
    {
        persistence.update(us);
        return us;
    }
    public void deleteArtista(long id)
    {
        persistence.delete(id);
    }

}

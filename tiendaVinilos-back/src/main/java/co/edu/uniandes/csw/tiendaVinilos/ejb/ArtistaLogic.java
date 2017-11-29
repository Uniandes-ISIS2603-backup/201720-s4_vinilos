
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.ArtistaPersistence;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;

import javax.ejb.Stateless;

import javax.inject.Inject;

/**
 *
 * @author cs.gomez
 */
@Stateless
public class ArtistaLogic {
    
    private ArtistaPersistence persistence;
    
    @Inject
    public ArtistaLogic(ArtistaPersistence persistence)
    {
        this.persistence = persistence;
    }
    
    public ArtistaLogic(){
        this.persistence = null;
    }

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public ArtistaEntity createArtista(ArtistaEntity entity) throws BusinessLogicException {
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
        return persistence.findAll();
    }

    public ArtistaEntity getArtista(long id) {
        return persistence.find(id);
    }

    public ArtistaEntity updateArtista(long id,ArtistaEntity us) {
        persistence.update(us);

        return us;
    }

    public void deleteArtista(long id) {
        persistence.delete(id);
    }

    // ----------RECURSOS DE ARTISTA-CANCION-----------
    public List<CancionEntity> getCanciones(Long id) {
        ArtistaEntity ent = persistence.find(id);

        return ent.getCanciones();
    }

    public void addArtista(Long id, CancionEntity canEnt) {
        ArtistaEntity ent = persistence.find(id);

        ent.getCanciones().add(canEnt);
        persistence.update(ent);
    }

    public CancionEntity getCancionFromArtista(ArtistaEntity artista, Long idCancion) {
        CancionEntity       cancionResult = null;
        List<CancionEntity> canciones     = artista.getCanciones();

        for (CancionEntity can : canciones) {
            if (can.getId() == idCancion) {
                cancionResult = can;
            }
        }

        return cancionResult;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.CancionPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author cs.gomez
 */
@Stateless
public class CancionLogic {
    
    @Inject
    CancionPersistence persistence;
    
    
    
    
    public CancionEntity createCancion(CancionEntity entity) throws BusinessLogicException {
        // Invoca la persistencia para crear la Tarjeta
        persistence.create(entity);
        return entity;
    }

   
    public List<CancionEntity> getCanciones() {
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<CancionEntity> Tarjeta = persistence.findAll();
        return Tarjeta;
    }
    
    
    public CancionEntity getCancion(Long id)
    {
        CancionEntity ent = persistence.find(id);
        return ent;
    }
    
    
    public CancionEntity updateCancion(Long id,CancionEntity entiy)
    {
        persistence.update(entiy);
        return entiy;
    }
    
    
    public void deleteCancion(Long id)
    {
        persistence.delete(id);
    }
    
    
    //----------RECURSOS ARTISTA-CANCION--------
    
    public CancionEntity addArtista(ArtistaEntity artista, CancionEntity cancion)
    {
        cancion.setArtistas(artista);
        return updateCancion(cancion.getId(), cancion);
    }
    
    public void sacarDeArtista(CancionEntity cancion)
    {
        cancion.setArtistas(null);
        deleteCancion(cancion.getId());
    }
    
    
    //----------RECURSOS VINILOS-CANCION--------
    
    public CancionEntity addVinilo(ViniloEntity vinilo, CancionEntity cancion){
        cancion.setVinilos(vinilo);
        return updateCancion(cancion.getId(), cancion);
    }
    
    
    
}






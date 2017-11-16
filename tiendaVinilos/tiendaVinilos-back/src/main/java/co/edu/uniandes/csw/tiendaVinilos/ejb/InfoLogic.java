/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

import co.edu.uniandes.csw.tiendaVinilos.entities.InfoEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.InfoPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jp.monsalvo
 */
@Stateless
public class InfoLogic {

    @Inject
    private InfoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public InfoEntity createInfo(InfoEntity entity) throws BusinessLogicException {
        // Invoca la persistencia para crear el Info
        if(persistence.find(entity.getId())!=null){
             throw new BusinessLogicException("Ya existe un info con el id \"" + entity.getId() + "\"");
        }
        persistence.create(entity);
        return entity;
    }

    /**
     * 
     * Obtener todas las Infoes existentes en la base de datos.
     *
     * @return una lista de Infoes.
     */
    public List<InfoEntity> getInfos() {
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<InfoEntity> Info = persistence.findAll();
        return Info;
    }
    public InfoEntity getInfo(long id)
    {
        InfoEntity ent=persistence.find(id);
        return ent;
    }
    public InfoEntity updateInfo(long id,InfoEntity us)
    {
        persistence.update(us);
        return us;
    }
    public void deleteInfo(long id)
    {
        persistence.delete(id);
    }


}

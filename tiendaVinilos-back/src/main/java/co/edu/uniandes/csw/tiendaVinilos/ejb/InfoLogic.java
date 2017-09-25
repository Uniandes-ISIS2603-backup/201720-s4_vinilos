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
import javax.inject.Inject;

/**
 *
 * @author jp.monsalvo
 */
public class InfoLogic {
    private static final Logger LOGGER = Logger.getLogger(InfoLogic.class.getName());

    @Inject
    private InfoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public InfoEntity createInfo(InfoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación del Info");
        // Invoca la persistencia para crear el Info
        if(persistence.find(entity.getId())!=null){
             throw new BusinessLogicException("Ya existe un info con el id \"" + entity.getId() + "\"");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Info");
        return entity;
    }

    /**
     * 
     * Obtener todas las Infoes existentes en la base de datos.
     *
     * @return una lista de Infoes.
     */
    public List<InfoEntity> getInfos() {
        LOGGER.info("Inicia proceso de consultar todas las Infoes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<InfoEntity> Info = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Infoes");
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

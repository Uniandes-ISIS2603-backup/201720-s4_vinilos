package co.edu.uniandes.csw.tiendaVinilos.ejb;


import co.edu.uniandes.csw.tiendaVinilos.entities.InfoEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.InfoPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author ISIS2603
 */
@Stateless
public class InfoLogic {

    private static final Logger LOGGER = Logger.getLogger(ViniloLogic.class.getName());

    @Inject
    private InfoPersistence persistence;

    @Inject
    private ViniloLogic viniloLogic;

    /**
     * Obtiene la lista de los registros de Info que pertenecen a un Vinilo.
     *
     * @param viniloid id del Vinilo el cual es padre de los Infos.
     * @return Colección de objetos de InfoEntity.
     * @throws co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException
     */
    public List<InfoEntity> getInfos(Long viniloid) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los infos");
        ViniloEntity vinilo = viniloLogic.getVinilo(viniloid);
        if (vinilo.getInfos() == null) {
            throw new BusinessLogicException("El vinilo que consulta aún no tiene infos");
        }
        if (vinilo.getInfos().isEmpty()) {
            throw new BusinessLogicException("El vinilo que consulta aún no tiene infos");
        }
        return vinilo.getInfos();
    }

    /**
     * Obtiene los datos de una instancia de Info a partir de su ID.
     *
     * @param viniloid
     * @pre La existencia del elemento padre Vinilo se debe garantizar.
     * @param infoid) Identificador del Info a consultar
     * @return Instancia de InfoEntity con los datos del Info consultado.
     * 
     */
    public InfoEntity getInfo(Long viniloid, Long infoid) {
        return persistence.find(viniloid, infoid);
    }

    /**
     * Se encarga de crear un Info en la base de datos.
     *
     * @param entity Objeto de InfoEntity con los datos nuevos
     * @param viniloid id del Vinilo el cual sera padre del nuevo Info.
     * @return Objeto de InfoEntity con los datos nuevos y su ID.
     * 
     */
    public InfoEntity createInfo(Long viniloid, InfoEntity entity) {
        LOGGER.info("Inicia proceso de crear info");
        ViniloEntity vinilo = viniloLogic.getVinilo(viniloid);
        entity.setVinilo(vinilo);
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Info.
     *
     * @param entity Instancia de InfoEntity con los nuevos datos.
     * @param viniloid id del Vinilo el cual sera padre del Info actualizado.
     * @return Instancia de InfoEntity con los datos actualizados.
     * 
     */
    public InfoEntity updateInfo(Long viniloid, InfoEntity entity) {
        LOGGER.info("Inicia proceso de actualizar info");
        ViniloEntity vinilo = viniloLogic.getVinilo(viniloid);
        entity.setVinilo(vinilo);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Info de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param viniloid id del Vinilo el cual es padre del Info.
     * 
     */
    public void deleteInfo(Long viniloid, Long id) {
        LOGGER.info("Inicia proceso de borrar info");
        InfoEntity old = getInfo(viniloid, id);
        persistence.delete(old.getId());
    }

}
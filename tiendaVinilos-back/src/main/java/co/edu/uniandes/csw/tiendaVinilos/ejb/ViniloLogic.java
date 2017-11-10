/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;


import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.ViniloPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jp.monsalvo
 */
@Stateless
public class ViniloLogic {
private static final Logger LOGGER = Logger.getLogger(ViniloLogic.class.getName());

    @Inject
    private ViniloPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public ViniloEntity createVinilo(ViniloEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación del Vinilo");
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe un vinilo con el nombre \"" + entity.getName() + "\"");
        }
        if(persistence.find(entity.getId())!=null){
            throw new BusinessLogicException("Ya existe un vinilo con el id\"" + entity.getId() + "\"");
        }
        // Invoca la persistencia para crear el vinilo
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Vinilo");
        return entity;
    }

    /**
     * 
     * Obtener todas las Viniloes existentes en la base de datos.
     *
     * @return una lista de Viniloes.
     */
    public List<ViniloEntity> getVinilos() {
        LOGGER.info("Inicia proceso de consultar todas las Viniloes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ViniloEntity> Vinilo = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Viniloes");
        return Vinilo;
    }
    public ViniloEntity getVinilo(long id)
    {
        ViniloEntity ent=persistence.find(id);
        return ent;
    }
    public ViniloEntity updateVinilo(long id,ViniloEntity us)
    {
        persistence.update(us);
        return us;
    }
    public void deleteVinilo(long id)
    {
        persistence.delete(id);
    }
    
    public void agregarVinilo(ProveedorEntity provEnt, ViniloEntity vinEnt) throws BusinessLogicException
    {
        vinEnt.setProveedor(provEnt);
        createVinilo(vinEnt);
    }
    
    public void eliminarVinilo ( Long vinEnt)
    {
        ViniloEntity vin = persistence.find(vinEnt);
        vin.setProveedor(null);
        persistence.delete(vinEnt);
    }
    
    public ViniloEntity modificarVinilo (ProveedorEntity provEnt, Long id, ViniloEntity vinEnt)
    {
        vinEnt.setProveedor(provEnt);
        return updateVinilo(id, vinEnt);
    }
    public ViniloEntity agregarViniloCarro(UsuarioEntity usuario,ViniloEntity vinilo) throws BusinessLogicException
    {
       
        return createVinilo(vinilo);
    }
    public ViniloEntity addCarrito(CarroComprasEntity carrito,ViniloEntity vinilo)
    {
        vinilo.setCarrosCompras(carrito);
        return updateVinilo(vinilo.getId(), vinilo);
    }
    public void sacraDelCarrito(ViniloEntity vinilo)
    {
        vinilo.setCarrosCompras(null);
        deleteVinilo(vinilo.getId());
    }
    
    
    
}

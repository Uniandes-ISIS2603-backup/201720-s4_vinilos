package co.edu.uniandes.csw.tiendaVinilos.ejb;


import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.ViniloPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
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

    @Inject
    private CancionLogic logica;
    
    @Inject
    private ArtistaLogic logicaartista;
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public ViniloEntity createVinilo(ViniloEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación del Vinilo");
//        if (persistence.findByName(entity.getName()) != null) {
//            throw new BusinessLogicException("Ya existe un vinilo con el nombre \"" + entity.getName() + "\"");
//        }
//        if(persistence.find(entity.getId())!=null){
//            throw new BusinessLogicException("Ya existe un vinilo con el id\"" + entity.getId() + "\"");
//        }
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
        List<CarroComprasEntity> lista= vinilo.getCarrosCompras();
        lista.add(carrito);
        vinilo.setCarrosCompras(lista);
        return updateVinilo(vinilo.getId(), vinilo);
    }
    public void sacraDelCarrito(ViniloEntity vinilo,CarroComprasEntity carro)
    {
        List<CarroComprasEntity> carros= vinilo.getCarrosCompras();
        for (int i = 0; i < carros.size(); i++) {
            System.out.println(carros.get(i).getId());
            if(carros.get(i).getId()==carro.getId())
            {
                carros.remove(i);
            }
        }
        vinilo.setCarrosCompras(carros);
        updateVinilo(vinilo.getId(),vinilo);
    }
    
    
    public List<CancionEntity> getCanciones(Long id)
    {
        ViniloEntity ent = persistence.find(id);
        return ent.getCanciones();
    }
    
    public void addVinilo(Long id, CancionEntity canEnt)
    {
        ViniloEntity ent = persistence.find(id);
        ent.getCanciones().add(canEnt);
        persistence.update(ent);
    }
    
    /**
     * Obtiene una colección de instancias de ArtistaEntity asociadas a una
     * instancia de Vinilo
     *
     * @param viniloId Identificador de la instancia de Vinilo
     * @return Colección de instancias de ArtistaEntity asociadas a la instancia
     * de Vinilo
     * 
     */
    public List<ArtistaEntity> listArtistas(Long viniloId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", viniloId);
        return getVinilo(viniloId).getArtistas();
    }

    /**
     * Obtiene una instancia de ArtistaEntity asociada a una instancia de Vinilo
     *
     * @param viniloId Identificador de la instancia de Vinilo
     * @param artistasId Identificador de la instancia de Artista
     * 
     */
    public ArtistaEntity getArtista(Long viniloId, Long artistasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", viniloId);
        List<ArtistaEntity> list = getVinilo(viniloId).getArtistas();
        ArtistaEntity artistasEntity = new ArtistaEntity();
        artistasEntity.setId(artistasId);
        int index = list.indexOf(artistasEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Artista existente a un Vinilo
     *
     * @param viniloId Identificador de la instancia de Vinilo
     * @param artistasId Identificador de la instancia de Artista
     * @return Instancia de ArtistaEntity que fue asociada a Vinilo
     * 
     */
    public ArtistaEntity addArtista(Long viniloId, ArtistaEntity artista, Long idArtistaExistente) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un autor al libro con id = {0}", viniloId);
//        ViniloEntity viniloEntity = getVinilo(viniloId);
//        ArtistaEntity artistasEntity = new ArtistaEntity();
//        artistasEntity.setId(artistasId);
//        viniloEntity.getArtistas().add(artistasEntity);
//        return getArtista(viniloId, artistasId);
        ViniloEntity vinilo = getVinilo(viniloId);
        List artistas = vinilo.getArtistas();
        artistas.add(artista);
        vinilo.setArtistas(artistas);
        updateVinilo(viniloId, vinilo);
        
        
        if(idArtistaExistente!=0)
        {
            artista =logicaartista.updateArtista(idArtistaExistente, artista);
        }
        else
        {
            try {    
                artista=logicaartista.createArtista(artista);
            } catch (BusinessLogicException ex) {
                Logger.getLogger(ViniloLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        artista.setVinilo(vinilo);
        logicaartista.updateArtista(artista.getId(), artista);
            
        
        
    
        return artista;
    }

    /**
     * Remplaza las instancias de Artista asociadas a una instancia de Vinilo
     *
     * @param viniloId Identificador de la instancia de Vinilo
     * @param list Colección de instancias de ArtistaEntity a asociar a instancia
     * de Vinilo
     * @return Nueva colección de ArtistaEntity asociada a la instancia de Vinilo
     * 
     */
    public List<ArtistaEntity> replaceArtistas(Long viniloId, List<ArtistaEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un autor del libro con id = {0}", viniloId);
        ViniloEntity viniloEntity = getVinilo(viniloId);
        viniloEntity.setArtistas(list);
        return viniloEntity.getArtistas();
    }

    /**
     * Desasocia un Artista existente de un Vinilo existente
     *
     * @param viniloId Identificador de la instancia de Vinilo
     * @param artistasId Identificador de la instancia de Artista
     * 
     */
    public void removeArtista(Long viniloId, Long artistasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor del libro con id = {0}", viniloId);
        ViniloEntity entity = getVinilo(viniloId);
        ArtistaEntity artistasEntity = new ArtistaEntity();
        artistasEntity.setId(artistasId);
        entity.getArtistas().remove(artistasEntity);
    }

    /**
     * Obtiene una colección de instancias de CancionEntity asociadas a una
     * instancia de Vinilo
     *
     * @param viniloId Identificador de la instancia de Vinilo
     * @return Colección de instancias de CancionEntity asociadas a la instancia
     * de Vinilo
     * 
     */
    public List<CancionEntity> listCanciones(Long viniloId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", viniloId);
        return getVinilo(viniloId).getCanciones();
    }

    /**
     * Obtiene una instancia de CancionEntity asociada a una instancia de Vinilo
     *
     * @param viniloId Identificador de la instancia de Vinilo
     * @param cancionsId Identificador de la instancia de Cancion
     * 
     */
    public CancionEntity getCancion(Long viniloId, Long cancionsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", viniloId);
        List<CancionEntity> list = getVinilo(viniloId).getCanciones();
        CancionEntity cancionsEntity = new CancionEntity();
        cancionsEntity.setId(cancionsId);
        int index = list.indexOf(cancionsEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Cancion existente a un Vinilo
     *
     * @param viniloId Identificador de la instancia de Vinilo
     * @param cancionsId Identificador de la instancia de Cancion
     * @return Instancia de CancionEntity que fue asociada a Vinilo
     * 
     */
    public CancionEntity addCancion(Long viniloId, CancionEntity cancion,Long idCancionExistente) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un autor al libro con id = {0}", viniloId);
//        ViniloEntity viniloEntity = getVinilo(viniloId);
//        CancionEntity cancionsEntity = new CancionEntity();
//        cancionsEntity.setId(cancionsId);
//        viniloEntity.getCanciones().add(cancionsEntity);
//        return getCancion(viniloId, cancionsId);
    ViniloEntity vinilo=getVinilo(viniloId);
    List canciones=vinilo.getCanciones();
    canciones.add(cancion);
        vinilo.setCanciones(canciones);
        updateVinilo(viniloId, vinilo);
        
        
        cancion.setVinilos(vinilo);
        
        if(idCancionExistente!=0)
        {
            cancion =logica.updateCancion(idCancionExistente, cancion);
        }
        else
        {
            try {    
                logica.createCancion(cancion);
            } catch (BusinessLogicException ex) {
                Logger.getLogger(ViniloLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        logica.addVinilo(vinilo, cancion);
        return cancion;
    
    }

    /**
     * Remplaza las instancias de Cancion asociadas a una instancia de Vinilo
     *
     * @param viniloId Identificador de la instancia de Vinilo
     * @param list Colección de instancias de CancionEntity a asociar a instancia
     * de Vinilo
     * @return Nueva colección de CancionEntity asociada a la instancia de Vinilo
     * 
     */
    public List<CancionEntity> replaceCanciones(Long viniloId, List<CancionEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un autor del libro con id = {0}", viniloId);
        ViniloEntity viniloEntity = getVinilo(viniloId);
        viniloEntity.setCanciones(list);
        return viniloEntity.getCanciones();
    }

    /**
     * Desasocia un Cancion existente de un Vinilo existente
     *
     * @param viniloId Identificador de la instancia de Vinilo
     * @param cancionsId Identificador de la instancia de Cancion
     * 
     */
    public void removeCancion(Long viniloId, Long cancionsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor del libro con id = {0}", viniloId);
        ViniloEntity entity = getVinilo(viniloId);
        CancionEntity cancionsEntity = new CancionEntity();
        cancionsEntity.setId(cancionsId);
        entity.getCanciones().remove(cancionsEntity);
    }

}
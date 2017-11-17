/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jp.monsalvo
 */
public class ViniloDetailDTO extends ViniloDTO{
    
    private CarroComprasDTO carros;
    private ArrayList<CancionDTO> canciones;
    
    public ArrayList<CancionDTO> getCanciones()
    {
        return canciones;
    }
    
    public void setCanciones(ArrayList<CancionDTO> canciones)
    {
        this.canciones = canciones;
    }
    
    public CarroComprasDTO getcarros()
    {
        return carros;
    }
    
    public void setCarros(CarroComprasDTO carros)
    {
        this.carros = carros;
    }
    
    private UsuarioDTO usuario;
    
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Constructor por defecto
     */
    public ViniloDetailDTO() {
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ViniloDetailDTO(ViniloEntity entity) {
        super(entity);
        if (entity.getCarrosCompras() != null)
        {
            carros = new CarroComprasDetailDTO(entity.getCarrosCompras());
        }
        else carros = null;
        
        if(entity != null)
        {
            canciones = new ArrayList<>();
            for(CancionEntity canEnt : entity.getCanciones()){
                canciones.add(new CancionDTO(canEnt));
            }
        }
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public ViniloEntity toEntity() {
        ViniloEntity entity = super.toEntity();
        
        if(entity != null){
            CarroComprasEntity ent = null;
            if (entity.getCarrosCompras() != null){
                ent = entity.getCarrosCompras();
            }
            entity.setCarrosCompras(ent);
            
            List<CancionEntity> listCan = null;
            if(getCanciones() != null)
            {
                listCan = new ArrayList<>();
                for(CancionDTO canDto : getCanciones())
                {
                    listCan.add(canDto.toEntity());
                }
            }
            entity.setCanciones(listCan);
        }
        return entity;
    }
}

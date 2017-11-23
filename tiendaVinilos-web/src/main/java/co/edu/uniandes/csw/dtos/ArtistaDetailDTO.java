/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cs.gomez
 */
public class ArtistaDetailDTO extends ArtistaDTO{
    
    private List<CancionDTO> canciones;
    
    public List<CancionDTO> getCanciones()
    {
        return canciones;
    }
    
    public void setCanciones(List<CancionDTO> canciones)
    {
        this.canciones = canciones;
    }
    
    //Constructor por defecto
    public ArtistaDetailDTO(){
        //Constructor por defecto
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     * @param entity
     */
    public ArtistaDetailDTO(ArtistaEntity entity) {
        super(entity);
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
     * @return 
     */
    @Override
    public ArtistaEntity toEntity() {
        ArtistaEntity entity = super.toEntity();
        
        if(entity != null)
        {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;

/**
 *
 * @author cs.gomez
 */
public class CancionDetailDTO extends CancionDTO{
    
    
    private ArtistaDTO artista;
    
    public ArtistaDTO getArtista(){
        return artista;
    }
    
    public void setArtista(ArtistaDTO artista){
        this.artista = artista;
    }
    
    public CancionDetailDTO(){
        
    }
    
    public CancionDetailDTO(CancionEntity entity){
        super(entity);
        if(entity.getArtistas() != null){
            artista = new ArtistaDetailDTO(entity.getArtistas());
        }else{
            artista = null;
        }
    }
    
    @Override
    public CancionEntity toEntity() {
        CancionEntity entity = super.toEntity();
        
        if(entity != null){
            ArtistaEntity ent = null;
            if(entity.getArtistas() != null){
                ent = entity.getArtistas();
            }
            entity.setArtistas(ent);
        }
        return entity;
    }
}

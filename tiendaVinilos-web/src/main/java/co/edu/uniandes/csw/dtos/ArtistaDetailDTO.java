/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;

/**
 *
 * @author cs.gomez
 */
public class ArtistaDetailDTO extends ArtistaDTO{
    
    public ArtistaDetailDTO(){
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ArtistaDetailDTO(ArtistaEntity entity) {
        super(entity);
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public ArtistaEntity toEntity() {
        ArtistaEntity infoE = super.toEntity();
        return infoE;
    }
}

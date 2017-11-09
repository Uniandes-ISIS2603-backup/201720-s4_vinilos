/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;

/**
 *
 * @author cs.gomez
 */
public class CancionDetailDTO extends CancionDTO{
    
    public CancionDetailDTO(){
        
    }
    
    public CancionDetailDTO(CancionEntity entity){
        super(entity);
    }
    
    @Override
    public CancionEntity toEntity() {
        CancionEntity entity = super.toEntity();
        return entity;
    }
}

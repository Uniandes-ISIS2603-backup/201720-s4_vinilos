/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;

/**
 *
 * @author cs.gomez
 */
public class CarroComprasDetailDTO extends CarroComprasDTO{
    
    public CarroComprasDetailDTO(){
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public CarroComprasDetailDTO(CarroComprasEntity entity) {
        super(entity);
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public CarroComprasEntity toEntity() {
        CarroComprasEntity infoE = super.toEntity();
        return infoE;
    }
    
}

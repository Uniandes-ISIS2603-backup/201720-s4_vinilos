/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;

/**
 *
 * @author s.saenz11
 */
public class PagoProveedorDetailDTO extends PagoProveedorDTO {
    
    
    public PagoProveedorDetailDTO(){
        
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public PagoProveedorDetailDTO(PagoProveedorEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    public PagoProveedorEntity toEntity() {
        PagoProveedorEntity UsuarioE = super.toEntity();
        return UsuarioE;
    }

    
}

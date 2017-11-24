
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;

/**
 *
 * @author s.saenz11
 */
public class PagoProveedorDetailDTO extends PagoProveedorDTO {

    // Constructor por defecto
    public PagoProveedorDetailDTO() {

        // Constructor por defecto
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
    @Override
    public PagoProveedorEntity toEntity() {
        PagoProveedorEntity UsuarioE = super.toEntity();

        return UsuarioE;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.InfoEntity;

/**
 *
 * @author jp.monsalvo
 */
public class InfoDetailDTO extends InfoDTO {

    /**
     * Constructor por defecto
     */
    public InfoDetailDTO() {
        //Constructor por defecto
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public InfoDetailDTO(InfoEntity entity) {
        super(entity);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

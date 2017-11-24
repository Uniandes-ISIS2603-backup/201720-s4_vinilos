
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;

/**
 *
 * @author jc.ruiz
 */
public class FeedBackDetailDTO extends FeedBackDTO {
    private ProveedorDTO proveedor;

    // Constructor por defecto
    public FeedBackDetailDTO() {

        // Constructor por defecto
    }

    /**
     * Constructor del feedback a partir de un entity
     * @param entity
     */
    public FeedBackDetailDTO(FeedBackEntity entity) {
        super(entity);

        if (entity.getProveedor() != null) {
            proveedor = new ProveedorDTO(entity.getProveedor());
        } else {
            proveedor = null;
        }
    }

    /**
     * Set del proveedor asociado al feedback
     * @param proveedor DTO del proveedor por asociar al feedback
     */
    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Get del proveedor asociado al feedback
     * @return  el proveedor asociado al feedback
     */
    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    @Override
    public FeedBackEntity toEntity() {
        FeedBackEntity entity = super.toEntity();

        if (entity != null) {
            ProveedorEntity ent = null;

            if (entity.getProveedor() != null) {
                ent = entity.getProveedor();
            }

            entity.setProveedor(ent);
        }

        return entity;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;

/**
 *
 * @author jc.ruiz
 */
public class FeedBackDetailDTO extends FeedBackDTO{
            
    ProveedorDTO proveedor;
    
    public FeedBackDetailDTO(){
        super();
    }
    
    public FeedBackDetailDTO(FeedBackEntity ent){
        super(ent);
        if (ent != null)
        {
            this.proveedor = new ProveedorDTO(ent.getProveedor());
        }
    }
}


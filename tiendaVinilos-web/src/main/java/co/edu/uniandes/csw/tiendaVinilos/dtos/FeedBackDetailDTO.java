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
    
    /**
     * Set del proveedor asociado al feedback
     * @param proveedor DTO del proveedor por asociar al feedback
     */
    public void setProveedor(ProveedorDTO proveedor)
    {
        this.proveedor = proveedor;
    }      
    
    /**
     * Get del proveedor asociado al feedback
     * @return  el proveedor asociado al feedback
     */
    public ProveedorDTO getProveedor ()
    {
        return proveedor;
    }
    
    /**
     * Constructor por defecto
     */
    public FeedBackDetailDTO(){
        super();
    }
    
    /**
     * Constructor del feedback a partir de un entity
     * @param ent 
     */
    public FeedBackDetailDTO(FeedBackEntity ent){
        super(ent);
        /*if (ent != null)
        {
            this.proveedor = new ProveedorDTO(ent.getProveedor());
        }*/
    }
}


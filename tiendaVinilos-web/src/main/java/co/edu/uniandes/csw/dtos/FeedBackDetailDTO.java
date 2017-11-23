/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;

/**
 *
 * @author jc.ruiz
 */
public class FeedBackDetailDTO extends FeedBackDTO{
            
    private ProveedorDTO proveedor;
    
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
    
    //Constructor por defecto
    public FeedBackDetailDTO(){
        //Constructor por defecto
    }
    
    @Override
    public FeedBackEntity toEntity()
    {
        FeedBackEntity entity = super.toEntity();
        if (entity != null){
            ProveedorEntity ent = null;
            if (entity.getProveedor() != null)
            {
                ent = entity.getProveedor();
            }
            entity.setProveedor(ent);
        }
        return entity;
    }
    
    /**
     * Constructor del feedback a partir de un entity
     * @param entity 
     */
    public FeedBackDetailDTO(FeedBackEntity entity){
        super(entity);
        if (entity.getProveedor() != null)
        {
            proveedor = new ProveedorDTO(entity.getProveedor());
        }
        else proveedor = null;
    }
}


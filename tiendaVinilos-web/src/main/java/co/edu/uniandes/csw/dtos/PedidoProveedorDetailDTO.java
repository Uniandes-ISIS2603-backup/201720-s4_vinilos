/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;

/**
 *
 * @author s.saenz11
 */
public class PedidoProveedorDetailDTO extends PedidoProveedorDTO{
    
    
     public PedidoProveedorDetailDTO(){
         
     }
   
    /**
     * Constructor por defecto
   
     * @param entity
     */
    public PedidoProveedorDetailDTO(PedidoProveedorEntity entity) {
        super(entity);
    }

    

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    public PedidoProveedorEntity toEntity() {
        PedidoProveedorEntity UsuarioE = super.toEntity();
        return UsuarioE;
    }

    
    
}

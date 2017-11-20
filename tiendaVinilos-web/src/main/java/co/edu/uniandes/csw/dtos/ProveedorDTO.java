/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;

/**
 *
 * @author jc.ruiz
 */
public class ProveedorDTO {
    
    Long id;
    String name; 
    String email;
    
    //Constructor por defecto
    public ProveedorDTO() {
        //Constructor por defecto
    }
    
    public Long getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public void setName (String name)
    {
        this.name = name;
    }
    public void setEmail (String email)
    {
        this.email = email;
    }
    
    public ProveedorDTO (ProveedorEntity entity)
    {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
    }
    
    public ProveedorEntity toEntity ()
    {
        ProveedorEntity entity = new ProveedorEntity();
        entity.setEmail(this.email);
        entity.setName(this.name);
        entity.setId(getId());
        
        return entity;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;

/**
 *
 * @author cs.gomez
 */
public class ArtistaDTO {
    
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ArtistaDTO(){
    }
    
    public ArtistaDTO(ArtistaEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
    
    public ArtistaEntity toEntity(){
        ArtistaEntity entity = new ArtistaEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        
        return entity;
    }
}

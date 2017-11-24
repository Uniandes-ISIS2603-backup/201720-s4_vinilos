/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;

/**
 *
 * @author cs.gomez
 */
public class CancionDTO {
    
    private Long id;
    private String name;
    private Double duracion;
    private String link;
    
    public CancionDTO(){
    }
    
    public CancionDTO(CancionEntity cancion){
        this.id = cancion.getId();
        this.name = cancion.getName();
        this.duracion = cancion.getDuracion();
        this.link = cancion.getLink();
    }
    
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
    
    public Double getDuracion(){
        return duracion;
    }
   
    public void setDuracion(Double d){
        this.duracion = d;
    }
    
    public String getLink(){
        return link;
    }
    
    public void setLink(String link){
        this.link = link;
    }
    
    
    public CancionEntity toEntity(){
        CancionEntity entity = new CancionEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setDuracion(this.duracion);
        entity.setLink(this.link);
        
        return entity;
    }
    
}

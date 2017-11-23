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
    
    //Constructor por defecto
    public CancionDTO(){
        //Constructor por defecto
    }
    
    public CancionDTO(CancionEntity cancion){
        this.id = cancion.getId();
        this.name = cancion.getName();
        this.duracion = cancion.getDuracion();
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
    
    
    public CancionEntity toEntity(){
        CancionEntity entity = new CancionEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setDuracion(this.duracion);
        
        return entity;
    }
    
}

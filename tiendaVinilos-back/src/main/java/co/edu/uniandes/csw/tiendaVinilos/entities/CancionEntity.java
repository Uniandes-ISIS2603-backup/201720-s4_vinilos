/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import javax.persistence.Entity;

/**
 *
 * @author cs.gomez
 */
@Entity
public class CancionEntity extends BaseEntity{
    
    private Double duracion; 
    
    public Double getDuracion(){
        return duracion;
    }
   
    
    public void setDuracion(Double d){
        this.duracion = d;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jc.ruiz
 */
@Entity
public class FeedBackEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String comentario;
    double calificacion;
    
    public long getId()
    {
        return id;
    }
    
    public String getComentario()
    {        
            return comentario;
    }
    
    public double getCalificacion()
    {
        return calificacion;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public void setComentario(String comentario)
    {
        this.comentario = comentario;
    }
    
    public void setCalificacion(double calificacion)
    {
        this.calificacion=calificacion;
    }
    
}

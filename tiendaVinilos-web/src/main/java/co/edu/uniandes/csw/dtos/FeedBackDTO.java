/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import java.util.logging.Logger;

/**
 *
 * @author jc.ruiz
 */
public class FeedBackDTO {
    
     private static final Logger LOGGER = Logger.getLogger(FeedBackDTO.class.getName());
     
    private long id;
    private String comentario;
    private double calificacion;
    
     public FeedBackDTO(){
    }   
     
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
    
    public FeedBackDTO(FeedBackEntity entity){
        id = entity.getId();
        System.out.println("EL ID DEL DTO ES ------------------------------- " + id);
        calificacion = entity.getCalificacion();
        comentario = entity.getComentario();
    }
    
    public FeedBackEntity toEntity (){
       
        FeedBackEntity ent = new FeedBackEntity();
        ent.setCalificacion(this.getCalificacion());
        ent.setComentario(this.getComentario());
        ent.setId(this.getId());
        return ent;
    }
}

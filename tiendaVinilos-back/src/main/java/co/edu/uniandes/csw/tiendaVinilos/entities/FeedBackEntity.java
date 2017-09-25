/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.ruiz
 */
@Entity
public class FeedBackEntity extends BaseEntity implements Serializable{
    
    
    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;
    private String comentario;
    private double calificacion;
    
    public ProveedorEntity getProveedor(){
        return proveedor;
    }
    
    public void setProveedor(ProveedorEntity prov)
    {
        this.proveedor = prov;
    }
    public String getComentario()
    {        
            return comentario;
    }
    
    public double getCalificacion()
    {
        return calificacion;
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

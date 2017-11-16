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
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.ruiz
 */
@Entity
public class FeedBackEntity implements Serializable{
    
    
    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    private String comentario;
    private double calificacion;
    
    /**
     * Get del usuario que efectuo el feedback
     * @return el usuario
     */
    public UsuarioEntity getUsuario()
    {
        return usuario;
    }
    
    /**
     * Set del usuario que efectuo el feedback
     * @param usuario el entity del usuario que efectuo el feedback
     */
    public void setUsuario(UsuarioEntity usuario)
    {
        this.usuario = usuario;
    }
    
    /**
     * Get del proveedor asociado al feedback
     * @return proveedor asociado
     */
    public ProveedorEntity getProveedor(){
        return proveedor;
    }
    
    /**
     * Set del proveedor asociado al feedback 
     * @param prov el entity del proveedor asociado
     */
    public void setProveedor(ProveedorEntity prov)
    {
        this.proveedor = prov;
    }
    
    /**
     * Get del comentario del feedback
     * @return el comentario asociado al feedback
     */
    public String getComentario()
    {        
            return comentario;
    }
    
    /**
     * La calificacion del feedback
     * @return la calificaciona sociada al feedback
     */
    public double getCalificacion()
    {
        return calificacion;
    }  
    
    /**
     * Set del comentario asociado al feedback
     * @param comentario el comentario que se desea asociar al feedback
     */
    public void setComentario(String comentario)
    {
        this.comentario = comentario;
    }
    
    /**
     * Set de la calificacion asociada al feedback
     * @param calificacion la calificacion que se desea asociar al feedback
     */
    public void setCalificacion(double calificacion)
    {
        this.calificacion=calificacion;
    }
    
}

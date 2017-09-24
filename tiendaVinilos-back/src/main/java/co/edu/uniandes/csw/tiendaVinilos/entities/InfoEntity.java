/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;

/**
 *
 * @author jp.monsalvo
 */
@Entity
public class InfoEntity extends BaseEntity implements Serializable{

    
    private String descripcion;
    private String urlCancion;
    private String urlImagen;

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the urlCancion
     */
    public String getUrlCancion() {
        return urlCancion;
    }

    /**
     * @param urlCancion the urlCancion to set
     */
    public void setUrlCancion(String urlCancion) {
        this.urlCancion = urlCancion;
    }

    /**
     * @return the urlImagen
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * @param urlImagen the urlImagen to set
     */
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
    
}

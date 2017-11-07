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
 * @author jp.monsalvo
 */
@Entity
public class InfoEntity implements Serializable {

       
    private String descripcion;
    private String urlCancion;
    private String urlImagen;

    private ViniloEntity vinilo;
    
    /**
     * @return the vinilo
     */
    public ViniloEntity getVinilo() {
        return vinilo;
    }

    /**
     * @param vinilo the vinilo to set
     */
    public void setVinilo(ViniloEntity vinilo) {
        this.vinilo = vinilo;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
     private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
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

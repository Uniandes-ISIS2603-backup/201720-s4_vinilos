/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;

/**
 *
 * @author jp.monsalvo
 */
public class InfoDTO {

    
    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    private String urlImagen;
    private String urlCancion;
    private String descripcion;
    private long id;
    
    /**
     * Constructor por defecto
     */
    public InfoDTO(){
    
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
     * @return the id
     */
    public long getId() {
        return id;
    }
   
}

package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author jp.monsalvo
 */
@Entity
public class InfoEntity implements Serializable {

    private String descripcion;
    private String urlCancion;
    private String urlImagen;

    @ManyToOne
    private ViniloEntity vinilo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private String name;
    private Long id;

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
}
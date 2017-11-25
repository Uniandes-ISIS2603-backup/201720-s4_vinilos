
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

//~--- non-JDK imports --------------------------------------------------------

import uk.co.jemos.podam.common.PodamExclude;

//~--- JDK imports ------------------------------------------------------------

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author cs.gomez
 */
@Entity
public class ArtistaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                id;
    @PodamExclude
    @OneToMany(mappedBy = "artistas")
    private List<CancionEntity> canciones;
    @PodamExclude
    @ManyToOne
    private ViniloEntity        vinilo;
    private String              name;

    public List<CancionEntity> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<CancionEntity> canciones) {
        this.canciones = canciones;
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


//~ Formatted by Jindent --- http://www.jindent.com

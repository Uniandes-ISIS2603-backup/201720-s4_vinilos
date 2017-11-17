/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;

/**
 *
 * @author cs.gomez
 */
@Entity
public class CancionEntity implements Serializable{

        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToMany(mappedBy = "canciones")
    private List<ViniloEntity> vinilos = new ArrayList<ViniloEntity>();
    
    private Double duracion;
    
    private String name;
    
    @PodamExclude
    @ManyToOne
    private ArtistaEntity artistas;
    
    @PodamExclude
    @ManyToOne
    private ViniloEntity  vinilo;
    
    public ViniloEntity getVinilos(){
        return vinilo;
    }
    
    public void setVinilos(ViniloEntity vin){
        this.vinilo = vin;
    }
    
    public ArtistaEntity getArtistas(){
        return artistas;
    }
    
    public void setArtistas (ArtistaEntity a){
        this.artistas = a;
    }
    
    public Double getDuracion(){
        return duracion;
    }
   
    
    public void setDuracion(Double d){
        this.duracion = d;
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
     * @return the vinilos
     */
    public List<ViniloEntity> getVinilos() {
        return vinilos;
    }

    /**
     * @param vinilos the vinilos to set
     */
    public void setVinilos(List<ViniloEntity> vinilos) {
        this.vinilos = vinilos;
    }
}

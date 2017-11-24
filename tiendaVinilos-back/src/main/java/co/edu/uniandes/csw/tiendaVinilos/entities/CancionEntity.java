/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
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
    
    private Double duracion;
    
    private String name;
    
    private String link;
    
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
    
    public String getLink(){
        return link;
    }
    
    public void setLink(String link){
        this.link = link;
    }
}

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
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author cs.gomez
 */
@Entity
public class CarroComprasEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private double precioTotal;
    @PodamExclude
    @ManyToMany(mappedBy="carrosCompras")
    private List<ViniloEntity> vinilos;
    
    public List<ViniloEntity> getVinilos()
    {
        return vinilos;
    }
    
    public void setVinilos(List<ViniloEntity> vinilos)
    {
        this.vinilos = vinilos;
    }
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public double getPrecioTotal(){
        return precioTotal;
    }
    
    public void setPrecioTotal(double precioTotal){
        this.precioTotal = precioTotal;
    }
    
    
}

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
 * @author jp.monsalvo
 */
@Entity
public class ViniloEntity extends BaseEntity {
    
    private int anio;
    private double precio;
    private int cantUnidades;

    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;

    public ProveedorEntity getProveedor()
    {
        return proveedor;
    }
    
    public void setProveedor (ProveedorEntity proveedor)
    {
        this.proveedor = proveedor;
    }
    
    /**
     * @return the año
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param año the año to set
     */
    public void setAnio(int año) {
        this.anio = año;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the cantUnidades
     */
    public int getCantUnidades() {
        return cantUnidades;
    }

    /**
     * @param cantUnidades the cantUnidades to set
     */
    public void setCantUnidades(int cantUnidades) {
        this.cantUnidades = cantUnidades;
    }
    
}

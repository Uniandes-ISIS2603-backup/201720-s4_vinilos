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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.monsalvo
 */
@Entity
public class ViniloEntity extends BaseEntity implements Serializable{
    
    private int anio;
    private double precio;
    private int cantUnidades;
    
    @OneToOne
    @PodamExclude
    private InfoEntity info;
    
    
    @PodamExclude
    @ManyToMany(mappedBy = "vinilo")
    private List<ArtistaEntity> artista= new ArrayList<ArtistaEntity>();
    
    @OneToMany
    @PodamExclude
    private CancionEntity cancion;
    
    @ManyToOne
    @PodamExclude
    private List<CarroComprasEntity> carroCompras= new ArrayList<CarroComprasEntity>();
    
    @PodamExclude
    @ManyToOne
    private List<ProveedorEntity> proveedor= new ArrayList<ProveedorEntity>();
    
    @PodamExclude
    @ManyToOne
    private List<PedidoProveedorEntity> pedidoProveedor= new ArrayList<PedidoProveedorEntity>();

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

    /**
     * @return the info
     */
    public InfoEntity getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(InfoEntity info) {
        this.info = info;
    }
    
}

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.monsalvo
 */
@Entity
public class ViniloEntity implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PodamExclude
    @OneToOne
   private InfoEntity info;
    
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
    
    @PodamExclude
    @ManyToOne
     private CarroComprasEntity carrosCompras;
    
    public CarroComprasEntity getCarrosCompras()
    {
        return carrosCompras;
    }
    
    public void setCarrosCompras(CarroComprasEntity carrosCompras)
    {
        this.carrosCompras = carrosCompras;
    }
            
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
    
    
    private int anio;
    private double precio;
    private int cantUnidades;

    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;
    
    @PodamExclude
    @ManyToOne
    private PedidoProveedorEntity pedidoProveedor;
    

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

    public PedidoProveedorEntity getPedidoProveedor() {
        return pedidoProveedor;
    }

    public void setPedidoProveedor(PedidoProveedorEntity pedidoProveedor) {
        this.pedidoProveedor = pedidoProveedor;
    }

  
    
}

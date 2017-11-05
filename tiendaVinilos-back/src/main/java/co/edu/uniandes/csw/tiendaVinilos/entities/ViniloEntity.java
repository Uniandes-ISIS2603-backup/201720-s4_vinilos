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

    /**
     * Id del vinilo
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * relacion con carroCompras
     */
    @PodamExclude
    @ManyToOne
     private CarroComprasEntity carrosCompras;
    
    /**
     * obtener la relacion de CarroCompras
     * @return 
     */
    public CarroComprasEntity getCarrosCompras()
    {
        return carrosCompras;
    }
    
    /**
     * Cambiar la relacion de carroCompras
     * @param carrosCompras 
     */
    public void setCarrosCompras(CarroComprasEntity carrosCompras)
    {
        this.carrosCompras = carrosCompras;
    }
        
    /**
     * Relacion con Info
     */
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
    /**
     * Retorna el id del vinilo
     * @return 
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el id del vinilo por el que entra por parametro
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * nombre del vinilo
     */
     private String name;

     /**
      * obtener el nombre del vinilo
      * @return 
      */
    public String getName() {
        return name;
    }
/**
 * cambiar el nombre del vinilo
 * @param name 
 */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * anio de publicacion del vinilo
     */
    private int anio;
    /**
     * precio del vinilo
     */
    private double precio;
    /**
     * cantidad de unidades del vinilo
     */
    private int cantUnidades;

    @PodamExclude
    @ManyToOne
    /**
     * relacion con Proveedor
     */
    private ProveedorEntity proveedor;
    
    @PodamExclude
    @ManyToOne
    /**
     * Relacion con pedidoProveedor
     */
    private PedidoProveedorEntity pedidoProveedor;
    
/**
 * obtener la relacion con el proveedor
 * @return 
 */
    public ProveedorEntity getProveedor()
    {
        return proveedor;
    }
    
    /**
     * Cambiar la relación con proveedor
     * @param proveedor 
     */
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

    /**
     * Obtener la relacion con PedidoProveedor
     * @return 
     */
    public PedidoProveedorEntity getPedidoProveedor() {
        return pedidoProveedor;
    }

    /**
     * Cambiar la relacion con PedidoProveedor
     * @param pedidoProveedor 
     */
    public void setPedidoProveedor(PedidoProveedorEntity pedidoProveedor) {
        this.pedidoProveedor = pedidoProveedor;
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    private int anio;
    private double precio;
    private int cantUnidades;
    private String nombre;

    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

    @PodamExclude
    @OneToMany(mappedBy = "vinilo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InfoEntity> infos = new ArrayList<InfoEntity>();

    @OneToMany(mappedBy = "vinilo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CancionEntity> canciones = new ArrayList<CancionEntity>();

    @OneToMany(mappedBy = "vinilo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArtistaEntity> artistas = new ArrayList<ArtistaEntity>();

    @PodamExclude
    @ManyToOne
    private CarroComprasEntity carrosCompras;

    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;

    @PodamExclude
    @ManyToOne
    private PedidoProveedorEntity pedidoProveedor;

    public void setCarrosCompras(CarroComprasEntity carrosCompras) {
        this.carrosCompras = carrosCompras;
    }

    public CarroComprasEntity getCarrosCompras() {
        return carrosCompras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorEntity proveedor) {
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

    /**
     * @return the infos
     */
    public List<InfoEntity> getInfos() {
        return infos;
    }

    /**
     * @param infos the infos to set
     */
    public void setInfos(List<InfoEntity> infos) {
        this.infos = infos;
    }

    /**
     * @return the canciones
     */
    public List<CancionEntity> getCanciones() {
        return canciones;
    }

    /**
     * @param canciones the canciones to set
     */
    public void setCanciones(List<CancionEntity> canciones) {
        this.canciones = canciones;
    }

    /**
     * @return the artistas
     */
    public List<ArtistaEntity> getArtistas() {
        return artistas;
    }

    /**
     * @param artistas the artistas to set
     */
    public void setArtistas(List<ArtistaEntity> artistas) {
        this.artistas = artistas;
    }

    /**
     * @return the usuario
     */
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}

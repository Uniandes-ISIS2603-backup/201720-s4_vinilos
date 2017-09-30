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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jd.arenas
 */
@Entity
public class UsuarioEntity implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    
    private String eMail;
    private int cantCompras;
    @PodamExclude
    @OneToMany
    private List<TarjetaEntity> tarjetas;
    
    @PodamExclude
    @OneToMany(mappedBy="usuario")
    private List<FeedBackEntity> feedBacks;
    
    @PodamExclude
    @OneToMany(mappedBy="usuario")
    private List<PedidoClienteEntity> pedidos;
    
    @PodamExclude
    @OneToMany(mappedBy="usuario",cascade=CascadeType.ALL, orphanRemoval=true)
    private List<ViniloEntity> carroCompras;
    
    @PodamExclude
    @OneToOne(fetch=FetchType.LAZY,mappedBy="usuario")
    private CarroComprasEntity carrito;

    public CarroComprasEntity getCarrito() {
        return carrito;
    }

    public void setCarrito(CarroComprasEntity carrito) {
        this.carrito = carrito;
    }
    
    

    public List<ViniloEntity> getCarroCompras() {
        return carroCompras;
    }

    public void setCarroCompras(List<ViniloEntity> carroCompras) {
        this.carroCompras = carroCompras;
    }
    
    
    

    public List<PedidoClienteEntity> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoClienteEntity> pedidos) {
        this.pedidos = pedidos;
    }
    
    public List<TarjetaEntity> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(ArrayList<TarjetaEntity> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public List<FeedBackEntity> getFeedBacks() {
        return feedBacks;
    }

    public void setFeedBacks(ArrayList<FeedBackEntity> feedBacks) {
        this.feedBacks = feedBacks;
    }
    

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getCantCompras() {
        return cantCompras;
    }

    public void setCantCompras(int cantCompras) {
        this.cantCompras = cantCompras;
    }

    public void setTarjetas(List<TarjetaEntity> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public void setFeedBacks(List<FeedBackEntity> feedBacks) {
        this.feedBacks = feedBacks;
    }

    
}

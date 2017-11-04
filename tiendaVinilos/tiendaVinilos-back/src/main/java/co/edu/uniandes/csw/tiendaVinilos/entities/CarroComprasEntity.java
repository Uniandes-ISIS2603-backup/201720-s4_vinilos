/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
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
    
    private String name;
    
    @PodamExclude
    @OneToMany (mappedBy="carrosCompras")
    private List<ViniloEntity> vinilos;
//    @OneToMany(mappedBy="carrito")
//    private PedidoClienteEntity pedido; 
    
    @PodamExclude
    private UsuarioEntity usuario;

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

//    public PedidoClienteEntity getPedido() {
//        return pedido;
//    }
//
//    public void setPedido(PedidoClienteEntity pedido) {
//        this.pedido = pedido;
//    }
    
    
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}

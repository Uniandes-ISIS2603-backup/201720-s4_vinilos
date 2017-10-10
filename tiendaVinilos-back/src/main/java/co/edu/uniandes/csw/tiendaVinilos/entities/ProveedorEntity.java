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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.ruiz
 */
@Entity
public class ProveedorEntity implements Serializable{
    
 
    String email;
    
    @PodamExclude
    @OneToMany(mappedBy = "proveedor")
    private List<FeedBackEntity> feedBacks = new ArrayList();
    
    @PodamExclude
    @OneToMany (mappedBy = "proveedor")
    private List<PagoProveedorEntity> pagos = new ArrayList();
    
    @PodamExclude 
    @OneToMany(mappedBy = "proveedor")
    private List <ViniloEntity> vinilos = new ArrayList();
    
     @PodamExclude
    @OneToMany(mappedBy = "proveedor")
    private List<PedidoProveedorEntity> pedidos = new ArrayList();
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
     private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    
    
     /* Get de los pedidos del proveedor
     * @return lista con los pedidos del proveedor
     */
    public List<PedidoProveedorEntity> getPedidos()
    {
        return pedidos;
    }
    
    
     /* Set de los pedidos asociadios al proveedor
     * @param pedidos los pedidos por asociar 
     */
    public void setPedidos(List<PedidoProveedorEntity> pedidos)
                {
        this.pedidos = pedidos;
    }
    
    
    public List<ViniloEntity> getVinilos()
    {
        return vinilos;
    }
    
    public void setVinilos(List<ViniloEntity> vinilos)
    {
        this.vinilos = vinilos;
    }
    
    public List<PagoProveedorEntity> getPagos()
    {
        return pagos;
    }
    
    public void setPagos (List<PagoProveedorEntity> pagos)
    {
        this.pagos = pagos;
    }
    
    public List<FeedBackEntity> getFeedBacks()
    {
        return feedBacks;
    }
    
    public void setFeedBacks(List<FeedBackEntity> feedBacks)
    {
        this.feedBacks = feedBacks;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
}
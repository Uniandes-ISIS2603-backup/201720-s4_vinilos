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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.ruiz
 */
@Entity
public class ProveedorEntity extends BaseEntity implements Serializable {
    
 
    String email;
    
    @PodamExclude
    @OneToMany(mappedBy = "proveedor")
    private List<FeedBackEntity> feedBacks = new ArrayList();
    
    
    @PodamExclude
    @OneToMany(mappedBy = "proveedor")
    private List<PedidoProveedorEntity> pedidos = new ArrayList();
    
    
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
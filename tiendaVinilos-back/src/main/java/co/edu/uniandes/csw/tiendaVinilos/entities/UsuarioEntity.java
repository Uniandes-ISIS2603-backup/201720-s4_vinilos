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
 * @author jd.arenas
 */
@Entity
public class UsuarioEntity extends BaseEntity {
    
    
    private String eMail;
    private int cantCompras;
    @PodamExclude
    @OneToMany
    private List<TarjetaEntity> tarjetas;
    
    @PodamExclude
    @OneToMany(mappedBy="usuario")
    private List<FeedBackEntity> feedBacks;

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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc.ruiz
 */
public class ProveedorDetailDTO extends ProveedorDTO{
    
    private List<FeedBackDTO> feedBack;
    
    public ProveedorDetailDTO(){
    }
    
    public ProveedorDetailDTO(ProveedorEntity entity) {
        super(entity);
        if (entity != null)
        {
            feedBack = new ArrayList();
            for (FeedBackEntity fb : entity.getFeedBacks())
                feedBack.add(new FeedBackDTO(fb));
        }
    }
    
    
    
}

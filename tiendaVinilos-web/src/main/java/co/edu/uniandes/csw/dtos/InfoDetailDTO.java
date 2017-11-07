/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.InfoEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;

/**
 *
 * @author jp.monsalvo
 */
public class InfoDetailDTO extends InfoDTO{
   
    private ViniloDTO vinilo;
    
    /**
     * @return the vinilo
     */
    public ViniloDTO getVinilo() {
        return vinilo;
    }

    /**
     * @param vinilo the vinilo to set
     */
    public void setVinilo(ViniloDTO vinilo) {
        this.vinilo = vinilo;
    }
   
    
    /**
     * Constructor por defecto
     */
    public InfoDetailDTO() {
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public InfoDetailDTO(InfoEntity entity) {
        super(entity);
         if (entity.getVinilo()!= null)
        {
            vinilo = new ViniloDetailDTO(entity.getVinilo());
        }
        else vinilo = null;
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public InfoEntity toEntity() {
        InfoEntity infoE = super.toEntity();
        if(infoE != null){
            ViniloEntity ent = null;
           
            if (infoE.getVinilo() != null){
                ent = infoE.getVinilo();
            }
            
            infoE.setVinilo(ent);
        }
        return infoE;
    }
}

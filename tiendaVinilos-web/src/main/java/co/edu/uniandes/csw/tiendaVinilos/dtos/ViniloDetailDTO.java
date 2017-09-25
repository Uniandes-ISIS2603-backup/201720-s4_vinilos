/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;

/**
 *
 * @author jp.monsalvo
 */
public class ViniloDetailDTO extends ViniloDTO{

    /**
     * @return the info
     */
    public InfoDTO getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(InfoDTO info) {
        this.info = info;
    }
    
    private InfoDTO info;
    
    /**
     * Constructor por defecto
     */
    public ViniloDetailDTO() {
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ViniloDetailDTO(ViniloEntity entity) {
        super(entity);
        info=new InfoDTO( entity.getInfo());
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public ViniloEntity toEntity() {
        ViniloEntity viniloE = super.toEntity();
        viniloE.setInfo(info.toEntity());
        return viniloE;
    }
}

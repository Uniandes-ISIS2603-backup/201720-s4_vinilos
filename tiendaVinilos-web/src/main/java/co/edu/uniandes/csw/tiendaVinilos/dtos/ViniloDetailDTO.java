/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;

/**
 *
 * @author jp.monsalvo
 */
public class ViniloDetailDTO extends ViniloDTO{

    
    private CarroComprasDTO carros;
    
    public CarroComprasDTO getcarros()
    {
        return carros;
    }
    
    public void setCarros(CarroComprasDTO carros)
    {
        this.carros = carros;
    }
    
   private InfoDTO info;
   
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
        if (entity.getCarrosCompras() != null)
        {
            carros = entity.getCarrosCompras();
        }
        else carros = null;
        if(entity.getInfo()!=null)
        {
            info= entity.getInfo();
        }
        else info=null;
        if(entity.getProveedor()!=null){
            pro
        }
       
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    public ViniloEntity toEntity() {
        ViniloEntity entity = super.toEntity();
        
        if(entity != null){
            CarroComprasEntity ent = null;
            if (entity.getCarrosCompras() != null){
                ent = entity.getCarrosCompras();
            }
            entity.setCarrosCompras(ent);
        }
        return entity;
    }
}

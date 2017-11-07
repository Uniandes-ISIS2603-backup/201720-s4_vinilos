/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.InfoEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;

/**
 *
 * @author jp.monsalvo
 */
public class ViniloDetailDTO extends ViniloDTO{

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
    
    private ProveedorDTO proveedor;
    
    /**
     * @return the proveedor
     */
    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }
    
    
    
    private CarroComprasDTO carros;
    
    public CarroComprasDTO getcarros()
    {
        return carros;
    }
    
    public void setCarros(CarroComprasDTO carros)
    {
        this.carros = carros;
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
            carros = new CarroComprasDetailDTO(entity.getCarrosCompras());
        }
        else carros = null;
        if(entity.getProveedor()!=null){
            proveedor= new ProveedorDetailDTO(entity.getProveedor());
        }
        else proveedor=null;
        if(entity.getInfo()!=null){
            info= new InfoDetailDTO(entity.getInfo());
        }
        else info= null;
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public ViniloEntity toEntity() {
        ViniloEntity entity = super.toEntity();
        
        if(entity != null){
            CarroComprasEntity ent = null;
            ProveedorEntity provEnt= null;
            InfoEntity infoEnt= null;
            if (entity.getCarrosCompras() != null){
                ent = entity.getCarrosCompras();
            }
            if(entity.getCarrosCompras()!=null){
                provEnt=entity.getProveedor();
            }
            if(entity.getInfo()!=null){
                infoEnt= entity.getInfo();
            }
            entity.setInfo(infoEnt);
            entity.setCarrosCompras(ent);
            entity.setProveedor(provEnt);
        }
        return entity;
    }
}

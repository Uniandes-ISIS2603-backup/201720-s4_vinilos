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
    
    private UsuarioDTO usuario;
    
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
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
            if (entity.getCarrosCompras() != null){
                ent = entity.getCarrosCompras();
            }
            entity.setCarrosCompras(ent);
        }
        return entity;
    }
}

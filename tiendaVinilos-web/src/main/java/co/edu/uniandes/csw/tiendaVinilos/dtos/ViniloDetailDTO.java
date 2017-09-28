/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.dtos;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import java.util.ArrayList;
import java.util.List;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import java.util.List;

/**
 *
 * @author jp.monsalvo
 */
public class ViniloDetailDTO extends ViniloDTO{
    
    private List<CarroComprasDTO> carros;
    
    public List<CarroComprasDTO> getcarros()
    {
        return carros;
    }
    
    public void setCarros(List<CarroComprasDTO> carros)
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
        
        if(entity != null){
            carros = new ArrayList();
            for (CarroComprasEntity carEnt : entity.getCarrosCompras())
                carros.add(new CarroComprasDTO(carEnt));
        }
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public ViniloEntity toEntity() {
        ViniloEntity entity = super.toEntity();
        
        if (entity != null){
            List<CarroComprasEntity> listCar =null;
            if (getcarros() != null){
                listCar = new ArrayList<>();
                for (CarroComprasDTO carDto : getcarros())
                    listCar.add(carDto.toEntity());
            }
            entity.setCarrosCompras(listCar);
        }
        
        return entity;
    }
}

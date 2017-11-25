
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cs.gomez
 */
public class CarroComprasDetailDTO extends CarroComprasDTO {
    private List<ViniloDTO> vinilos;

    // Constructor por defecto
    public CarroComprasDetailDTO() {

        // Constructor por defecto
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public CarroComprasDetailDTO(CarroComprasEntity entity) {
        super(entity);

        if (entity != null) {
            vinilos = new ArrayList<>();

            for (ViniloEntity vinEnt : entity.getVinilos()) {
                vinilos.add(new ViniloDTO(vinEnt));
            }
        }
    }

    public void setVinilos(List<ViniloDTO> vinilos) {
        this.vinilos = vinilos;
    }

    public List<ViniloDTO> getVinilos() {
        return vinilos;
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return
     */
    @Override
    public CarroComprasEntity toEntity() {
        CarroComprasEntity entity = super.toEntity();

        if (entity != null) {
            List<ViniloEntity> listVin = null;

            if (getVinilos() != null) {
                listVin = new ArrayList<>();

                for (ViniloDTO vindto : getVinilos()) {
                    listVin.add(vindto.toEntity());
                }
            }

            entity.setVinilos(listVin);
        }

        return entity;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;

/**
 *
 * @author cs.gomez
 */
public class CarroComprasDTO {
    private Long   id;
    private double precioTotal;

    // Constructor por defecto
    public CarroComprasDTO() {

        // Constructor por defecto
    }

    public CarroComprasDTO(CarroComprasEntity carro) {
        if (carro != null) {
            this.id          = carro.getId();
            this.precioTotal = carro.getPrecioTotal();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public CarroComprasEntity toEntity() {
        CarroComprasEntity entity = new CarroComprasEntity();

        entity.setId(this.id);
        entity.setPrecioTotal(this.precioTotal);

        return entity;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

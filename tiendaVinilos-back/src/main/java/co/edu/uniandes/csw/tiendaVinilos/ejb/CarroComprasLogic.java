
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

//~--- non-JDK imports --------------------------------------------------------
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.CarroComprasPersistence;

//~--- JDK imports ------------------------------------------------------------
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import javax.inject.Inject;

/**
 *
 * @author cs.gomez
 */
@Stateless
public class CarroComprasLogic {

    @Inject
    CarroComprasPersistence persistence;

    public CarroComprasEntity createCarroCompras(CarroComprasEntity entity) {
        persistence.create(entity);

        return entity;
    }

    public List<CarroComprasEntity> getAllCarroCompras() {
        List<CarroComprasEntity> carro = persistence.findAll();

        return carro;
    }

    public CarroComprasEntity getCarroCompras(Long id) {
        return persistence.find(id);
    }

    public CarroComprasEntity updateCarroCompras(Long id, CarroComprasEntity entity) {
        return persistence.update(entity);
    }

    public void deleteCarroCompras(Long id) {
        persistence.delete(id);
    }

    public List<ViniloEntity> getVinilos(Long id) {
        CarroComprasEntity ent = persistence.find(id);

        return ent.getVinilos();
    }

    public void addCarrito(Long id, ViniloEntity vinEnt) {
        CarroComprasEntity ent = persistence.find(id);
        List<ViniloEntity> vinilos = ent.getVinilos();
        vinilos.add(vinEnt);
        ent.setVinilos(vinilos);
        persistence.update(ent);
    }

    public void deleteVinilo(Long id, ViniloEntity vinEnt) {
        CarroComprasEntity ent = persistence.find(id);
        List<ViniloEntity> vinilos = ent.getVinilos();
        vinilos.remove(vinEnt);
        ent.setVinilos(vinilos);
        updateCarroCompras(ent.getId(), ent);
    }

    public ViniloEntity getViniloFromCarrito(CarroComprasEntity carro, Long idVinilo) {
        ViniloEntity viniloResp = null;
        List<ViniloEntity> vinilos = carro.getVinilos();

        for (ViniloEntity vinilo : vinilos) {
            if (vinilo.getId() == idVinilo) {
                viniloResp = vinilo;
            }
        }

        return viniloResp;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

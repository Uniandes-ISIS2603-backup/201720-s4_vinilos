
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.FeedBackPersistence;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import javax.inject.Inject;

/**
 *
 * @author jc.ruiz
 */
@Stateless
public class FeedBackLogic {
    @Inject
    FeedBackPersistence persistence;

    public ProveedorEntity getProveedor(Long id) {
        return persistence.find(id).getProveedor();
    }

    public FeedBackEntity createFeedBack(FeedBackEntity entity) {
        persistence.create(entity);

        return entity;
    }

    public List<FeedBackEntity> getAll() {
        return persistence.findAll();
    }

    public FeedBackEntity getFeedBack(Long id) {
        return persistence.find(id);
    }

    public FeedBackEntity updateFeedBack(FeedBackEntity entity) {
        return persistence.update(entity);
    }

    public void deleteFeedBack(Long id) {
        persistence.delete(id);
    }

    public void agregarFeedBack(UsuarioEntity usuario, FeedBackEntity fb, ProveedorEntity proveedor) {
        fb.setProveedor(proveedor);
        fb.setUsuario(usuario);
        createFeedBack(fb);
    }

    public void deleteFB(FeedBackEntity fbEntity) {
        fbEntity.setProveedor(null);
        fbEntity.setUsuario(null);
        deleteFeedBack(fbEntity.getId());
    }

    /**
     * Modifica un feedback, junto con su proveedor y usuario
     * @param usuario nuevo usuario asociado
     * @param fb feedback por modificar
     * @param proveedor nuevo proveedor asociado
     */
    public void modificarFeedBack(UsuarioEntity usuario, FeedBackEntity fb, ProveedorEntity proveedor) {
        fb.setProveedor(proveedor);
        fb.setUsuario(usuario);
        updateFeedBack(fb);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.ejb;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.ProveedorPersistence;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;

import javax.ejb.Stateless;

import javax.inject.Inject;

/**
 *
 * @author jc.ruiz
 */
@Stateless
public class ProveedorLogic {
    @Inject
    ProveedorPersistence persistence;

    public List<PedidoProveedorEntity> getPedidos(Long id) {
        ProveedorEntity ent = persistence.find(id);

        return ent.getPedidos();
    }

    public PedidoProveedorEntity getPedido(Long id, Long idPedido) {
        ProveedorEntity       prove     = persistence.find(id);
        PedidoProveedorEntity pedidoEnt = new PedidoProveedorEntity();

        pedidoEnt.setId(idPedido);

        List<PedidoProveedorEntity> list  = prove.getPedidos();
        int                         index = list.indexOf(pedidoEnt);

        if (index >= 0) {
            return list.get(index);
        }

        return null;
    }

    public List<ViniloEntity> getVinilos(Long id) {
        ProveedorEntity ent = persistence.find(id);

        return ent.getVinilos();
    }

    public ViniloEntity getVinilo(Long idProv, Long idVin) {
        ProveedorEntity ent    = persistence.find(idProv);
        ViniloEntity    vinEnt = new ViniloEntity();

        vinEnt.setId(idVin);

        List<ViniloEntity> list  = ent.getVinilos();
        int                index = list.indexOf(vinEnt);

        if (index >= 0) {
            return list.get(index);
        }

        return null;
    }

    public List<PagoProveedorEntity> getPagosProveedor(Long id) {
        ProveedorEntity ent = persistence.find(id);

        return ent.getPagos();
    }

    public PagoProveedorEntity getPagoProveedor(Long idProv, Long idPP) {
        ProveedorEntity           ent      = persistence.find(idProv);
        List<PagoProveedorEntity> list     = ent.getPagos();
        PagoProveedorEntity       ppEntity = new PagoProveedorEntity();

        ppEntity.setId(idPP);

        int index = list.indexOf(ppEntity);

        if (index >= 0) {
            return list.get(index);
        }

        return null;
    }

    public List<FeedBackEntity> getFeedBacks(Long id) {
        ProveedorEntity ent = persistence.find(id);

        return ent.getFeedBacks();
    }

    public FeedBackEntity getFeedBack(Long idProv, Long idFB) {
        ProveedorEntity      ent      = persistence.find(idProv);
        List<FeedBackEntity> list     = ent.getFeedBacks();
        FeedBackEntity       fbEntity = new FeedBackEntity();

        fbEntity.setId(idFB);

        int index = list.indexOf(fbEntity);

        if (index >= 0) {
            return list.get(index);
        }

        return null;
    }

    public ProveedorEntity createProveedor(ProveedorEntity entity) throws BusinessLogicException {
        if (persistence.findByEmail(entity.getEmail()) != null) {
            throw new BusinessLogicException("Ya existe un proveedor con el email \"" + entity.getEmail() + "\"");
        }

        persistence.create(entity);

        return entity;
    }

    public List<ProveedorEntity> getAll() {
        return persistence.findAll();
    }

    public ProveedorEntity getProveedor(Long id) {
        return persistence.find(id);
    }

    public ProveedorEntity updateProveedor(ProveedorEntity entity) throws BusinessLogicException {
        return persistence.update(entity);
    }

    public void deleteProveedor(Long id) {
        persistence.delete(id);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;

/**
 *
 * @author cs.gomez
 */
public class CancionDetailDTO extends CancionDTO {
    private ViniloDTO  vinilo;
    private ArtistaDTO artista;

    // Constructor por defecto
    public CancionDetailDTO() {

        // Constructor por defecto
    }

    public CancionDetailDTO(CancionEntity entity) {
        super(entity);

        if (entity.getArtistas() != null) {
            artista = new ArtistaDetailDTO(entity.getArtistas());
        } else {
            artista = null;
        }

        if (entity.getVinilos() != null) {
            vinilo = new ViniloDetailDTO(entity.getVinilos());
        } else {
            vinilo = null;
        }
    }

    public ArtistaDTO getArtista() {
        return artista;
    }

    public ViniloDTO getVinilo() {
        return vinilo;
    }

    public void setVinilo(ViniloDTO vin) {
        this.vinilo = vin;
    }

    public void setArtista(ArtistaDTO artista) {
        this.artista = artista;
    }

    @Override
    public CancionEntity toEntity() {
        CancionEntity entity = super.toEntity();

        if (entity != null) {
            ViniloEntity  vinEnt = null;
            ArtistaEntity ent    = null;

            if (entity.getArtistas() != null) {
                ent = entity.getArtistas();
            }

            if (entity.getVinilos() != null) {
                vinEnt = entity.getVinilos();
            }

            entity.setVinilos(vinEnt);
            entity.setArtistas(ent);
        }

        return entity;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

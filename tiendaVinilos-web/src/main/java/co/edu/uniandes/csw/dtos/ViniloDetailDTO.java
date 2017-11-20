package co.edu.uniandes.csw.dtos;

import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.InfoEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jp.monsalvo
 */
public class ViniloDetailDTO extends ViniloDTO {

    /*
    * Relación a un proveedor
     */
    private ProveedorDTO proveedor;

// relación  cero o muchos infos
    private List<InfoDTO> infos;

    // relación  cero o muchos canciones
    private List<CancionDTO> canciones;
    
    // relación  cero o muchos canciones
    private List<ArtistaDTO> artistas;

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
        if (entity != null) {
            canciones = new ArrayList<>();
            for (CancionEntity entityCancion : entity.getCanciones()) {
                canciones.add(new CancionDTO(entityCancion));
            }
            if (entity.getProveedor() != null) {
                this.proveedor = new ProveedorDTO(entity.getProveedor());
            } else {
                entity.setProveedor(null);
            }
            if (entity.getInfos() != null) {
                infos = new ArrayList<>();
                for (InfoEntity entityInfo : entity.getInfos()) {
                    infos.add(new InfoDTO(entityInfo));
                }
            }
             if (entity.getArtistas() != null) {
                artistas = new ArrayList<>();
                for (ArtistaEntity entityArt : entity.getArtistas()) {
                    artistas.add(new ArtistaDTO(entityArt));
                }
            }
          

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
        if (getCanciones() != null) {
            List<CancionEntity> cancionesEntity = new ArrayList<>();
            for (CancionDTO dtoCancion : getCanciones()) {
                cancionesEntity.add(dtoCancion.toEntity());
            }
            entity.setCanciones(cancionesEntity);
        }

        if (this.getProveedor() != null) {
            entity.setProveedor(this.getProveedor().toEntity());
        }
        if (getInfos() != null) {
            List<InfoEntity> infosEntity = new ArrayList<>();
            for (InfoDTO dtoInfo : getInfos()) {
                infosEntity.add(dtoInfo.toEntity());
            }
            entity.setInfos(infosEntity);
        }
        if (getArtistas() != null) {
            List<ArtistaEntity> artistasEntity = new ArrayList<>();
            for (ArtistaDTO dtoArt : getArtistas()) {
                artistasEntity.add(dtoArt.toEntity());
            }
            entity.setArtistas(artistasEntity);
        }

        return entity;
    }

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

    /**
     * @return the infos
     */
    public List<InfoDTO> getInfos() {
        return infos;
    }

    /**
     * @param infos the infos to set
     */
    public void setInfos(List<InfoDTO> infos) {
        this.infos = infos;
    }

    /**
     * @return the canciones
     */
    public List<CancionDTO> getCanciones() {
        return canciones;
    }

    /**
     * @param canciones the canciones to set
     */
    public void setCanciones(List<CancionDTO> canciones) {
        this.canciones = canciones;
    }

       /**
     * @return the artistas
     */
    public List<ArtistaDTO> getArtistas() {
        return artistas;
    }

    /**
     * @param artistas the artistas to set
     */
    public void setArtistas(List<ArtistaDTO> artistas) {
        this.artistas = artistas;
    }
  
}
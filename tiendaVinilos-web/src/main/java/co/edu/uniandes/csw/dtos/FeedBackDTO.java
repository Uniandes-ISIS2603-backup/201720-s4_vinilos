
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.dtos;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;

/**
 *
 * @author jc.ruiz
 */
public class FeedBackDTO {

    /*
     * Fue necesario crear idFb como un String debido a que el json no muestra cuando es Long, por tanto es la unica manera de poder saber cual es el id del feedback,
     * sin embargo, en toda la logica se sigue usando el id original (idVerdadero)
     */
    private String idFb;
    long           idVerdadero;
    private String comentario;
    private double calificacion;

    // Constructor por defecto
    public FeedBackDTO() {

        // Constructor por defecto
    }

    public FeedBackDTO(FeedBackEntity entity) {
        idVerdadero  = entity.getId();
        calificacion = entity.getCalificacion();
        comentario   = entity.getComentario();
        idFb         = entity.getId() + "";
    }

    public String getIdFb() {
        return idFb;
    }

    public void setIdFb(String idFb) {
        this.idFb = idFb;
    }

    public long getId() {
        return idVerdadero;
    }

    public String getComentario() {
        return comentario;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setId(Long id) {
        this.idVerdadero = id;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public FeedBackEntity toEntity() {
        FeedBackEntity ent = new FeedBackEntity();

        ent.setCalificacion(this.getCalificacion());
        ent.setComentario(this.getComentario());
        ent.setId(this.getId());

        return ent;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

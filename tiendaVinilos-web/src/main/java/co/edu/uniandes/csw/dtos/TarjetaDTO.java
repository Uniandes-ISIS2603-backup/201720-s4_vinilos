/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */



package co.edu.uniandes.csw.dtos;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TarjetaDTO Objeto de transferencia de datos de Tarjetaes. Los DTO contienen
 * las represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * @author jd.arenas
 */
public class TarjetaDTO {
    private Integer numero;
    private String  nombrePropietario;
    private Long    id;
    private String  name;
    private Integer cvc;
    @Temporal(TemporalType.DATE)
    private Date    goodThru;

    // Constructor por defecto
    public TarjetaDTO() {

        // Constructor por defecto
    }

    public TarjetaDTO(TarjetaEntity tarjeta) {
        this.numero            = tarjeta.getNumero();
        this.nombrePropietario = tarjeta.getNombrePropietario();
        this.id                = tarjeta.getId();
        this.goodThru          = tarjeta.getGoodThru();
        this.cvc               = tarjeta.getCvc();
        this.name              = tarjeta.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
    public Integer getNumero() {
        return numero;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }
    
    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    public void setGoodThru(Date goodThru) {
        this.goodThru = goodThru;
    }
      
    public Date getGoodThru() {
        return goodThru;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public TarjetaEntity toEntity() {
        TarjetaEntity entity = new TarjetaEntity();

        entity.setCvc(this.cvc);
        entity.setGoodThru(this.goodThru);
        entity.setNombrePropietario(nombrePropietario);
        entity.setNumero(this.numero);
        entity.setId(this.id);
        entity.setName(this.name);

        return entity;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

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

import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;

/**
 * UsuarioDTO Objeto de transferencia de datos de Usuarioes. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el cliente y el servidor.
 *
 * @author ISIS2603
 */
public class UsuarioDTO {
    private Long   id;
    private String name;
    private int    cantCompras;
    private String email;

    // Constructor por defecto
    public UsuarioDTO() {

        // Constructor por defecto
    }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param usuario: Es la entidad que se va a convertir a DTO
     */
    public UsuarioDTO(UsuarioEntity usuario) {
        this.id          = usuario.getId();
        this.name        = usuario.getName();
        this.email       = usuario.geteMail();
        this.cantCompras = usuario.getCantCompras();
    }

    public int getNumCompras() {
        return cantCompras;
    }

    public void setNumCompras(int cantCompras) {
        this.cantCompras = cantCompras;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO
     */
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();

        entity.setId(this.id);
        entity.setName(this.name);
        entity.setCantCompras(this.cantCompras);
        entity.seteMail(this.email);

        return entity;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

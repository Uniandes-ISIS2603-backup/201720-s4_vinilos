/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jd.arenas
 */
@Stateless
public class UsuarioPersistence extends DefaultPersistence{
    @PersistenceContext(unitName="tiendaVinilosPU")
    protected EntityManager em;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.resources;

import co.edu.uniandes.csw.tiendaVinilos.ejb.ViniloLogic;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jp.monsalvo
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViniloArtistasResource {
    
    @Inject
    private ViniloLogic viniloLogic;
    
}

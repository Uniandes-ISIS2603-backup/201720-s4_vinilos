
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.logic.Test;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

/**
 *
 * @author jd.arenas
 */
public class UsuarioLogicJUnitTest {
    @Inject
    UsuarioLogic        logic;
    List<UsuarioEntity> usuarios;

    public UsuarioLogicJUnitTest() {}

    @BeforeClass
    public static void setUpClass() {}

    @AfterClass
    public static void tearDownClass() {}

    @Before
    public void setUp() {
        usuarios = new ArrayList<>();

        List<UsuarioEntity> users = logic.getUsuarios();

        for (UsuarioEntity user : users) {
            usuarios.add(user);
            logic.deleteUsuario(user.getId());
        }

        for (int i = 0; i < 10; i++) {
            UsuarioEntity usu = new UsuarioEntity();

            usu.setName("usuario" + i);

            try {
                logic.createUsuario(usu);
            } catch (BusinessLogicException ex) {
                Logger.getLogger(UsuarioLogicJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @After
    public void tearDown() {
        for (UsuarioEntity usuario : usuarios) {
            try {
                logic.createUsuario(usuario);
            } catch (BusinessLogicException ex) {
                Logger.getLogger(UsuarioLogicJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testCreateUsuarios() {
        for (int i = 0; i < 10; i++) {
            try {
                UsuarioEntity usuario = new UsuarioEntity();

                logic.createUsuario(usuario);
                assertNotNull(logic.getUsuario(usuario.getId()));
                assertEquals(usuario, logic.getUsuario(usuario.getId()));
                logic.deleteUsuario(usuario.getId());
            } catch (BusinessLogicException ex) {
                fail();
            }
        }
    }

    @Test
    public void testDeleteUsuario() {
        for (int i = 0; i < 10; i++) {
            try {
                UsuarioEntity usuario = new UsuarioEntity();

                logic.createUsuario(usuario);
                assertNotNull(logic.getUsuario(usuario.getId()));
                assertEquals(usuario, logic.getUsuario(usuario.getId()));
                logic.deleteUsuario(usuario.getId());
                assertNull(usuario.getId());
            } catch (BusinessLogicException ex) {
                fail();
            }
        }
    }

    @Test
    public void testUpdateUsuario() {
        for (int i = 0; i < 10; i++) {
            try {
                UsuarioEntity usuario = new UsuarioEntity();

                usuario.setName("Julian");
                logic.createUsuario(usuario);
                usuario.setName("Ruiz");
                logic.updateUsuario(usuario.getId(), usuario);
                assertNotEquals(logic.getUsuario(usuario.getId()).getName(), "Julian");
                logic.deleteUsuario(usuario.getId());
            } catch (BusinessLogicException ex) {
                fail();
            }
        }
    }

    @Test
    public void testGetUsuarios() {
        List<UsuarioEntity> users      = new ArrayList<>();
        int                 actualSize = logic.getUsuarios().size();

        for (int i = 0; i < 10; i++) {
            try {
                UsuarioEntity usuario = new UsuarioEntity();

                logic.createUsuario(usuario);
                assertTrue(actualSize + i + 1 == logic.getUsuarios().size());
                assertNotNull(logic.getUsuario(usuario.getId()));
                users.add(usuario);
            } catch (BusinessLogicException ex) {
                fail();
            }
        }

        for (UsuarioEntity user : users) {
            logic.deleteUsuario(user.getId());
        }
    }

    @Test
    public void testGetTarjetas() {
        for (int i = 0; i < 10; i++) {
            try {
                List<TarjetaEntity> tarjetas = new ArrayList<>();

                for (int j = 0; j < 10; j++) {
                    TarjetaEntity card = new TarjetaEntity();

                    card.setName("Tarjeta" + j);
                    tarjetas.add(card);
                }

                UsuarioEntity usuario = new UsuarioEntity();

                usuario.setTarjetas(tarjetas);
                logic.createUsuario(usuario);
                assertNotNull(logic.getTarjetas(usuario.getId()));
                logic.deleteUsuario(usuario.getId());
            } catch (BusinessLogicException ex) {
                fail();
            }
        }
    }

    @Test
    public void testGetPedidos() {
        for (int i = 0; i < 10; i++) {
            try {
                List<PedidoClienteEntity> pedidos = new ArrayList<>();

                for (int j = 0; j < 10; j++) {
                    PedidoClienteEntity pedido = new PedidoClienteEntity();

                    pedido.setName("pedido" + j);
                    pedidos.add(pedido);
                }

                UsuarioEntity usuario = new UsuarioEntity();

                usuario.setPedidos(pedidos);
                logic.createUsuario(usuario);
                assertNotNull(logic.getPedidos(usuario.getId()));
                logic.deleteUsuario(usuario.getId());
            } catch (BusinessLogicException ex) {
                fail();
            }
        }
    }

    @Test
    public void testGetCarrito() {
        for (int i = 0; i < 10; i++) {
            try {
                CarroComprasEntity carro = new CarroComprasEntity();

                carro.setName("carro" + i);

                UsuarioEntity usuario = new UsuarioEntity();

                usuario.setCarrito(carro);
                logic.createUsuario(usuario);
                assertNotNull(logic.getCarrito(usuario.getId()));
                logic.deleteUsuario(usuario.getId());
            } catch (BusinessLogicException ex) {
                fail();
            }
        }
    }

    @Test
    public void testGetFeedBacks() {
        for (int i = 0; i < 10; i++) {
            try {
                List<FeedBackEntity> feeds = new ArrayList<>();

                for (int j = 0; j < 10; j++) {
                    FeedBackEntity feed = new FeedBackEntity();

                    feed.setComentario("feed" + j);
                    feeds.add(feed);
                }

                UsuarioEntity usuario = new UsuarioEntity();

                usuario.setFeedBacks(feeds);
                logic.createUsuario(usuario);
                assertNotNull(logic.getPedidos(usuario.getId()));
                logic.deleteUsuario(usuario.getId());
            } catch (BusinessLogicException ex) {
                fail();
            }
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

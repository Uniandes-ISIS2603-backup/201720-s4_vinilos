
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
import co.edu.uniandes.csw.tiendaVinilos.persistence.UsuarioPersistence;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.transaction.UserTransaction;

//~--- JDK imports ------------------------------------------------------------
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jd.arenas
 */
@RunWith(Arquillian.class)
public class UsuarioLogicJUnitTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(PedidoClienteEntity.class.getPackage())
                .addPackage(TarjetaEntity.class.getPackage())
                .addPackage(FeedBackEntity.class.getPackage())
                .addPackage(CarroComprasEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Inyección de la dependencia a la clase UsuarioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private UsuarioPersistence persistence;
    /**
     *
     */
    @Inject
    private UsuarioLogic logic;
    /**
     *
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     *
     */
    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();

    private UsuarioEntity entity = new UsuarioEntity();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de XYZ, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyección de dependencias.
     */
    public UsuarioLogicJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();

            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateUsuario() {
        entity = new UsuarioEntity();
        try {
            logic.createUsuario(entity);
            assertEquals(logic.getUsuario(entity.getId()).getId(), entity.getId());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteUsuario() {
        for (int i = 0; i < 10; i++) {
            try {
                UsuarioEntity usuario = new UsuarioEntity();
                logic.createUsuario(usuario);
                assertNotNull(logic.getUsuario(usuario.getId()));
                assertEquals(usuario.getId(), logic.getUsuario(usuario.getId()).getId());
                logic.deleteUsuario(usuario.getId());
                logic.getUsuario(usuario.getId());
            } catch (BusinessLogicException ex) {
                assertTrue(true);
            }
        }
    }

    @Test
    public void testUpdateUsuario() {
        entity = new UsuarioEntity();
        try {
            logic.createUsuario(entity);
            entity.setName("Julian");
            assertEquals(logic.updateUsuario(entity.getId(), entity), entity);
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    @Test
    public void testGetUsuarios() {
        entity = new UsuarioEntity();
        try {
            logic.createUsuario(entity);
            assertEquals(logic.getUsuario(entity.getId()).getId(), entity.getId());
            assertTrue(logic.getUsuarios().size()>0);
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    @Test
    public void testGetTarjetas() {
        entity = new UsuarioEntity();
        try {
            List<TarjetaEntity> tarjetas = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                TarjetaEntity tarjeta = new TarjetaEntity();
                tarjeta.setName("tarjeta" + i);
            }

            entity.setTarjetas(tarjetas);
            logic.createUsuario(entity);
            assertEquals(tarjetas, logic.getUsuario(entity.getId()).getTarjetas());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    @Test
    public void testGetPedidos() {
        entity = new UsuarioEntity();
        try {
            List<PedidoClienteEntity> pedidos = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                PedidoClienteEntity pedido = new PedidoClienteEntity();
                pedido.setName("pedido" + i);
            }

            entity.setPedidos(pedidos);
            logic.createUsuario(entity);
            assertEquals(pedidos, logic.getUsuario(entity.getId()).getPedidos());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    @Test
    public void testGetCarrito() {

        entity = new UsuarioEntity();
        try {
            CarroComprasEntity carro = new CarroComprasEntity();
            carro.setUsuario(entity);
            entity.setCarrito(carro);
            logic.createUsuario(entity);
            assertNotNull(logic.getUsuario(entity.getId()).getCarrito());
            assertEquals(carro.getId(), logic.getUsuario(entity.getId()).getCarrito().getId());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    @Test
    public void testGetFeedBacks() {
        entity = new UsuarioEntity();
        try {
            List<FeedBackEntity> feeds = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                FeedBackEntity feed = new FeedBackEntity();
                feed.setComentario("feed" + i);
            }

            entity.setFeedBacks(feeds);
            logic.createUsuario(entity);
            assertEquals(feeds, logic.getUsuario(entity.getId()).getFeedBacks());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    private void clearData() {
        em.createQuery("delete from usuarioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 10; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class
            );

            em.persist(entity);
            data.add(entity);
        }
    }
}
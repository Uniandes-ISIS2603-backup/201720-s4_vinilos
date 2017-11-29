/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.logic.Test;

import co.edu.uniandes.csw.tiendaVinilos.ejb.PagoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoClienteLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.PedidoProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.PagoProveedorPersistennce;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
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
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jc.ruiz
 */
@RunWith(Arquillian.class)
public class PedidoClienteLogicTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PedidoClienteEntity.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(PagoProveedorLogic.class.getPackage())
                .addPackage(PagoProveedorPersistennce.class.getPackage())
                .addPackage(PedidoProveedorLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     *
     */
    @Inject
    private PedidoClienteLogic logic;
    /**
     *
     */
    @Inject
    private UsuarioLogic logicUsuario;

    @Inject
    private PedidoProveedorLogic logicPedidoProveedor;
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
    private List<PedidoClienteEntity> data = new ArrayList<PedidoClienteEntity>();

    private PedidoClienteEntity entity = new PedidoClienteEntity();

    public PedidoClienteLogicTest() {
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

    /**
     * Test of createPedido method, of class PedidoClienteLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreatePedido() throws Exception {
        entity = new PedidoClienteEntity();
        UsuarioEntity usuario = new UsuarioEntity();
        logicUsuario.createUsuario(usuario);
        try {
            logic.createPedido(entity, usuario);
            assertEquals(logic.getPedido(entity.getId()).getId(), entity.getId());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of getPedido method, of class PedidoClienteLogic.
     */
    @Test
    public void testGetPedido() throws Exception {
        entity = new PedidoClienteEntity();
        UsuarioEntity usuario = new UsuarioEntity();
        logicUsuario.createUsuario(usuario);
        try {
            logic.createPedido(entity, usuario);
            assertEquals(logic.getPedido(entity.getId()).getId(), entity.getId());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of updatePedido method, of class PedidoClienteLogic.
     */
    @Test
    public void testUpdatePedido() throws Exception {
        entity = new PedidoClienteEntity();
        UsuarioEntity usuario = new UsuarioEntity();
        logicUsuario.createUsuario(usuario);
         entity.setEstado("Aceptado");
        try {
            logic.createPedido(entity, usuario);
            entity.setDireccion("calle 1");
            logic.updatePedido(entity.getId(), entity, usuario);
            assertEquals(logic.getPedido(entity.getId()).getId(), entity.getId());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of cancelarPedido method, of class PedidoClienteLogic.
     */
    @Test
    public void testCancelarPedido() throws Exception {
        entity = new PedidoClienteEntity();
        UsuarioEntity usuario = new UsuarioEntity();
        logicUsuario.createUsuario(usuario);
        entity.setEstado("Aceptado");
        try {
            logic.createPedido(entity, usuario);
            logic.cancelarPedido(entity.getId());
            assertEquals(logic.getPedido(entity.getId()).getEstado(), "CANCELADO");
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of deletePedido method, of class PedidoClienteLogic.
     */
    @Test
    public void testDeletePedido() throws Exception {
        entity = new PedidoClienteEntity();
        UsuarioEntity usuario = new UsuarioEntity();
        logicUsuario.createUsuario(usuario);
        entity.setEstado("Aceptado");
        try {
            logic.createPedido(entity, usuario);
            logic.deletePedido(entity.getId());
            logic.getPedido(entity.getId());
        } catch (BusinessLogicException ex) {
            assertTrue(true);
        }
    }

    /**
     * Test of getPedidos method, of class PedidoClienteLogic.
     */
    @Test
    public void testGetPedidos() throws Exception {
        entity = new PedidoClienteEntity();
        UsuarioEntity usuario = new UsuarioEntity();
        logicUsuario.createUsuario(usuario);
        try {
            logic.createPedido(entity, usuario);
            assertTrue(logic.getPedidos().size() > 0);
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of getPedidoProveedor method, of class PedidoClienteLogic.
     */
    @Test
    public void testGetPedidoProveedor() throws Exception {
        entity = new PedidoClienteEntity();
        UsuarioEntity usuario = new UsuarioEntity();
        logicUsuario.createUsuario(usuario);
        try {
            List<PedidoProveedorEntity> pedidos = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                PedidoProveedorEntity pedido = new PedidoProveedorEntity();
                pedido.setName("pedido" + i);
            }

            entity.setPedidoProveedor(pedidos);
            logic.createPedido(entity, usuario);
            assertEquals(pedidos, logic.getPedidoProveedor(entity.getId()));
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of getUsuario method, of class PedidoClienteLogic.
     */
    @Test
    public void testGetUsuario() throws Exception {
        entity = new PedidoClienteEntity();
        UsuarioEntity usuario = new UsuarioEntity();
        logicUsuario.createUsuario(usuario);
        try {
            logic.createPedido(entity, usuario);
            assertEquals(logic.getUsuario(entity.getId()).getId(), usuario.getId());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    private void clearData() {
        em.createQuery("delete from PedidoClienteEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 10; i++) {
            PedidoClienteEntity entity = factory.manufacturePojo(PedidoClienteEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
}

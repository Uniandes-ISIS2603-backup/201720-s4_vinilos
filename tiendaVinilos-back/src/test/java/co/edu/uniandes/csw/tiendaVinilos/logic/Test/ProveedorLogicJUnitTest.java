
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.logic.Test;

//~--- non-JDK imports --------------------------------------------------------
import co.edu.uniandes.csw.tiendaVinilos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.ProveedorPersistence;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jd.arenas
 */
@RunWith(Arquillian.class)
public class ProveedorLogicJUnitTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProveedorEntity.class.getPackage())
                .addPackage(ProveedorPersistence.class.getPackage())
                .addPackage(ProveedorLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Inyección de la dependencia a la clase ProveedorPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ProveedorPersistence persistence;
    /**
     *
     */
    @Inject
    private ProveedorLogic logic;
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
    private List<ProveedorEntity> data = new ArrayList<ProveedorEntity>();

    private ProveedorEntity entity = new ProveedorEntity();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de XYZ, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyección de dependencias.
     */
    public ProveedorLogicJUnitTest() {
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
    public void testCreateProveedor() {
        entity = new ProveedorEntity();
        try {
            logic.createProveedor(entity);
            assertEquals(logic.getProveedor(entity.getId()).getId(), entity.getId());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ProveedorLogicJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testDeleteProveedor() {
        for (int i = 0; i < 10; i++) {
            ProveedorEntity usuario = new ProveedorEntity();
            try {
                logic.createProveedor(usuario);
                assertNotNull(logic.getProveedor(usuario.getId()));
                assertEquals(usuario.getId(), logic.getProveedor(usuario.getId()).getId());
                logic.deleteProveedor(usuario.getId());
                assertNull(logic.getProveedor(usuario.getId()));
            } catch (BusinessLogicException ex) {
                Logger.getLogger(ProveedorLogicJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testUpdateProveedor() {
        entity = new ProveedorEntity();
        try {
            logic.createProveedor(entity);
            entity.setName("Julian");
            assertEquals(logic.updateProveedor(entity).getName(), "Julian");
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ProveedorLogicJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetProveedor() {
        entity = new ProveedorEntity();
        try {
            logic.createProveedor(entity);
            assertEquals(logic.getProveedor(entity.getId()).getId(), entity.getId());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ProveedorLogicJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Test
    public void testGetPedidos() {
        entity = new ProveedorEntity();
       try {
            List<PedidoProveedorEntity> pedidos = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                PedidoProveedorEntity pedido = new PedidoProveedorEntity();
                pedido.setName("pedido" + i);
            }
            entity.setPedidos(pedidos);
            logic.createProveedor(entity);
            assertEquals(pedidos, logic.getPedidos(entity.getId()));
       }catch (BusinessLogicException ex) {
           fail();
        }
    }
    
    @Test
    public void testGetPedido() {
        entity = new ProveedorEntity();
       try {
            List<PedidoProveedorEntity> pedidos = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                PedidoProveedorEntity pedido = new PedidoProveedorEntity();
                pedido.setName("pedido" + i);
            }
            entity.setPedidos(pedidos);
            logic.createProveedor(entity);
            assertNotNull(logic.getPedidos(entity.getId()));
       }catch (BusinessLogicException ex) {
           fail();
        }
    }
    
     @Test
    public void testGetVinilos() {
        entity = new ProveedorEntity();
       try {
            List<ViniloEntity> vinilos = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ViniloEntity vinilo = new ViniloEntity();
                vinilo.setName("vinilo" + i);
            }
            entity.setVinilos(vinilos);
            logic.createProveedor(entity);
            assertEquals(vinilos, logic.getVinilos(entity.getId()));
       }catch (BusinessLogicException ex) {
           fail();
        }
    }
    
     @Test
    public void testGetVinilo() {
        entity = new ProveedorEntity();
       try {
            List<ViniloEntity> vinilos = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ViniloEntity vinilo = new ViniloEntity();
                vinilo.setName("vinilo" + i);
            }
            entity.setVinilos(vinilos);
            logic.createProveedor(entity);
            assertNotNull(logic.getVinilos(entity.getId()));
       }catch (BusinessLogicException ex) {
           fail();
        }
    }
    
     @Test
    public void testGetPagosProveedor() {
        entity = new ProveedorEntity();
       try {
            List<PagoProveedorEntity> pagos = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                PagoProveedorEntity pago = new PagoProveedorEntity();
                pago.setName("pago" + i);
            }
            entity.setPagos(pagos);
            logic.createProveedor(entity);
            assertEquals(pagos, logic.getPagosProveedor(entity.getId()));
       }catch (BusinessLogicException ex) {
           fail();
        }
    }
    
    @Test
    public void testGetPagoProveedor() {
        entity = new ProveedorEntity();
       try {
            List<PagoProveedorEntity> pagos = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                PagoProveedorEntity pago = new PagoProveedorEntity();
                pago.setName("pago" + i);
            }
            entity.setPagos(pagos);
            logic.createProveedor(entity);
            assertNotNull(logic.getPagosProveedor(entity.getId()));
       }catch (BusinessLogicException ex) {
           fail();
        }
    }
    
    @Test
    public void testGetFeedbacks() {
        entity = new ProveedorEntity();
       try {
            List<FeedBackEntity> feedbacks = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                FeedBackEntity feedback = new FeedBackEntity();
                feedback.setComentario("feedback" + i);
            }
            entity.setFeedBacks(feedbacks);
            logic.createProveedor(entity);
            assertEquals(feedbacks, logic.getFeedBacks(entity.getId()));
       }catch (BusinessLogicException ex) {
           fail();
        }
    }
    
     @Test
    public void testGetFeedback() {
        entity = new ProveedorEntity();
       try {
             List<FeedBackEntity> feedbacks = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                FeedBackEntity feedback = new FeedBackEntity();
                feedback.setComentario("feedback" + i);
            }
            entity.setFeedBacks(feedbacks);
            logic.createProveedor(entity);
            assertNotNull(logic.getFeedBacks(entity.getId()));
       }catch (BusinessLogicException ex) {
           fail();
        }
    }
    
    @Test
    public void testGetAll() {
        entity = new ProveedorEntity();
       try {
            logic.createProveedor(entity);
            assertTrue(logic.getAll().size() >0);
       }catch (BusinessLogicException ex) {
           fail();
        }
    }
    
    
    
    
    

    private void clearData() {
        em.createQuery("delete from usuarioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 10; i++) {
            ProveedorEntity entity = factory.manufacturePojo(ProveedorEntity.class
            );

            em.persist(entity);
            data.add(entity);
        }
    }
}

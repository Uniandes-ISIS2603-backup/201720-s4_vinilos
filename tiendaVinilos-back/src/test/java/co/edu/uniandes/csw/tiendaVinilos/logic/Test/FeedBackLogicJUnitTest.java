
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.logic.Test;

//~--- non-JDK imports --------------------------------------------------------
import co.edu.uniandes.csw.tiendaVinilos.ejb.FeedBackLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.FeedBackPersistence;
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
public class FeedBackLogicJUnitTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FeedBackEntity.class.getPackage())
                .addPackage(FeedBackPersistence.class.getPackage())
                .addPackage(FeedBackLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Inyección de la dependencia a la clase FeedBackPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private FeedBackPersistence persistence;
    /**
     *
     */
    @Inject
    private FeedBackLogic logic;
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
    private List<FeedBackEntity> data = new ArrayList<FeedBackEntity>();

    private FeedBackEntity entity = new FeedBackEntity();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de XYZ, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyección de dependencias.
     */
    public FeedBackLogicJUnitTest() {
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
    public void testCreateFeedBack() {
        entity = new FeedBackEntity();
            logic.createFeedBack(entity);
            assertEquals(logic.getFeedBack(entity.getId()).getId(), entity.getId());

    }

    @Test
    public void testDeleteFeedBack() {
        for (int i = 0; i < 10; i++) {
                FeedBackEntity usuario = new FeedBackEntity();
                logic.createFeedBack(usuario);
                assertNotNull(logic.getFeedBack(usuario.getId()));
                assertEquals(usuario.getId(), logic.getFeedBack(usuario.getId()).getId());
                logic.deleteFeedBack(usuario.getId());
                assertNull(logic.getFeedBack(usuario.getId()));
        }
    }

    @Test
    public void testUpdateFeedBack() {
        entity = new FeedBackEntity();
            logic.createFeedBack(entity);
            entity.setComentario("Julian");
            assertNotEquals(logic.updateFeedBack(entity), entity);
    }

    @Test
    public void testGetFeedBacks() {
        entity = new FeedBackEntity();
            logic.createFeedBack(entity);
            assertEquals(logic.getFeedBack(entity.getId()).getId(), entity.getId());
  
    }

    private void clearData() {
        em.createQuery("delete from usuarioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 10; i++) {
            FeedBackEntity entity = factory.manufacturePojo(FeedBackEntity.class
            );

            em.persist(entity);
            data.add(entity);
        }
    }
}
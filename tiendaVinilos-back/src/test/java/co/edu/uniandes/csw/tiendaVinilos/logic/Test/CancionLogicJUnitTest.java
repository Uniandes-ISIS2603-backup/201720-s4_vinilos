
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.logic.Test;

//~--- non-JDK imports --------------------------------------------------------
import co.edu.uniandes.csw.tiendaVinilos.ejb.CancionLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.CancionPersistence;
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
public class CancionLogicJUnitTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CancionEntity.class.getPackage())
                .addPackage(CancionPersistence.class.getPackage())
                .addPackage(CancionLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Inyección de la dependencia a la clase CancionPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private CancionPersistence persistence;
    /**
     *
     */
    @Inject
    private CancionLogic logic;
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
    private List<CancionEntity> data = new ArrayList<CancionEntity>();

    private CancionEntity entity = new CancionEntity();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de XYZ, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyección de dependencias.
     */
    public CancionLogicJUnitTest() {
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
    public void testCreateCancion() {
        entity = new CancionEntity();
        try {
            logic.createCancion(entity);
            assertEquals(logic.getCancion(entity.getId()).getId(), entity.getId());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteCancion() {
        for (int i = 0; i < 10; i++) {
            try {
                CancionEntity usuario = new CancionEntity();
                logic.createCancion(usuario);
                assertNotNull(logic.getCancion(usuario.getId()));
                assertEquals(usuario.getId(), logic.getCancion(usuario.getId()).getId());
                logic.deleteCancion(usuario.getId());
                logic.getCancion(usuario.getId());
            } catch (BusinessLogicException ex) {
                assertTrue(true);
            }
        }
    }

    @Test
    public void testUpdateCancion() {
        entity = new CancionEntity();
        try {
            logic.createCancion(entity);
            entity.setName("Julian");
            assertEquals(logic.updateCancion(entity.getId(), entity), entity);
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    @Test
    public void testGetCancions() {
        entity = new CancionEntity();
        try {
            logic.createCancion(entity);
            assertEquals(logic.getCancion(entity.getId()).getId(), entity.getId());
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
            CancionEntity entity = factory.manufacturePojo(CancionEntity.class
            );

            em.persist(entity);
            data.add(entity);
        }
    }
}
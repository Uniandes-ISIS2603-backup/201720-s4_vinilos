
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.logic.Test;

//~--- non-JDK imports --------------------------------------------------------
import co.edu.uniandes.csw.tiendaVinilos.ejb.TarjetaLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.TarjetaPersistence;
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
public class TarjetaLogicJUnitTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaEntity.class.getPackage())
                .addPackage(TarjetaPersistence.class.getPackage())
                .addPackage(TarjetaLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Inyección de la dependencia a la clase TarjetaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private TarjetaPersistence persistence;
    /**
     *
     */
    @Inject
    private TarjetaLogic logic;
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
    private List<TarjetaEntity> data = new ArrayList<TarjetaEntity>();

    private TarjetaEntity entity = new TarjetaEntity();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de XYZ, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyección de dependencias.
     */
    public TarjetaLogicJUnitTest() {
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
    public void testCreateTarjeta() {
        try {
            entity = new TarjetaEntity();
            logic.createTarjeta(entity);
            assertEquals(logic.getTarjeta(entity.getId()).getId(), entity.getId());
        } catch (BusinessLogicException ex) {
            fail();
        }

    }

    @Test
    public void testDeleteTarjeta() {
        for (int i = 0; i < 10; i++) {
            try {
                TarjetaEntity usuario = new TarjetaEntity();
                logic.createTarjeta(usuario);
                assertNotNull(logic.getTarjeta(usuario.getId()));
                assertEquals(usuario.getId(), logic.getTarjeta(usuario.getId()).getId());
                logic.deleteTarjeta(usuario.getId());
                assertNull(logic.getTarjeta(usuario.getId()));
            } catch (BusinessLogicException ex) {
                fail();
            }
        }
    }

    @Test
    public void testUpdateTarjeta() {
        entity = new TarjetaEntity();
        try {
            logic.createTarjeta(entity);
            entity.setName("Julian");
            assertEquals(logic.updateTarjeta(entity.getId(), entity).getName(), "Julian");
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    @Test
    public void testGetTarjetas() {
        entity = new TarjetaEntity();
        try {
            logic.createTarjeta(entity);
            assertEquals(logic.getTarjeta(entity.getId()).getId(), entity.getId());

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
            TarjetaEntity entity = factory.manufacturePojo(TarjetaEntity.class
            );

            em.persist(entity);
            data.add(entity);
        }
    }
}

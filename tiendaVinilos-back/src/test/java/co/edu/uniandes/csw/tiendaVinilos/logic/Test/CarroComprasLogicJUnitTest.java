
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.logic.Test;

//~--- non-JDK imports --------------------------------------------------------
import co.edu.uniandes.csw.tiendaVinilos.ejb.CarroComprasLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.CarroComprasPersistence;
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
public class CarroComprasLogicJUnitTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarroComprasEntity.class.getPackage())
                .addPackage(CarroComprasPersistence.class.getPackage())
                .addPackage(CarroComprasLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Inyección de la dependencia a la clase CarroComprasPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private CarroComprasPersistence persistence;
    /**
     *
     */
    @Inject
    private CarroComprasLogic logic;
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
    private List<CarroComprasEntity> data = new ArrayList<CarroComprasEntity>();

    private CarroComprasEntity entity = new CarroComprasEntity();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de XYZ, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyección de dependencias.
     */
    public CarroComprasLogicJUnitTest() {
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
    public void testCreateCarroCompras() {
        entity = new CarroComprasEntity();
            logic.createCarroCompras(entity);
            assertEquals(logic.getCarroCompras(entity.getId()).getId(), entity.getId());

    }

    @Test
    public void testDeleteCarroCompras() {
        for (int i = 0; i < 10; i++) {
                CarroComprasEntity usuario = new CarroComprasEntity();
                logic.createCarroCompras(usuario);
                assertNotNull(logic.getCarroCompras(usuario.getId()));
                assertEquals(usuario.getId(), logic.getCarroCompras(usuario.getId()).getId());
                logic.deleteCarroCompras(usuario.getId());
                assertNull(logic.getCarroCompras(usuario.getId()));
        }
    }

    @Test
    public void testUpdateCarroCompras() {
        entity = new CarroComprasEntity();
            logic.createCarroCompras(entity);
            entity.setName("Julian");
            assertNotEquals(logic.updateCarroCompras(entity.getId(), entity), entity);
    }

    @Test
    public void testGetCarroComprass() {
        entity = new CarroComprasEntity();
            logic.createCarroCompras(entity);
            assertEquals(logic.getCarroCompras(entity.getId()).getId(), entity.getId());
  
    }

    private void clearData() {
        em.createQuery("delete from usuarioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 10; i++) {
            CarroComprasEntity entity = factory.manufacturePojo(CarroComprasEntity.class
            );

            em.persist(entity);
            data.add(entity);
        }
    }
}
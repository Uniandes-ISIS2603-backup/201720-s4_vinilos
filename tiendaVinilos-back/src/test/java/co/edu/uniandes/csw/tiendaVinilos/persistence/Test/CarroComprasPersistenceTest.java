/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence.Test;

import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.CarroComprasPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.jboss.arquillian.junit.Arquillian;

import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;

import org.jboss.shrinkwrap.api.ShrinkWrap;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author cs.gomez
 */
@RunWith(Arquillian.class)
public class CarroComprasPersistenceTest {
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de CarroCompras, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarroComprasEntity.class.getPackage())
                .addPackage(CarroComprasPersistence.class.getPackage())
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
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
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
    
    
    
    public CarroComprasPersistenceTest() {
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
    
    private void clearData() {
        em.createQuery("delete from CarroComprasEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CarroComprasEntity entity = factory.manufacturePojo(CarroComprasEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class CarroComprasPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        CarroComprasEntity newEntity = factory.manufacturePojo(CarroComprasEntity.class);
        CarroComprasEntity result = persistence.create(newEntity);

        assertNotNull(result);
        CarroComprasEntity entity = em.find(CarroComprasEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Test of uptade method, of class CarroComprasPersistence.
     */
    @Test
    public void testUptade() throws Exception {
        for (int i = 0; i < data.size(); i++) {
            CarroComprasEntity entity = data.get(i);
            PodamFactory factory = new PodamFactoryImpl();
            CarroComprasEntity newEntity = factory.manufacturePojo(CarroComprasEntity.class);
            newEntity.setId(entity.getId());
            persistence.update(newEntity);
            CarroComprasEntity resp = em.find(CarroComprasEntity.class, entity.getId());
            assertEquals(newEntity.getName() , resp.getName());
        }
    }

    /**
     * Test of delete method, of class CarroComprasPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        for (int i = 0; i < data.size(); i++) {
            CarroComprasEntity entity = data.get(i);
            persistence.delete(entity.getId());
            CarroComprasEntity deleted = em.find(CarroComprasEntity.class, entity.getId());
            assertNull(deleted);
        }
    }

    /**
     * Test of find method, of class CarroComprasPersistence.
     */
    @Test
    public void testFind() throws Exception {
        for (int i = 0; i < data.size(); i++) {
            CarroComprasEntity entity = data.get(i);
            CarroComprasEntity newEntity =  persistence.find(entity.getId());
            assertNotNull(newEntity);
            assertEquals(entity.getId(), newEntity.getId());

        }
    }

    /**
     * Test of findAll method, of class CarroComprasPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<CarroComprasEntity> list = persistence.findAll();
        assertEquals(data.size(), list.size());
        for (CarroComprasEntity ent : list) {
            boolean found = false;
            for (CarroComprasEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }
    
}

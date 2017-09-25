/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
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
import org.junit.runner.RunWith;
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
public class ArtistaPersistenceTest {
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de XYZ, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ArtistaEntity.class.getPackage())
                .addPackage(ArtistaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ArtistaPersistence persistence;

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
    private List<ArtistaEntity> data = new ArrayList<ArtistaEntity>();
    
    
    
    public ArtistaPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    
    private void clearData() {
        em.createQuery("delete from XYZEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ArtistaEntity entity = factory.manufacturePojo(ArtistaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
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
     * Test of create method, of class ArtistaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
        ArtistaEntity result = (ArtistaEntity)persistence.create(newEntity);
        assertNotNull(result);
        ArtistaEntity entity = em.find(ArtistaEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class ArtistaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        for (int i = 0; i < data.size(); i++) {
            ArtistaEntity entity = data.get(i);
            PodamFactory factory = new PodamFactoryImpl();
            ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
            newEntity.setId(entity.getId());
            persistence.update(newEntity);
            ArtistaEntity resp = em.find(ArtistaEntity.class, entity.getId());
            assertEquals(newEntity.getName(), resp.getName());
        }
    }

    /**
     * Test of delete method, of class ArtistaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
       for (int i = 0; i < data.size(); i++) {
            ArtistaEntity entity = data.get(i);
            persistence.delete(entity.getId());
            ArtistaEntity deleted = em.find(ArtistaEntity.class, entity.getId());
            assertNull(deleted);
        }
    }

    /**
     * Test of find method, of class ArtistaPersistence.
     */
    @Test
    public void testFind() throws Exception {
       for (int i = 0; i < data.size(); i++) {
            ArtistaEntity entity = data.get(i);
            ArtistaEntity newEntity = (ArtistaEntity) persistence.find(entity.getId());
            assertNotNull(newEntity);
            assertEquals(entity.getName(), newEntity.getName());

        }
    }

    /**
     * Test of findAll method, of class ArtistaPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
         List list = persistence.findAll();
        assertEquals(data.size(), list.size());
        for (Object ent : list) {
            boolean found = false;
            for (ArtistaEntity entity : data) {
                 if (((ArtistaEntity)ent).getId().equals(entity.getId())) {
                     found = true;
                }
            }
            assertTrue(found);
        }
    }
    
    
    
}

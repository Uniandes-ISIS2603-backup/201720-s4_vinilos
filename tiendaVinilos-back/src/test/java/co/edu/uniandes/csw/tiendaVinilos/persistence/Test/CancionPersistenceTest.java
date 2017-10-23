/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
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
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.PodamFactory;


/**
 *
 * @author cs.gomez
 */
@RunWith(Arquillian.class)
public class CancionPersistenceTest {
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Cancion, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CancionEntity.class.getPackage())
                .addPackage(CancionPersistence.class.getPackage())
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
    private List<CancionEntity> data = new ArrayList<CancionEntity>();
    
    
    
    public CancionPersistenceTest() {
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
        em.createQuery("delete from CancionEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CancionEntity entity = factory.manufacturePojo(CancionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    
    
    @After
    public void tearDown() {
    }

   
    
    
     @Test
    public void testCreate() throws Exception {
        // TODO review the generated test code and remove the default call to fail.
        PodamFactory factory = new PodamFactoryImpl();
    CancionEntity newEntity = factory.manufacturePojo(CancionEntity.class);
    CancionEntity result = (CancionEntity)persistence.create(newEntity);
    assertNotNull(result);
    CancionEntity entity = em.find(CancionEntity.class, result.getId());
    assertNotNull(entity);
    assertEquals(newEntity.getName(), entity.getName());
    }

    @Test
    public void testUpdate() throws Exception {
        // TODO review the generated test code and remove the default call to fail.
        for (int i = 0; i < data.size(); i++) {
            CancionEntity entity = data.get(i);

            PodamFactory factory = new PodamFactoryImpl();
            CancionEntity newEntity = factory.manufacturePojo(CancionEntity.class);
            newEntity.setId(entity.getId());
            persistence.update(newEntity);
            CancionEntity resp = em.find(CancionEntity.class, entity.getId());
            assertEquals(newEntity.getName(), resp.getName());
        }
    }

    @Test
    public void testDelete() throws Exception {
        // TODO review the generated test code and remove the default call to fail.
        for (int i = 0; i < data.size(); i++) {
            CancionEntity entity = data.get(i);
            persistence.delete(entity.getId());
            CancionEntity deleted = em.find(CancionEntity.class, entity.getId());
            assertNull(deleted);
        }
    }

    @Test
    public void testFind() throws Exception {
        for (int i = 0; i < data.size(); i++) {
            CancionEntity entity = data.get(i);
            CancionEntity newEntity = (CancionEntity) persistence.find(entity.getId());
            assertNotNull(newEntity);
            assertEquals(entity.getName(), newEntity.getName());

        }
    }

    @Test
    public void testFindAll() throws Exception {
        List list = persistence.findAll();
        //ArrayList<CancionEntity> ar = (ArrayList<CancionEntity>) list;
        assertEquals(data.size(), list.size());
        for (Object ent : list) {
            boolean existe = false;
            for (CancionEntity entity : data) {
                if ( ((CancionEntity)ent).getId().equals(entity.getId())) {

                    existe = true;
                }
            }
            assertTrue(existe);
        }
    }

   
    }
    
    
    
    
    
    
    
    
       


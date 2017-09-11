/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
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
 * @author Julian
 */
@RunWith(Arquillian.class)
public class TarjetaPersistenceTest {
    
    /**
     * Inyección de la dependencia a la clase TarjetaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private TarjetaPersistence persistence;

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
    private List<TarjetaEntity> data = new ArrayList<TarjetaEntity>();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de XYZ, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaEntity.class.getPackage())
                .addPackage(TarjetaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public TarjetaPersistenceTest() {
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
    public void testCreate() throws Exception {
        // TODO review the generated test code and remove the default call to fail.
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaEntity newEntity = factory.manufacturePojo(TarjetaEntity.class);
        TarjetaEntity result = (TarjetaEntity) persistence.create(newEntity);
        assertNotNull(result);
        TarjetaEntity entity = em.find(TarjetaEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(entity.getName(), result.getName());
    }

    @Test
    public void testUpdate() throws Exception {
        // TODO review the generated test code and remove the default call to fail.
        for (int i = 0; i < data.size(); i++) {
            TarjetaEntity entity = data.get(i);

            PodamFactory factory = new PodamFactoryImpl();
            TarjetaEntity newEntity = factory.manufacturePojo(TarjetaEntity.class);
            newEntity.setId(entity.getId());
            persistence.update(newEntity);
            TarjetaEntity resp = em.find(TarjetaEntity.class, entity.getId());
            assertEquals(newEntity.getName(), resp.getName());
        }
    }

    @Test
    public void testDelete() throws Exception {
        // TODO review the generated test code and remove the default call to fail.
        for (int i = 0; i < data.size(); i++) {
            TarjetaEntity entity = data.get(i);
            persistence.delete(entity.getId());
            TarjetaEntity deleted = em.find(TarjetaEntity.class, entity.getId());
            assertNull(deleted);
        }
    }

    @Test
    public void testFind() throws Exception {
        for (int i = 0; i < data.size(); i++) {
            TarjetaEntity entity = data.get(i);
            TarjetaEntity newEntity = (TarjetaEntity) persistence.find(entity.getId());
            assertNotNull(newEntity);
            assertEquals(entity.getName(), newEntity.getName());

        }
    }

    @Test
    public void testFindAll() throws Exception {
        List list = persistence.findAll();
        ArrayList<TarjetaEntity> ar = (ArrayList<TarjetaEntity>) list;
        assertEquals(data.size(), list.size());
        for (TarjetaEntity ent : ar) {
            boolean existe = false;
            for (TarjetaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {

                    existe = true;
                }
            }
            assertTrue(existe);
        }
    }

    private void clearData() {
        em.createQuery("delete from TarjetaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TarjetaEntity entity = factory.manufacturePojo(TarjetaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

}

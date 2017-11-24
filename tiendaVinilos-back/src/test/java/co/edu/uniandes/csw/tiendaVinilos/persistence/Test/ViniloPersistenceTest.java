
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence.Test;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.ViniloPersistence;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.Assert.*;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.UserTransaction;

/**
 *
 * @author jp.monsalvo
 */
@RunWith(Arquillian.class)
public class ViniloPersistenceTest {

    /**
     *
     */
    private List<ViniloEntity> data = new ArrayList<ViniloEntity>();

    /**
     * Inyección de la dependencia a la clase ViniloPersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    private ViniloPersistence persistence;

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

    public ViniloPersistenceTest() {}

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Vinilo, el descriptor de la base
     * de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(ViniloEntity.class.getPackage()).addPackage(
            ViniloPersistence.class.getPackage()).addAsManifestResource(
            "META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @BeforeClass
    public static void setUpClass() {}

    @AfterClass
    public static void tearDownClass() {}

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
        em.createQuery("delete from ViniloEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        for (int i = 0; i < 3; i++) {
            ViniloEntity entity = factory.manufacturePojo(ViniloEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createViniloEntityTest() {
        PodamFactory factory   = new PodamFactoryImpl();
        ViniloEntity newEntity = factory.manufacturePojo(ViniloEntity.class);
        ViniloEntity result    = persistence.create(newEntity);

        Assert.assertNotNull(result);

        ViniloEntity entity = em.find(ViniloEntity.class, result.getId());

        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    @Test
    public void getVinilosTest() {
        List<ViniloEntity> list = persistence.findAll();

        Assert.assertEquals(data.size(), list.size());

        for (ViniloEntity ent : list) {
            boolean found = false;

            for (ViniloEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }

            Assert.assertTrue(found);
        }
    }

    @Test
    public void getViniloTest() {
        ViniloEntity entity    = data.get(0);
        ViniloEntity newEntity = persistence.find(entity.getId());

        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void getViniloByNameTest() {
        ViniloEntity entity    = data.get(0);
        ViniloEntity newEntity = persistence.findByName(entity.getName());

        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void updateViniloTest() {
        ViniloEntity entity    = data.get(0);
        PodamFactory factory   = new PodamFactoryImpl();
        ViniloEntity newEntity = factory.manufacturePojo(ViniloEntity.class);

        newEntity.setId(entity.getId());
        persistence.update(newEntity);

        ViniloEntity resp = em.find(ViniloEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    @Test
    public void deleteViniloTest() {
        ViniloEntity entity = data.get(0);

        persistence.delete(entity.getId());

        ViniloEntity deleted = em.find(ViniloEntity.class, entity.getId());

        Assert.assertNull(deleted);
    }

    @After
    public void tearDown() {}

    /**
     * Test of create method, of class ViniloPersistence.
     */
    @Test
    public void testCreate() throws Exception {}

    /**
     * Test of update method, of class ViniloPersistence.
     */
    @Test
    public void testUpdate() throws Exception {}

    /**
     * Test of delete method, of class ViniloPersistence.
     */
    @Test
    public void testDelete() throws Exception {}

    /**
     * Test of find method, of class ViniloPersistence.
     */
    @Test
    public void testFind() throws Exception {}

    /**
     * Test of findAll method, of class ViniloPersistence.
     */
    @Test
    public void testFindAll() throws Exception {}

    /**
     * Test of findByName method, of class ViniloPersistence.
     */
    @Test
    public void testFindByName() throws Exception {}
}


//~ Formatted by Jindent --- http://www.jindent.com

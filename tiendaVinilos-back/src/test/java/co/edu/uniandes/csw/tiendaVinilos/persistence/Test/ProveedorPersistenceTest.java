
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence.Test;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.ProveedorPersistence;

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
 * @author jc.ruiz
 */
@RunWith(Arquillian.class)
public class ProveedorPersistenceTest {
    private List<ProveedorEntity> data = new ArrayList<ProveedorEntity>();
    @Inject
    private ProveedorPersistence  persistence;
    @PersistenceContext
    private EntityManager         em;
    @Inject
    UserTransaction               utx;

    public ProveedorPersistenceTest() {}

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(ProveedorEntity.class.getPackage()).addPackage(
            ProveedorPersistence.class.getPackage()).addAsManifestResource(
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
        em.createQuery("delete from ProveedorEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        for (int i = 0; i < 3; i++) {
            ProveedorEntity entity = factory.manufacturePojo(ProveedorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @After
    public void tearDown() {}

    /**
     * Test of create method, of class ProveedorPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory    factory   = new PodamFactoryImpl();
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);
        ProveedorEntity result    = persistence.create(newEntity);

        Assert.assertNotNull(result);

        ProveedorEntity entity = em.find(ProveedorEntity.class, result.getId());

        Assert.assertNotNull(entity);
        Assert.assertEquals(entity.getName(), result.getName());
    }

    /**
     * Test of update method, of class ProveedorPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        ProveedorEntity entity    = data.get(0);
        PodamFactory    factory   = new PodamFactoryImpl();
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);

        newEntity.setId(entity.getId());
        persistence.update(newEntity);

        ProveedorEntity resp = em.find(ProveedorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of find method, of class ProveedorPersistence.
     */
    @Test
    public void testFind() throws Exception {
        ProveedorEntity entity    = data.get(0);
        ProveedorEntity newEntity = persistence.find(entity.getId());

        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of delete method, of class ProveedorPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        ProveedorEntity entity = data.get(0);

        persistence.delete(entity.getId());

        ProveedorEntity deleted = em.find(ProveedorEntity.class, entity.getId());

        Assert.assertNull(deleted);
    }

    /**
     * Test of findAll method, of class ProveedorPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<ProveedorEntity> list = persistence.findAll();

        Assert.assertEquals(data.size(), list.size());

        for (ProveedorEntity ent : list) {
            boolean found = false;

            for (ProveedorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }

            Assert.assertTrue(found);
        }
    }

    @Test
    public void testFindByEmail() throws Exception {
        ProveedorEntity entity = data.get(0);
        ProveedorEntity result = persistence.findByEmail(entity.getEmail());

        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getName(), result.getName());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence.Test;

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.FeedBackPersistence;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jc.ruiz
 */
@RunWith(Arquillian.class)
public class FeedBackPersistenceTest {
    
     @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FeedBackEntity.class.getPackage())
                .addPackage(FeedBackPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private FeedBackPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<FeedBackEntity> data = new ArrayList<FeedBackEntity>();
   
    public FeedBackPersistenceTest() {
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
         
        em.createQuery("delete from FeedBackEntity").executeUpdate();
     }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FeedBackEntity entity = factory.manufacturePojo(FeedBackEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class FeedBackPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        FeedBackEntity newEntity = factory.manufacturePojo (FeedBackEntity.class);
        FeedBackEntity result = persistence.create(newEntity);
        Assert.assertNotNull(result);
        FeedBackEntity entity = em.find(FeedBackEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(entity.getComentario(), result.getComentario());
    }

    /**
     * Test of update method, of class FeedBackPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
    FeedBackEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    FeedBackEntity newEntity = factory.manufacturePojo(FeedBackEntity.class);
    newEntity.setId(entity.getId());
    persistence.update(newEntity);
    
    FeedBackEntity resp = em.find(FeedBackEntity.class, entity.getId());
    Assert.assertEquals(newEntity.getId(), resp.getId());
    Assert.assertEquals(newEntity.getComentario(), resp.getComentario());
    }

    /**
     * Test of find method, of class FeedBackPersistence.
     */
    @Test
    public void testFind() throws Exception {
        FeedBackEntity entity = data.get(0);
    FeedBackEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getId(), newEntity.getId());
    Assert.assertEquals(entity.getComentario(), newEntity.getComentario());
    }
    
    /**
     * Test of delete method, of class FeedBackPersistence.
     */
    @Test
    public void testDelete() throws Exception {
     FeedBackEntity entity = data.get(0);
    persistence.delete(entity.getId());
    FeedBackEntity deleted = em.find(FeedBackEntity.class, entity.getId());
    Assert.assertNull(deleted);
    }

    /**
     * Test of findAll method, of class FeedBackPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
         List<FeedBackEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (FeedBackEntity ent : list) {
        boolean found = false;
        for (FeedBackEntity entity : data) {
            if(ent.getId() == entity.getId()) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }
    }
    
}

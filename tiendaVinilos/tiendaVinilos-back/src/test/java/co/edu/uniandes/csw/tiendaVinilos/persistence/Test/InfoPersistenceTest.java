/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence.Test;

import co.edu.uniandes.csw.tiendaVinilos.entities.InfoEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.InfoPersistence;
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
 * @author jp.monsalvo
 */
@RunWith(Arquillian.class)
public class InfoPersistenceTest {
    
        /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Vinilo, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InfoEntity.class.getPackage())
                .addPackage(InfoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase InfoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private InfoPersistence persistence;

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
    private List<InfoEntity> data = new ArrayList<InfoEntity>();
    
    public InfoPersistenceTest() {
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
        em.createQuery("delete from InfoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            InfoEntity entity = factory.manufacturePojo(InfoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
     public void createInfoEntityTest() {
    PodamFactory factory = new PodamFactoryImpl();
    InfoEntity newEntity = factory.manufacturePojo(InfoEntity.class);
    InfoEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    InfoEntity entity = em.find(InfoEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
}
     @Test
public void getInfosTest() {
    List<InfoEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (InfoEntity ent : list) {
        boolean found = false;
        for (InfoEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }
}

@Test
public void getInfoTest() {
    InfoEntity entity = data.get(0);
    InfoEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
}
@Test
public void getInfoByNameTest() {
    InfoEntity entity = data.get(0);
    InfoEntity newEntity = persistence.findByName(entity.getName());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
}
@Test
public void updateInfoTest() {
    InfoEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    InfoEntity newEntity = factory.manufacturePojo(InfoEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    InfoEntity resp = em.find(InfoEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getName(), resp.getName());
}

@Test
public void testDelete() throws Exception {
    InfoEntity entity = data.get(0);
    persistence.delete(entity.getId());
    InfoEntity deleted = em.find(InfoEntity.class, entity.getId());
    Assert.assertNull(deleted);
}

     
    @AfterClass
    public static void tearDownClass() {
    }
    
  
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class InfoPersistence.
     */
    @Test
    public void testCreate() {
    }

    /**
     * Test of update method, of class InfoPersistence.
     */
    @Test
    public void testUpdate() {
    }

    /**
     * Test of delete method, of class InfoPersistence.
     */
   // @Test
    //public void testDelete() {
    //}

    /**
     * Test of find method, of class InfoPersistence.
     */
    @Test
    public void testFind() {
    }

    /**
     * Test of findAll method, of class InfoPersistence.
     */
    @Test
    public void testFindAll() {
    }

    /**
     * Test of findByName method, of class InfoPersistence.
     */
    @Test
    public void testFindByName() {
    }
    
}

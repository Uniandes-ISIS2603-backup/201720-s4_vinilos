/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoProveedorEntity;
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
 * @author s.saenz11
 */
@RunWith(Arquillian.class)
public class PedidoProveedorPersistenceTest {
    
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
                .addPackage(PedidoProveedorEntity.class.getPackage())
                .addPackage(PedidoProveedorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private PedidoProveedorPersistence persistence;

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
    private List<PedidoProveedorEntity> data = new ArrayList<PedidoProveedorEntity>();
    
    public PedidoProveedorPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    private void clearData() {
        em.createQuery("delete from PedidoProveedorEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PedidoProveedorEntity entity = factory.manufacturePojo(PedidoProveedorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
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
     * Test of find method, of class PedidoProveedorPersistence.
     */
    @Test
    public void testFind() throws Exception {
         PedidoProveedorEntity entity = data.get(0);
    PedidoProveedorEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of findAll method, of class PedidoProveedorPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
       List<PedidoProveedorEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (PedidoProveedorEntity ent : list) {
        boolean found = false;
        for (PedidoProveedorEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }
    }

    /**
     * Test of create method, of class PedidoProveedorPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
    PedidoProveedorEntity newEntity = factory.manufacturePojo(PedidoProveedorEntity.class);
    PedidoProveedorEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    PedidoProveedorEntity entity = em.find(PedidoProveedorEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class PedidoProveedorPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
         PedidoProveedorEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    PedidoProveedorEntity newEntity = factory.manufacturePojo(PedidoProveedorEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    PedidoProveedorEntity resp = em.find(PedidoProveedorEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class PedidoProveedorPersistence.
     */
    @Test
    public void testDelete() throws Exception {
         PedidoProveedorEntity entity = data.get(0);
    persistence.delete(entity.getId());
    PedidoProveedorEntity deleted = em.find(PedidoProveedorEntity.class, entity.getId());
    Assert.assertNull(deleted);
    }
    
}

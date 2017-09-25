/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
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
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author mj.jaime10
 */
@RunWith(Arquillian.class)
public class PedidoClientePersistenceTest 
{
    /**
     * Inyección de la dependencia a la clase PedidoClientePersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private PedidoClientePersistence persistence;
    
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
    private List<PedidoClienteEntity> data = new ArrayList<PedidoClienteEntity>();
    
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
                .addPackage(PedidoClienteEntity.class.getPackage())
                .addPackage(PedidoClientePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public PedidoClientePersistenceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @Before
    public void setUp() 
    {
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
        em.createQuery("delete from PedidoClienteEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PedidoClienteEntity entity = factory.manufacturePojo(PedidoClienteEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createPedidoClienteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PedidoClienteEntity newEntity = factory.manufacturePojo(PedidoClienteEntity.class);
        PedidoClienteEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        PedidoClienteEntity entity = em.find(PedidoClienteEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
     /**
     * Test of find method, of class PedidoClientePersistence.
     */
    @Test
    public void getPedidoClienteTest() {
        PedidoClienteEntity entity = data.get(0);
        PedidoClienteEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test
    public void getPedidoClientesTest() {
        List<PedidoClienteEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PedidoClienteEntity ent : list) {
            boolean found = false;
            for (PedidoClienteEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Test of update method, of class PedidoClienteersistence.
     */
    @Test
    public void updatePedidoClienteTest() {
        PedidoClienteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PedidoClienteEntity newEntity = factory.manufacturePojo(PedidoClienteEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        PedidoClienteEntity resp = em.find(PedidoClienteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
    /**
     * Test of delete method, of class PedidoClienteersistence.
     */
    @Test
    public void deletePedidoClienteTest() {
        PedidoClienteEntity entity = data.get(0);
        persistence.delete(entity.getId());
        PedidoClienteEntity deleted = em.find(PedidoClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }       
}


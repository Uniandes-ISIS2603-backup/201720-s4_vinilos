
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence.Test;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.PagoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.persistence.PagoClientePersistence;

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

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.UserTransaction;

/**
 *
 * @author mj.jaime10
 */
@RunWith(Arquillian.class)
public class PagoClientePersistenceTest {

    /**
     *
     */
    private List<PagoClienteEntity> data = new ArrayList<PagoClienteEntity>();

    /**
     * Inyección de la dependencia a la clase PedidoClientePersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private PagoClientePersistence persistence;

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

    public PagoClientePersistenceTest() {}

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de XYZ, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(PagoClienteEntity.class.getPackage()).addPackage(
            PagoClientePersistence.class.getPackage()).addAsManifestResource(
            "META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @BeforeClass
    public static void setUpClass() {}

    @AfterClass
    public static void tearDownClass() {}

    /**
     *
     */
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
        em.createQuery("delete from PagoClienteEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        for (int i = 0; i < 3; i++) {
            PagoClienteEntity entity = factory.manufacturePojo(PagoClienteEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @After
    public void tearDown() {}

    @Test
    public void createPagoClienteTest() {
        PodamFactory      factory   = new PodamFactoryImpl();
        PagoClienteEntity newEntity = factory.manufacturePojo(PagoClienteEntity.class);
        PagoClienteEntity result    = persistence.create(newEntity);

        Assert.assertNotNull(result);

        PagoClienteEntity entity = em.find(PagoClienteEntity.class, result.getId());

        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of find method, of class PedidoClientePersistence.
     */
    @Test
    public void getPagoClienteTest() {
        PagoClienteEntity entity    = data.get(0);
        PagoClienteEntity newEntity = persistence.find(entity.getId());

        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void getPagoClientesTest() {
        List<PagoClienteEntity> list = persistence.findAll();

        Assert.assertEquals(data.size(), list.size());

        for (PagoClienteEntity ent : list) {
            boolean found = false;

            for (PagoClienteEntity entity : data) {
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
    public void updatePagoClienteTest() {
        PagoClienteEntity entity    = data.get(0);
        PodamFactory      factory   = new PodamFactoryImpl();
        PagoClienteEntity newEntity = factory.manufacturePojo(PagoClienteEntity.class);

        newEntity.setId(entity.getId());
        persistence.update(newEntity);

        PagoClienteEntity resp = em.find(PagoClienteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class PedidoClienteersistence.
     */
    @Test
    public void deletePagoClienteTest() {
        PagoClienteEntity entity = data.get(0);

        persistence.delete(entity.getId());

        PagoClienteEntity deleted = em.find(PagoClienteEntity.class, entity.getId());

        Assert.assertNull(deleted);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

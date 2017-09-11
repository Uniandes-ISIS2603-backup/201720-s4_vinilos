/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.persistence;

import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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
import static org.junit.Assert.*;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import java.util.*;
import javax.transaction.UserTransaction;

/**
 *
 * @author jd.arenas
 */
public class UsuarioPersistenceTest {

    /**
     * Inyección de la dependencia a la clase UsuarioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private UsuarioPersistence persistence;

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
    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de XYZ, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public UsuarioPersistenceTest() {
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
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity result = (UsuarioEntity) persistence.create(newEntity);
        assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(entity.getName(), result.getName());
    }

    @Test
    public void testUpdate() throws Exception {
        // TODO review the generated test code and remove the default call to fail.
        UsuarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setId(entity.getId());
        persistence.update(newEntity);

        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
        assertEquals(newEntity.getName(), resp.getName());
    }

    @Test
    public void testDelete() throws Exception {
        // TODO review the generated test code and remove the default call to fail.
        for (int i = 0; i < data.size(); i++) {
            UsuarioEntity entity = data.get(i);
            persistence.delete(entity.getId());
            UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
            assertNull(deleted);
        }
    }

    @Test
    public void testFind() throws Exception {
        for (int i = 0; i < data.size(); i++) {
            UsuarioEntity entity = data.get(i);
            UsuarioEntity newEntity = (UsuarioEntity) persistence.find(entity.getId());
            assertNotNull(newEntity);
            assertEquals(entity.getName(), newEntity.getName());

        }
    }

    @Test
    public void testFindAll() throws Exception {
        List list = persistence.findAll();
        ArrayList<UsuarioEntity> ar=(ArrayList<UsuarioEntity>)list;
        assertEquals(data.size(), list.size());
        for (UsuarioEntity ent : ar) {
            boolean existe = false;
            for (UsuarioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    
                    existe= true;
                }
            }
            assertTrue(existe);
            
        }
    }

    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

}

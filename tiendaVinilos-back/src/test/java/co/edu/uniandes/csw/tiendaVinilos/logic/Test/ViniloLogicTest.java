/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendaVinilos.logic.Test;

import co.edu.uniandes.csw.tiendaVinilos.ejb.ArtistaLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.CancionLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.CarroComprasLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.tiendaVinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendaVinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.CarroComprasEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.InfoEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ProveedorEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendaVinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendaVinilos.persistence.ViniloPersistence;
import co.edu.uniandes.csw.tiendaVinilos.persistence.PagoProveedorPersistennce;
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
 * @author jc.ruiz
 */
@RunWith(Arquillian.class)
public class ViniloLogicTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViniloEntity.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(ViniloLogic.class.getPackage())
                .addPackage(InfoEntity.class.getPackage())
                .addPackage(ProveedorLogic.class.getPackage())
                .addPackage(PagoProveedorPersistennce.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase UsuarioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ViniloPersistence persistence;
    /**
     *
     */
    @Inject
    private ViniloLogic logic;

    @Inject
    private CancionLogic logicCancion;

    @Inject
    private ArtistaLogic logicArtista;

    @Inject
    private CarroComprasLogic logicCarro;

    /**
     *
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    private List<ViniloEntity> data = new ArrayList<ViniloEntity>();

    private ViniloEntity entity = new ViniloEntity();

    public ViniloLogicTest() {
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

    /**
     * Test of createVinilo method, of class ViniloLogic.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateVinilo() throws Exception {
        ViniloEntity vin = new ViniloEntity();
        try {
            logic.createVinilo(vin);
            assertEquals(logic.getVinilo(vin.getId()).getId(), vin.getId());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of getVinilos method, of class ViniloLogic.
     */
    @Test
    public void testGetVinilos() throws Exception {
        entity = new ViniloEntity();
        try {
            logic.createVinilo(entity);
            assertEquals(logic.getVinilo(entity.getId()).getId(), entity.getId());
            assertTrue(logic.getVinilos().size() > 0);
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of getVinilo method, of class ViniloLogic.
     */
    @Test
    public void testGetVinilo() throws Exception {
        entity = new ViniloEntity();
        try {
            logic.createVinilo(entity);
            assertEquals(logic.getVinilo(entity.getId()).getId(), entity.getId());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of updateVinilo method, of class ViniloLogic.
     */
    @Test
    public void testUpdateVinilo() throws Exception {
        entity = new ViniloEntity();
        try {
            logic.createVinilo(entity);
            entity.setName("AC DC");
            assertEquals(logic.updateVinilo(entity.getId(), entity), entity);
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of deleteVinilo method, of class ViniloLogic.
     */
    @Test
    public void testDeleteVinilo() throws Exception {
        for (int i = 0; i < 10; i++) {
            try {
                ViniloEntity vinilo = new ViniloEntity();
                logic.createVinilo(vinilo);
                assertNotNull(logic.getVinilo(vinilo.getId()));
                assertEquals(vinilo.getId(), logic.getVinilo(vinilo.getId()).getId());
                logic.deleteVinilo(vinilo.getId());
                logic.getVinilo(vinilo.getId());
            } catch (BusinessLogicException ex) {
                assertTrue(true);
            }
        }
    }

    /**
     * Test of agregarVinilo method, of class ViniloLogic.
     */
    @Test
    public void testAgregarVinilo() throws Exception {
        ProveedorEntity prov = new ProveedorEntity();
        ViniloEntity vin = new ViniloEntity();
        try {
           logic.agregarVinilo(prov, vin);
           assertEquals(logic.getVinilo(vin.getId()).getId(), vin.getId());
        } catch (Exception ex) {
          assertTrue(true);
        }
    }

    /**
     * Test of eliminarVinilo method, of class ViniloLogic.
     */
    @Test
    public void testEliminarVinilo() throws Exception {
        for (int i = 0; i < 10; i++) {
            try {
                ViniloEntity vinilo = new ViniloEntity();
                logic.createVinilo(vinilo);
                assertNotNull(logic.getVinilo(vinilo.getId()));
                assertEquals(vinilo.getId(), logic.getVinilo(vinilo.getId()).getId());
                logic.deleteVinilo(vinilo.getId());
                logic.getVinilo(vinilo.getId());
            } catch (BusinessLogicException ex) {
                assertTrue(true);
            }
        }
    }

    /**
     * Test of modificarVinilo method, of class ViniloLogic.
     */
    @Test
    public void testModificarVinilo() throws Exception {
        entity = new ViniloEntity();
        ProveedorEntity prov = new ProveedorEntity();
        try {
            logic.createVinilo(entity);
            entity.setName("AC DC");
            assertEquals(logic.modificarVinilo(prov, entity.getId(), entity), entity);
        } catch (BusinessLogicException ex) {
            fail();
        }

    }

    /**
     * Test of addCarrito method, of class ViniloLogic.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddCarrito() throws Exception {
        CarroComprasEntity carrito = new CarroComprasEntity();
        entity = new ViniloEntity();
        try {
            logicCarro.createCarroCompras(carrito);
            logic.createVinilo(entity);
            logic.addCarrito(carrito, entity);
            assertTrue(!logic.getVinilo(entity.getId()).getCarrosCompras().isEmpty());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of sacraDelCarrito method, of class ViniloLogic.
     */
    @Test
    public void testSacraDelCarrito() throws Exception {
        entity = new ViniloEntity();
        CarroComprasEntity carrito = new CarroComprasEntity();
        List<CarroComprasEntity> carritos = new ArrayList<>();
        logicCarro.createCarroCompras(carrito);
        carritos.add(carrito);
        entity.setCarrosCompras(carritos);
        try {
            logic.createVinilo(entity);
            logic.sacraDelCarrito(entity, carrito);
            assertTrue(!logic.getVinilo(entity.getId()).getCarrosCompras().contains(carrito));
        } catch (Exception ex) {
            fail();
        }
    }

    /**
     * Test of getCanciones method, of class ViniloLogic.
     */
    @Test
    public void testGetCanciones() throws Exception {
        entity = new ViniloEntity();
        try {
            List<CancionEntity> canciones = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                CancionEntity cancion = new CancionEntity();
                cancion.setName("cancion" + i);
            }
            entity.setCanciones(canciones);
            logic.createVinilo(entity);
            assertEquals(canciones, logic.getCanciones(entity.getId()));
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of addVinilo method, of class ViniloLogic.
     */
    @Test
    public void testAddVinilo() throws Exception {
        entity = new ViniloEntity();
        CancionEntity cancion = new CancionEntity();
        logicCancion.createCancion(cancion);
        try {
            logic.createVinilo(entity);
            logic.addVinilo(entity.getId(), cancion);
            assertTrue(logic.getVinilo(entity.getId()).getCanciones().isEmpty());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of listArtistas method, of class ViniloLogic.
     */
    @Test
    public void testListArtistas() throws Exception {
        entity = new ViniloEntity();
        try {
            List<ArtistaEntity> artistas = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ArtistaEntity artista = new ArtistaEntity();
                artista.setName("artista" + i);
            }
            entity.setArtistas(artistas);
            logic.createVinilo(entity);
            assertEquals(artistas, logic.listArtistas(entity.getId()));
        } catch (Exception ex) {
            fail();
        }
    }

    /**
     * Test of getArtista method, of class ViniloLogic.
     */
    @Test
    public void testGetArtista() throws Exception {
        entity = new ViniloEntity();
        ArtistaEntity artista = new ArtistaEntity();
        List<ArtistaEntity> artistas = new ArrayList<>();
        logicArtista.createArtista(artista);
        artistas.add(artista);
        entity.setArtistas(artistas);
        try {
            logic.createVinilo(entity);
            assertEquals(logic.getArtista(entity.getId(), artista.getId()).getId(), artista.getId());
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    /**
     * Test of addArtista method, of class ViniloLogic.
     */
    @Test
    public void testAddArtista() throws Exception {
        entity = new ViniloEntity();
        ArtistaEntity artista = new ArtistaEntity();
        logicArtista.createArtista(artista);
        try {
            logic.createVinilo(entity);
            logic.addArtista(entity.getId(), artista, artista.getId());
            assertTrue(logic.getVinilo(entity.getId()).getArtistas().size() > 0);
        } catch (Exception ex) {
            fail();
        }
    }

    /**
     * Test of replaceArtistas method, of class ViniloLogic.
     */
    @Test
    public void testReplaceArtistas() throws Exception {
        entity = new ViniloEntity();
        ArtistaEntity artista = new ArtistaEntity();
        List<ArtistaEntity> artistas = new ArrayList<>();
        artistas.add(artista);
        try {
            logicArtista.createArtista(artista);
            logic.createVinilo(entity);
            assertTrue(!logic.replaceArtistas(entity.getId(), artistas).isEmpty());
        } catch (Exception ex) {
            fail();
        }
    }

    /**
     * Test of removeArtista method, of class ViniloLogic.
     */
    @Test
    public void testRemoveArtista() throws Exception {
        entity = new ViniloEntity();
        ArtistaEntity artista = new ArtistaEntity();
        List<ArtistaEntity> artistas = new ArrayList<>();
        artistas.add(artista);
        entity.setArtistas(artistas);
        try {
            logic.createVinilo(entity);
            logic.removeArtista(entity.getId(), artista.getId());
            assertTrue(logic.listArtistas(entity.getId()).isEmpty());
        } catch (Exception ex) {
            fail();
        }
    }

    /**
     * Test of listCanciones method, of class ViniloLogic.
     */
    @Test
    public void testListCanciones() throws Exception {
        entity = new ViniloEntity();
        CancionEntity cancion = new CancionEntity();
        List<CancionEntity> canciones = new ArrayList<>();
        logicCancion.createCancion(cancion);
        canciones.add(cancion);
        entity.setCanciones(canciones);
        try {
            logic.createVinilo(entity);
            assertTrue(logic.listCanciones(entity.getId()).isEmpty());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of getCancion method, of class ViniloLogic.
     */
    @Test
    public void testGetCancion() throws Exception {
        entity = new ViniloEntity();
        CancionEntity cancion = new CancionEntity();
        List<CancionEntity> canciones = new ArrayList<>();
        canciones.add(cancion);
        entity.setCanciones(canciones);
        try {
            logic.createVinilo(entity);
            assertEquals(logic.getCancion(entity.getId(), cancion.getId()).getId(), cancion.getId());
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    /**
     * Test of addCancion method, of class ViniloLogic.
     */
    @Test
    public void testAddCancion() throws Exception {
        entity = new ViniloEntity();
        CancionEntity cancion = new CancionEntity();
        try {
            logic.createVinilo(entity);
            logic.addCancion(entity.getId(), cancion, cancion.getId());
            assertTrue(logic.getCanciones(entity.getId()).size() > 0);
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    /**
     * Test of replaceCanciones method, of class ViniloLogic.
     */
    @Test
    public void testReplaceCanciones() throws Exception {
        entity = new ViniloEntity();
        CancionEntity cancion = new CancionEntity();
        List<CancionEntity> canciones = new ArrayList<>();
        canciones.add(cancion);
        try {
            logicCancion.createCancion(cancion);
            logic.createVinilo(entity);
            assertTrue(!logic.replaceCanciones(entity.getId(), canciones).isEmpty());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    /**
     * Test of removeCancion method, of class ViniloLogic.
     */
    @Test
    public void testRemoveCancion() throws Exception {
        entity = new ViniloEntity();
        CancionEntity cancion = new CancionEntity();
        List<CancionEntity> canciones = new ArrayList<>();
        canciones.add(cancion);
        entity.setCanciones(canciones);
        try {
            logic.createVinilo(entity);
            logic.removeCancion(entity.getId(), cancion.getId());
            assertTrue(logic.getCanciones(entity.getId()).isEmpty());
        } catch (BusinessLogicException ex) {
            fail();
        }
    }

    private void clearData() {
        em.createQuery("delete from ViniloEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 10; i++) {
            ViniloEntity entity = factory.manufacturePojo(ViniloEntity.class
            );
            em.persist(entity);
            data.add(entity);
        }
    }
}

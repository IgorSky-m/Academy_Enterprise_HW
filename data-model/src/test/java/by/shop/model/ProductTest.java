package by.shop.model;

import static org.junit.Assert.*;

import org.hibernate.*;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.*;

import javax.persistence.FlushModeType;
import java.sql.Date;


public class ProductTest {

    SessionFactory factory;
    String idFromDel;

    @Before
    public void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            factory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }

    }




    @Test
    public void create(){
        //Given:
        Product product = new Product();
        product.name = "ASUS notebook";
        product.productNumber="1234productNum";
        product.serialNumber="1135234SerialAsus";
        product.produceDate = Date.valueOf("2020-10-10");
        product.description = "this is a top notebook";
        String productId;
        Stock stock = new Stock();
        stock.value = 25;
        product.stock = stock;
        stock.product = product;

        //when
        Session sess = factory.openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();

            productId = (String) sess.save(product);

            tx.commit();

            Product readProduct = sess.get(Product.class,product.id);
            System.out.println(readProduct.stock.value);
            assertEquals(25,readProduct.stock.value);
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            throw e;
        }
        finally {
            sess.close();
        }

        idFromDel = productId;
        assertTrue(productId.length() >1);
        assertNotNull(product.id);

    }




    @Test
    public void addToTable(){
        //Given:
        Product product2 = new Product();
        product2.name = "ASUS notebook2";
        product2.productNumber="1234productNum2";
        product2.serialNumber="1135234SerialAsus2";
        product2.produceDate = Date.valueOf("2019-10-10");
        product2.description = "this is copy a top notebook";
        String productId;

        //when
        Session sess = factory.openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();

            sess.persist(product2);



            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            throw e;
        }
        finally {
            sess.close();
        }

     //   assertTrue(productId.length() >1);
        assertNotNull(product2.id);

    }

    @Test
    public void delete(){
        Product product = new Product();
        product.name = "ASER notebook";
        product.productNumber="212234productNum";
        product.serialNumber="1231135234SerialAsus";
        product.produceDate = Date.valueOf("2010-10-10");
        product.description = "this is not a top notebook";
        String productId;
        Product productFromDel;
        Session session = factory.openSession();
        Transaction transaction =null;
        try{
            transaction=session.beginTransaction();
            productId = (String)session.save(product);
            productFromDel=session.get(Product.class,productId);
            assertEquals(product.id,productFromDel.id);
            session.delete(productFromDel);
            assertThrows(ObjectNotFoundException.class,()-> session.load(Product.class,productFromDel.id));

            transaction.commit();
        } catch (Exception e){
            if(transaction !=null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }



    };

    @After
    public void tearDown() throws Exception {

        if (!factory.isClosed()) {
            factory.close();
            factory =null;
        }
    }


}
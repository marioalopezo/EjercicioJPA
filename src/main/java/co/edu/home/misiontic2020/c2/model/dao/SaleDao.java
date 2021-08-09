package co.edu.home.misiontic2020.c2.model.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.home.misiontic2020.c2.model.entity.Sale;

public class SaleDao {

    private EntityManagerFactory emf = null;

    public SaleDao() {
        emf = Persistence.createEntityManagerFactory("bookstore-jpa-pu");
    }

    public void saveSale(Sale sale) {
        var em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(sale);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}

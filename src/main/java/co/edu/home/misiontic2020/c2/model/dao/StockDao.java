package co.edu.home.misiontic2020.c2.model.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.home.misiontic2020.c2.model.entity.Stock;

public class StockDao {

    private EntityManagerFactory emf = null;

    public StockDao() {
        emf = Persistence.createEntityManagerFactory("bookstore-jpa-pu");
    }

    public void saveStock(Stock stock) {
        var em = emf.createEntityManager();
        var newStock = em.find(Stock.class, stock.getBook().getId());
        if (newStock != null) {
            newStock.setAmount(stock.getAmount());
            stock = newStock;
        }
        try {
            em.getTransaction().begin();
            em.persist(stock);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public int getStock(int bookId) {
        var em = emf.createEntityManager();
        var stockBook = em.find(Stock.class, bookId);
        em.close();
        return stockBook != null ? (Integer) stockBook.getAmount() : 0;
    }

}

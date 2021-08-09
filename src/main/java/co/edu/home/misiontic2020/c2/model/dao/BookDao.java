package co.edu.home.misiontic2020.c2.model.dao;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.home.misiontic2020.c2.model.entity.Book;

public class BookDao {

    private EntityManagerFactory emf = null;

    public BookDao() {
        emf = Persistence.createEntityManagerFactory("bookstore-jpa-pu");
    }

    public void saveBook(Book book) {
        var em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Book findISBN(String isbn) {
        Book book = null;
        var em = emf.createEntityManager();
        try {
            var query = em.createQuery("SELECT e FROM Book e WHERE e.isbn = '" + isbn + "'");
            book = (Book) query.getSingleResult();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
        return book;
    }

    public Book findById(int book_id) {
        var em = emf.createEntityManager();
        var book = em.find(Book.class, book_id);
        em.close();
        return book;
    }

    public List<Book> getBooks() {
        List<Book> books = null;
        var em = emf.createEntityManager();
        try {
            var query = em.createQuery("SELECT e FROM Book e", Book.class);
            books = query.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
        return books;
    }

}
package co.edu.home.misiontic2020.c2.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import co.edu.home.misiontic2020.c2.model.dao.BookDao;
import co.edu.home.misiontic2020.c2.model.dao.StockDao;
import co.edu.home.misiontic2020.c2.model.dao.SaleDao;
import co.edu.home.misiontic2020.c2.model.entity.Book;
import co.edu.home.misiontic2020.c2.model.entity.Stock;
import co.edu.home.misiontic2020.c2.model.entity.Sale;

public class BookshopController {

    private BookDao bookDao;
    private SaleDao saleDao;
    private StockDao stockDao;

    public BookshopController() {
        bookDao = new BookDao();
        saleDao = new SaleDao();
        stockDao = new StockDao();
    }

    /**
     * Saves a book in the Book table.
     *
     * @param book The book object to be saved.
     * @throws SQLException If somthing fails with the DB.
     */

    public void saveBook(Book book) throws SQLException {
        bookDao.saveBook(book);
    }

    /**
     * Return the number of units in stock of the given book.
     *
     * @param book The book object.
     * @return The number of units in stock, or 0 if the book does not exist in the
     *         database.
     * @throws SQLException If somthing fails with the DB.
     */

    public int getStock(Book book) throws SQLException {
        return getStock(book.getId());
    }

    /**
     * Return the number of units in stock of the given book.
     *
     * @param bookId The book identifier in the database.
     * @return The number of units in stock, or 0 if the book does not exist in the
     *         database.
     */
    public int getStock(int bookId) throws SQLException {

        return stockDao.getStock(bookId);
    }

    /**
     * Search book by ISBN.
     *
     * @param isbn The ISBN of the book.
     * @return The Book object, or null if not found.
     * @throws SQLException If somthing fails with the DB.
     */
    public Book searchBook(String isbn) throws SQLException {
        return bookDao.findISBN(isbn);
    }

    /**
     * Sell a book.
     *
     * @param book  The book.
     * @param units Number of units that are being sold.
     * @return True if the operation succeeds, or false otherwise (e.g. when the
     *         stock of the book is not big enough).
     * @throws SQLException If somthing fails with the DB.
     */
    public boolean sellBook(Book book, int units) throws SQLException {
        return sellBook(book.getId(), units);
    }

    /**
     * Sell a book.
     *
     * @param book  The book's identifier.
     * @param units Number of units that are being sold.
     * @return True if the operation succeeds, or false otherwise (e.g. when the
     *         stock of the book is not big enough).
     * @throws SQLException If something fails with the DB.
     */
    public boolean sellBook(int book_id, int units) throws SQLException {

        var new_stock = getStock(book_id) - units;

        if (units == 0 || new_stock < 0)
            return false;

        var venta = new Sale(Date.valueOf(LocalDate.now()), units, bookDao.findById(book_id));
        var nuevoInventario = new Stock(bookDao.findById(book_id), new_stock);

        saleDao.saveSale(venta);
        stockDao.saveStock(nuevoInventario);

        return true;
    }

    /**
     * Return a list with all the books in the database.
     *
     * @return List with all the books.
     * @throws SQLException If something fails with the DB.
     */
    public List<Book> listBooks() throws SQLException {
        List<Book> books = bookDao.getBooks();
        return books;
    }

    /**
     * Saves the stock of each book in the Stock table.
     *
     * @param book The book object to be saved.
     * @param cant The amount of books that are in the stock
     * @throws SQLException If somthing fails with the DB.
     */

    public void inventarioInicial(Book book, int cant) {
        stockDao.saveStock(new Stock(book, cant));
    }

}

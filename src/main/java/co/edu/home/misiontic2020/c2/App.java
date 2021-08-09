package co.edu.home.misiontic2020.c2;

import java.sql.SQLException;

import co.edu.home.misiontic2020.c2.controller.BookshopController;
import co.edu.home.misiontic2020.c2.model.entity.Book;
import co.edu.home.misiontic2020.c2.view.BookShop;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws SQLException {

        //setBD();
        new BookShop().start();
    }

    public static void setBD() throws SQLException {
        var controller = new BookshopController();

        var book1 = new Book("AAA", "11111", 2020);
        var book2 = new Book("bbb", "22222", 2000);
        var book3 = new Book("CCC", "33333", 1990);

        controller.saveBook(book1);
        controller.saveBook(book2);
        controller.saveBook(book3);

        controller.inventarioInicial(book1, 10);
        controller.inventarioInicial(book2, 20);
        controller.inventarioInicial(book3, 13);
    }
}

package co.edu.home.misiontic2020.c2.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
public class Book implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Título", nullable = false, length = 100)
    private String title;

    @Column(name = "ISBN", nullable = false, length = 13)
    private String isbn;

    @Column(name = "Año", nullable = false)
    private int year;

    public Book() {
    }

    public Book(String title, String isbn, int year) {
        this.title = title;
        this.isbn = isbn;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", year=" + year + "]";
    }

}
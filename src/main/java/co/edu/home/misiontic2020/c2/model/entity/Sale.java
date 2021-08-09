package co.edu.home.misiontic2020.c2.model.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Sales")
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "Fecha_Venta", nullable = false)
    Date saleDate;

    @Column(name = "Unidades", nullable = false)
    int units;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Book book;

    public Sale() {
    }

    public Sale(Date saleDate, int units, Book book) {
        this.saleDate = saleDate;
        this.units = units;
        this.book = book;
    }

}
package com.crud.kodillalibrary.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Integer id;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_COPY")
    public BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "READER")
    private Reader reader;

    @Column(name = "DATEFROM")
    private Date dateFrom;

    @Column(name = "DATETO")
    private Date dateTo;

    public Rental(BookCopy bookCopy, Reader reader, Date dateFrom, Date dateTo) {
        this.bookCopy = bookCopy;
        this.reader = reader;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", bookCopy=" + bookCopy.getId() +
                ", reader=" + reader.getId() +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}

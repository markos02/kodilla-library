package com.crud.kodillalibrary.library.domain;

import com.crud.kodillalibrary.library.Status;
import com.crud.kodillalibrary.library.controller.BookNotAvailableException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "titles")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "RELEASE_DATE")
    private int releaseDate;

    @OneToMany(
            targetEntity = BookCopy.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<BookCopy> bookCopies = new ArrayList<>();

    public void setBookCopies(List<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }

    public Title(String title, String author, int releaseDate) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    public Integer findAvailableCopy() throws BookNotAvailableException {
        for (BookCopy copy : bookCopies) {
            if (copy.getStatus() == Status.AVAILABLE) {
                return copy.getId();
            }
        }
        throw new BookNotAvailableException();
    }
}

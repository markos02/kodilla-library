package com.crud.kodillalibrary.library.domain;

import com.crud.kodillalibrary.library.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "book_copies")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "TITLE")
    public Title title;

    @Column(name = "STATUS")
    private Status status;

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BookCopy(Status status) {
        this.status = status;
    }

}

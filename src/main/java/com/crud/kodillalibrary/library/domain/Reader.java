package com.crud.kodillalibrary.library.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "readers")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Integer id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @NotNull
    @Column(name = "CREATED")
    private Date created;

    @OneToMany(
            targetEntity = Rental.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Rental> rentals = new ArrayList<>();

    public Reader(String firstName, String lastName, Date created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = created;
    }
}

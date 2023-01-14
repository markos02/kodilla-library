package com.crud.kodillalibrary.library.domain;

import com.crud.kodillalibrary.library.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
public class BookCopyDto {
    private Integer id;
    private Title title;
    private Status status;
}

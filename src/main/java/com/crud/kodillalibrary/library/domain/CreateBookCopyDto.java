package com.crud.kodillalibrary.library.domain;

import com.crud.kodillalibrary.library.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateBookCopyDto {
    private Integer titleId;
    private Status status;
}

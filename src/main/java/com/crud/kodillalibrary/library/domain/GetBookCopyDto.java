package com.crud.kodillalibrary.library.domain;

import com.crud.kodillalibrary.library.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetBookCopyDto {
    private Integer id;
    private GetTitleDto getTitleDto;
    private Status status;
}

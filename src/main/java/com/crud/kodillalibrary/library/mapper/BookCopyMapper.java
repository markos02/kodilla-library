package com.crud.kodillalibrary.library.mapper;

import com.crud.kodillalibrary.library.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookCopyMapper {

    private TitleMapper titleMapper;

    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getTitle(),
                bookCopyDto.getStatus()

        );
    }

    public BookCopyDto mapToBookCopyDto(BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getTitle(),
                bookCopy.getStatus()

        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(this::mapToBookCopyDto)
                .toList();
    }

    public GetBookCopyDto mapToGetBookCopyDto(BookCopy bookCopy) {
        return new GetBookCopyDto(
                bookCopy.getId(),
                titleMapper.mapToGetTitleDto(bookCopy.getTitle()),
                bookCopy.getStatus()

        );
    }

    public List<GetBookCopyDto> mapToGetBookCopyDtoList(List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(this::mapToGetBookCopyDto)
                .toList();
    }
}

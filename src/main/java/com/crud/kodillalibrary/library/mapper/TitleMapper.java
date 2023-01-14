package com.crud.kodillalibrary.library.mapper;

import com.crud.kodillalibrary.library.domain.BookCopy;
import com.crud.kodillalibrary.library.domain.GetTitleDto;
import com.crud.kodillalibrary.library.domain.Title;
import com.crud.kodillalibrary.library.domain.TitleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleMapper {

    public Title mapToTitle(TitleDto titleDto) {
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getReleaseDate(),
                titleDto.getBookCopies()
        );
    }

    public TitleDto mapToTitleDto(Title title) {
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getReleaseDate(),
                title.getBookCopies()
        );
    }

    public List<TitleDto> mapToTitleDtoList(List<Title> titleList) {
        return titleList.stream()
                .map(this::mapToTitleDto)
                .toList();
    }

    public GetTitleDto mapToGetTitleDto(Title title) {
         return new GetTitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getReleaseDate(),
                title.getBookCopies().stream()
                        .map(BookCopy::getId)
                        .toList()
        );
    }

    public List<GetTitleDto> mapToGetTitleDtoList(List<Title> titleList) {
        return titleList.stream()
                .map(this::mapToGetTitleDto)
                .toList();
    }

}

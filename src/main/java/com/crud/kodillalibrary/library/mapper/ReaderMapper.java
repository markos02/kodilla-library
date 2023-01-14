package com.crud.kodillalibrary.library.mapper;

import com.crud.kodillalibrary.library.domain.Reader;
import com.crud.kodillalibrary.library.domain.ReaderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderMapper {

    public Reader mapToReader(ReaderDto readerDto) {
        return new Reader(
                readerDto.getId(),
                readerDto.getFirstName(),
                readerDto.getLastName(),
                readerDto.getCreated(),
                readerDto.getRentals()
        );
    }

    public ReaderDto mapToReaderDto(Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getCreated(),
                reader.getRentals()
        );
    }

    public List<ReaderDto> mapToReaderDtoList(List<Reader> readerList) {
        return readerList.stream()
                .map(this::mapToReaderDto)
                .toList();
    }

}

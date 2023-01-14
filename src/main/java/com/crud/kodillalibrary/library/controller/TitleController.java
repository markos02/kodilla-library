package com.crud.kodillalibrary.library.controller;

import com.crud.kodillalibrary.library.domain.GetTitleDto;
import com.crud.kodillalibrary.library.domain.Title;
import com.crud.kodillalibrary.library.domain.TitleDto;
import com.crud.kodillalibrary.library.domain.TitleNumberAvailableCopiesDto;
import com.crud.kodillalibrary.library.mapper.TitleMapper;
import com.crud.kodillalibrary.library.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/titles")
@RequiredArgsConstructor
public class TitleController {

    private final TitleDbService titleDbService;
    private final TitleMapper titleMapper;

    @GetMapping
    public ResponseEntity<List<GetTitleDto>> getAllTitles() {
        List<Title> titles = titleDbService.getAllTitles();
        return ResponseEntity.ok(titleMapper.mapToGetTitleDtoList(titles));
    }

    @GetMapping(value = "{titleId}")
    public ResponseEntity<GetTitleDto> getTitle(@PathVariable Integer titleId) throws TitleNotFoundException {
        return ResponseEntity.ok(titleMapper.mapToGetTitleDto(titleDbService.getTitle(titleId)));
    }

    @GetMapping("available_copies/{titleId}")
    public ResponseEntity<TitleNumberAvailableCopiesDto> getNumberAvailableCopies(@PathVariable Integer titleId) throws TitleNotFoundException {
        return ResponseEntity.ok(new TitleNumberAvailableCopiesDto(
                titleId,
                titleDbService.numberAvailableCopies(titleId)
        ));
    }

    @PostMapping
    public ResponseEntity<Void> createTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        titleDbService.saveTitle(title);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{titleId}")
    public ResponseEntity<Object> deleteTitle(@PathVariable Integer titleId) throws TitleNotFoundException {
        titleDbService.deleteTitle(titleId);
        return ResponseEntity.ok().build();
    }
}

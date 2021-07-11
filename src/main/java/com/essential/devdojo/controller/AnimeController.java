package com.essential.devdojo.controller;


import com.essential.devdojo.domain.converterDTO.AnimeConverterDTO;
import com.essential.devdojo.domain.dto.AnimeCreateDTO;
import com.essential.devdojo.domain.dto.AnimeDTO;
import com.essential.devdojo.domain.dto.AnimePutDTO;
import com.essential.devdojo.domain.models.Anime;
import com.essential.devdojo.domain.repositories.AnimeRepository;
import com.essential.devdojo.domain.service.AnimeService;
import com.essential.devdojo.domain.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;


    @GetMapping
    public ResponseEntity <Page<Anime>> list(Pageable pageable){
        log.info(dateUtil.formatLocalDateTimeToDateBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll(pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity <List<Anime>> listAll(){
        log.info(dateUtil.formatLocalDateTimeToDateBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAllNonPageable());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id){
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));

    }
    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name){
        return ResponseEntity.ok(animeService.findByName(name));

    }
    @PostMapping
    public ResponseEntity<AnimeDTO> save(@RequestBody @Valid AnimeCreateDTO animeCreateDTO){
        return animeService.save(animeCreateDTO);
//         return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/{animeId}")
    public ResponseEntity<AnimeDTO> updateAnime(@PathVariable Long animeId, @RequestBody AnimePutDTO animePutDTO) {
        return animeService.updateAnime(animeId, animePutDTO);
    }

//    @PutMapping
//    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody AnimePutRequestBody){
//        animeService.replace(AnimePutRequestBody);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//    }

}


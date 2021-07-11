package com.essential.devdojo.domain.service;

import com.essential.devdojo.domain.converterDTO.AnimeConverterDTO;
import com.essential.devdojo.domain.dto.AnimeCreateDTO;
import com.essential.devdojo.domain.dto.AnimeDTO;
import com.essential.devdojo.domain.dto.AnimePutDTO;
import com.essential.devdojo.domain.exception.BadRequestException;
import com.essential.devdojo.domain.models.Anime;
import com.essential.devdojo.domain.repositories.AnimeRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;
    private final AnimeConverterDTO animeConverterDTO;

    public Page<Anime> listAll(Pageable pageable) {return animeRepository.findAll(pageable);}

    public List<Anime> listAllNonPageable() {
        return animeRepository.findAll();
            }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }



    public Anime findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("Anime Not Found"));
    }

    public ResponseEntity<AnimeDTO> save(AnimeCreateDTO animeCreateDTO){

        Anime anime = new Anime();
        anime.setName(animeCreateDTO.getName());

        animeRepository.save(anime);

        AnimeDTO animeDTO = new AnimeDTO();
        animeDTO.setId(anime.getId());


        return ResponseEntity.ok(animeDTO);

    }

    public ResponseEntity<AnimeDTO> updateAnime(Long animeId, AnimePutDTO animePutDTO){
        Anime animeExist = animeRepository.findById(animeId).orElseThrow(() -> new RuntimeException("Anime not found"));

        animeExist.setName(animePutDTO.getName());
        animeRepository.save(animeExist);

        AnimeDTO animeDTO = new AnimeDTO();
        animeDTO.setId(animeExist.getId());
        animeDTO.setName(animeExist.getName());

        return ResponseEntity.ok(animeDTO);
    }


    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

//    public void replace(AnimePutRequestBody AnimePutRequestBody) {
//        Anime SavedAnime = findByIdOrThrowBadRequestException(AnimePutRequestBody.getId());
//        Anime anime = AnimeMapper.INSTANCE.ToAnime(AnimePutRequestBody);
//        anime.setId(SavedAnime.getId());
//        AnimeRepository.save(anime);
//
//    }


}

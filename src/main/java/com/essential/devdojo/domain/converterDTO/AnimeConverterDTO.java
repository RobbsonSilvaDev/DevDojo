package com.essential.devdojo.domain.converterDTO;

import com.essential.devdojo.domain.dto.AnimeCreateDTO;
import com.essential.devdojo.domain.dto.AnimeDTO;
import com.essential.devdojo.domain.models.Anime;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class AnimeConverterDTO {

    private final ModelMapper modelMapper;

    public AnimeDTO toModelAnime(Anime anime) {
        return modelMapper.map(anime, AnimeDTO.class);
    }

    public Anime toModelAnimeCreate(AnimeCreateDTO anime) {
        return modelMapper.map(anime, Anime.class);
    }
}

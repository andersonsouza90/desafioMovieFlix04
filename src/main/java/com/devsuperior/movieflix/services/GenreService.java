package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreService {

    @Autowired
    private GenreRepository repository;

    public List<GenreDTO> findAll(){

        var genreList = repository.findAllByOrderByIdAsc();


        var returnList = new ArrayList<GenreDTO>();

        for (Genre g : genreList){
            var genreDTO = new GenreDTO();
            genreDTO.setId(g.getId());
            genreDTO.setName(g.getName());

            returnList.add(genreDTO);
        }

        return returnList;
    }

}

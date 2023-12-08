package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public MovieDetailsDTO findById(Long id) {

        var movie = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        var movieDetailDTO = new MovieDetailsDTO();
        movieDetailDTO.setId(movie.getId());
        movieDetailDTO.setTitle(movie.getTitle());
        movieDetailDTO.setSubTitle(movie.getSubTitle());
        movieDetailDTO.setYear(movie.getYear());
        movieDetailDTO.setImgUrl(movie.getImgUrl());
        movieDetailDTO.setSynopsis(movie.getSynopsis());

        var genreDTO = new GenreDTO();
        genreDTO.setId(movie.getGenre().getId());
        genreDTO.setName(movie.getGenre().getName());

        movieDetailDTO.setGenre(genreDTO);

        return movieDetailDTO;
    }


    public Page<MovieCardDTO> findAllMovie(Pageable pageable, Long genreId) {

        Page<Movie> movieList;

        if (genreId == 0){
            movieList = repository.findAllByOrderByTitleAsc(pageable);
        }else{
            movieList = repository.findByGenreId(pageable, genreId);
        }

        return movieList.map(movie -> {
            MovieCardDTO movieCardDTO = new MovieCardDTO();
            movieCardDTO.setId(movie.getId());
            movieCardDTO.setTitle(movie.getTitle());
            movieCardDTO.setSubTitle(movie.getSubTitle());
            movieCardDTO.setYear(movie.getYear());
            movieCardDTO.setImgUrl(movie.getImgUrl());
            return movieCardDTO;
        });

    }

}

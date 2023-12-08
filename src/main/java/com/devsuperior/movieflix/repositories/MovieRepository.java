package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface MovieRepository extends JpaRepository<Movie, Long> {

//    @Query(nativeQuery = true,
//            value = "select m.id, m.title, m.suntitle " +
//                    "from tb_movie m inner join tb_genre g on m.genre_id = g.id")
//    List<Movie> findAllByOrderByTittleAsc();

    List<Movie> findAllByOrderByTitleAsc();

    List<Movie> findByGenreId(Long genreId);
}

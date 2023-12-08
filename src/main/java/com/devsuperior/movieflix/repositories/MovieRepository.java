package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

//    @Query(nativeQuery = true,
//            value = "select m.id, m.title, m.suntitle " +
//                    "from tb_movie m inner join tb_genre g on m.genre_id = g.id")
//    List<Movie> findAllByOrderByTittleAsc();

    Page<Movie> findAllByOrderByTitleAsc(Pageable pageable);

    Page<Movie> findByGenreId(Pageable pageable, Long genreId);
}

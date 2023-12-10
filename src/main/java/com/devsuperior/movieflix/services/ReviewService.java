package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private UserService service;

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    public ReviewDTO criaReview(ReviewDTO reviewDTO){
        var review = new Review();
        review.setText(reviewDTO.getText());

        var movie = movieRepository.findById(reviewDTO.getMovieId());
        review.setMovie(movie.get());

        var userDTO = service.getProfile();
        var user = new User();
        user.setId(userDTO.getId());

        review.setUser(user);

        var newReview = repository.save(review);

        reviewDTO.setMovieId(movie.get().getId());
        reviewDTO.setId(newReview.getId());
        reviewDTO.setUserId(userDTO.getId());
        reviewDTO.setUserName(userDTO.getName());
        reviewDTO.setUserEmail(userDTO.getEmail());
        return reviewDTO;
    }
}

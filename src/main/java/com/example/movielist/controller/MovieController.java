package com.example.movielist.controller;

import com.example.movielist.model.MovieModel;
import com.example.movielist.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/movies")
public class MovieController {

    @Autowired
    private MovieRepo movieRepo;

    @GetMapping
    public List<MovieModel> getAllMovies() {
        return movieRepo.findAll();
    }

    @GetMapping("/{id}")
    public MovieModel getMovieById(@PathVariable(name = "id") long id) {
        return movieRepo.findById(id).orElseThrow(() -> new RuntimeException("Movie not found with id " + id));
    }

    @PostMapping
    public MovieModel createMovie(@RequestBody MovieModel movieModel) {
        return movieRepo.save(movieModel);
    }

    @PutMapping("/{id}")
    public MovieModel updateMovie(@PathVariable(name = "id") int id, @RequestBody MovieModel movieModel) {
        movieModel.setId(id);
        return movieRepo.save(movieModel);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable(name = "id") Long id) {
        MovieModel movieModel = movieRepo.findById(id).orElseThrow(() -> new RuntimeException("Movie not found with id " + id));
        movieRepo.delete(movieModel);

    }

}

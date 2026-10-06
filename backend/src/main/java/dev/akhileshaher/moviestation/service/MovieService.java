package dev.akhileshaher.moviestation.service;

import dev.akhileshaher.moviestation.dto.MovieDTO;
import dev.akhileshaher.moviestation.entity.Movie;
import dev.akhileshaher.moviestation.exception.MovieNotFoundException;
import dev.akhileshaher.moviestation.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());
        return movieRepository.save(movie);
    }


    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre)
                .orElseThrow(() -> new MovieNotFoundException("No Movies found for this Genre: "+genre));
    }

    public List<Movie> getMoviesByLanguage(String language) {
        return movieRepository.findByLanguage(language)
                .orElseThrow(() -> new MovieNotFoundException("No Movies found of language: "+language));
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());
        return movieRepository.save(movie);
    }


    public Movie getMovieByTitle(String title) {
        return movieRepository.findByName(title)
                .orElseThrow(() -> new MovieNotFoundException("No Movie found of Title: "+title));
    }

    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie Not Exist with id: " + id));
        movieRepository.delete(movie);
    }
}

package dev.akhileshaher.moviestation.service;

import dev.akhileshaher.moviestation.dto.ShowDTO;
import dev.akhileshaher.moviestation.entity.Booking;
import dev.akhileshaher.moviestation.entity.Movie;
import dev.akhileshaher.moviestation.entity.Show;
import dev.akhileshaher.moviestation.entity.Theater;
import dev.akhileshaher.moviestation.exception.MovieNotFoundException;
import dev.akhileshaher.moviestation.exception.ShowNotFoundException;
import dev.akhileshaher.moviestation.exception.TheaterNotFoundException;
import dev.akhileshaher.moviestation.repository.MovieRepository;
import dev.akhileshaher.moviestation.repository.ShowRepository;
import dev.akhileshaher.moviestation.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;

    public ShowService(ShowRepository showRepository, MovieRepository movieRepository, TheaterRepository theaterRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
    }

    public Show createShow(ShowDTO showDTO) {

        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(() -> new MovieNotFoundException("No Movie Found "));

        Theater theater = theaterRepository.findById(showDTO.getTheaterId())
                .orElseThrow(() -> new TheaterNotFoundException("no Theater found"));

        Show show = new Show();
        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);
        return showRepository.save(show);
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }


    public List<Show> getShowsByMovie(Long movieId) {
        return showRepository.findByMovieId(movieId)
                .orElseThrow(() -> new ShowNotFoundException("No Shows Available for movie " + movieId));
    }

    public List<Show> getShowsByTheater(Long theaterId) {
        return showRepository.findByTheaterId(theaterId)
                .orElseThrow(() -> new ShowNotFoundException("No shows available for the Theater id " + theaterId));
    }

    public Show updateShow(Long id, ShowDTO showDTO) {
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new ShowNotFoundException("No Show Found for id " + id));

        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(() -> new MovieNotFoundException("No Movie Found for id " + showDTO.getMovieId()));

        Theater theater = theaterRepository.findById(showDTO.getTheaterId())
                .orElseThrow(() -> new TheaterNotFoundException("no Theater found for id " + showDTO.getTheaterId()));

        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);
        return showRepository.save(show);
    }


    public void deleteShow(Long id) {
        if(!showRepository.existsById(id)) {
            throw new ShowNotFoundException("No Show Found for id " + id);
        }

        List<Booking> bookings = showRepository.findById(id).get().getBookings();

        if(!bookings.isEmpty()) {
            throw new RuntimeException("Can't delete show with exisitng bookings");
        }

        showRepository.deleteById(id);
    }
}

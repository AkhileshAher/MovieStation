package dev.akhileshaher.moviestation.service;

import dev.akhileshaher.moviestation.dto.TheaterDTO;
import dev.akhileshaher.moviestation.entity.Theater;
import dev.akhileshaher.moviestation.exception.TheaterNotFoundException;
import dev.akhileshaher.moviestation.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public Theater addTheater(TheaterDTO theaterDTO) {
        Theater theater = new Theater();
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        return theaterRepository.save(theater);
    }

    public List<Theater> getTheaterByLocation(String location) {
        return theaterRepository.findByTheaterLocation(location)
                .orElseThrow(() -> new TheaterNotFoundException("No Theaters found at this location " + location));
    }


    public Theater updateTheater(Long id, TheaterDTO theaterDTO) {

        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new TheaterNotFoundException("No Theater Found with this id " + id));

        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        return theaterRepository.save(theater);
    }

    public void deleteTheater(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new TheaterNotFoundException("No Theater Found with this id " + id));

        theaterRepository.delete(theater);
    }
}

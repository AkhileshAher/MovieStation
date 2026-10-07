package dev.akhileshaher.moviestation.controller;

import dev.akhileshaher.moviestation.dto.ShowDTO;
import dev.akhileshaher.moviestation.entity.Show;
import dev.akhileshaher.moviestation.service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping()
    public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(showService.createShow(showDTO));
    }

    @GetMapping()
    public ResponseEntity<List<Show>> getAllShows() {
        return ResponseEntity.ok(showService.getAllShows());
    }

    @GetMapping("/showsbymovie")
    public ResponseEntity<List<Show>> getShowsByMovie(@RequestParam Long movieId) {
        return ResponseEntity.ok(showService.getShowsByMovie(movieId));
    }

    @GetMapping("/showsbytheater")
    public ResponseEntity<List<Show>> getShowsByTheater(@RequestParam Long theaterId) {
        return ResponseEntity.ok(showService.getShowsByTheater(theaterId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id,@RequestBody ShowDTO showDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(showService.updateShow(id,showDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }

}

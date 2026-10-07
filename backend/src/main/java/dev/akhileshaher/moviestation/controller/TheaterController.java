package dev.akhileshaher.moviestation.controller;

import dev.akhileshaher.moviestation.dto.TheaterDTO;
import dev.akhileshaher.moviestation.entity.Theater;
import dev.akhileshaher.moviestation.service.TheaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    private final TheaterService theaterService;

    public  TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> addTheater(@RequestBody TheaterDTO theaterDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(theaterService.addTheater(theaterDTO));
    }

    @GetMapping("/theaterbylocation")
    public ResponseEntity<List<Theater>> getTheaterByLocation(@RequestParam String location){
        return ResponseEntity.ok(theaterService.getTheaterByLocation(location));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id,@RequestBody TheaterDTO theaterDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(theaterService.updateTheater(id,theaterDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id){
        theaterService.deleteTheater(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

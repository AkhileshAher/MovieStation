package dev.akhileshaher.moviestation.controller;

import dev.akhileshaher.moviestation.dto.BookingDTO;
import dev.akhileshaher.moviestation.entity.Booking;
import dev.akhileshaher.moviestation.entity.BookingStatus;
import dev.akhileshaher.moviestation.repository.BookingRepository;
import dev.akhileshaher.moviestation.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/createbooking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(bookingDTO));
    }

    @GetMapping("/getUserBookings/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getUserBookings(userId));
    }

    @GetMapping("/getshowbookings/{id}")
    public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long showId) {
        return ResponseEntity.ok(bookingService.getShowBookings(showId));
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingService.confirmBooking(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingService.cancelBooking(id));
    }

    @GetMapping("/getbookingsbystatus/{status}")
    public ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable BookingStatus bookingStatus) {
        return ResponseEntity.ok(bookingService.getBookingsByStatus(bookingStatus));
    }

}

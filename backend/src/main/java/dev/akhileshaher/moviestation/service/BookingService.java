package dev.akhileshaher.moviestation.service;

import dev.akhileshaher.moviestation.dto.BookingDTO;
import dev.akhileshaher.moviestation.entity.Booking;
import dev.akhileshaher.moviestation.entity.BookingStatus;
import dev.akhileshaher.moviestation.entity.Show;
import dev.akhileshaher.moviestation.entity.User;
import dev.akhileshaher.moviestation.exception.BookedSeatException;
import dev.akhileshaher.moviestation.exception.SeatsNotAvailableException;
import dev.akhileshaher.moviestation.exception.ShowNotFoundException;
import dev.akhileshaher.moviestation.exception.UserNotFoundException;
import dev.akhileshaher.moviestation.repository.BookingRepository;
import dev.akhileshaher.moviestation.repository.ShowRepository;
import dev.akhileshaher.moviestation.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;

    public BookingService(
            BookingRepository bookingRepository,
            ShowRepository showRepository,
            UserRepository userRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
    }


    public Booking createBooking(BookingDTO bookingDTO) {

        Show show = showRepository.findById(bookingDTO.getShowId())
                .orElseThrow(() -> new ShowNotFoundException("Show Not Found"));

        if(!isSeatsAvailable(show.getId(), bookingDTO.getNumberOfSeats())) {
            throw new SeatsNotAvailableException("Not enough seats available");
        }

        if(bookingDTO.getSeatNumbers().size() != bookingDTO.getNumberOfSeats()) {
            throw new SeatsNotAvailableException("Number of seats not available");
        }

        validateDuplicateSeats(show.getId(), bookingDTO.getSeatNumbers());

        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setSeatNumbers(bookingDTO.getSeatNumbers());
        booking.setPrice(calculateTotalAmount(show.getPrice(), bookingDTO.getNumberOfSeats()));
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);

    }

    private Double calculateTotalAmount(Double price, Integer numberOfSeats) {
        return price * numberOfSeats;
    }

    private void validateDuplicateSeats(Long id, List<String> seatsNumbers) {
        Show show = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException("Show Not Found"));

        Set<String> occupiedSeats = show.getBookings().stream()
                .filter(b -> b.getBookingStatus() != BookingStatus.CANCELLED)
                .flatMap(b -> b.getSeatNumbers().stream())
                .collect(Collectors.toSet());

        List<String> duplicateSeats = seatsNumbers.stream()
                .filter(occupiedSeats::contains)
                .collect(Collectors.toList());

        if(!duplicateSeats.isEmpty()) {
            throw new BookedSeatException("Seats are already booked ");
        }

    }

    private boolean isSeatsAvailable(Long id, Integer numberOfSeats) {
        Show show = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException("Show Not Found"));
        int bookedSeats = show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELLED)
                .mapToInt(Booking::getNumberOfSeats)
                .sum();

        return (show.getTheater().getTheaterCapacity() - bookedSeats) >= numberOfSeats;
    }

    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getShowBookings(Long showId) {
        return bookingRepository.findByShowId(showId);
    }


    public Booking confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking Not Found"));

        if(booking.getBookingStatus() != BookingStatus.PENDING) {
            throw new BookedSeatException("Booking is not in pending state");
        }

        // TODO PAYMENT API PROCESS
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);
    }

    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookedSeatException("Booking Not Found"));

        validateCancellation(booking);
        booking.setBookingStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }

    private void validateCancellation(Booking booking) {
        LocalDateTime showTime = booking.getShow().getShowTime();
        LocalDateTime deadLineTime = showTime.minusHours(2);

        if(LocalDateTime.now().isAfter(deadLineTime)) {
            throw new RuntimeException("Cannot cancel the booking");
        }

        if(booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking is already cancelled");
        }

    }

    public List<Booking> getBookingsByStatus(BookingStatus bookingStatus) {
        return bookingRepository.findBookingsByBookingStatus(bookingStatus);
    }
}

package dev.akhileshaher.moviestation.repository;

import dev.akhileshaher.moviestation.entity.Booking;
import dev.akhileshaher.moviestation.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByShowId(Long userId);

    List<Booking> findBookingsByBookingStatus(BookingStatus bookingStatus);
}

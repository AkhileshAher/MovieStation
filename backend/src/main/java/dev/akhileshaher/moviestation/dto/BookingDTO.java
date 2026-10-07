package dev.akhileshaher.moviestation.dto;

import dev.akhileshaher.moviestation.entity.BookingStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    @NotNull(message = "Number of seats is required")
    @Positive(message = "Number of seats cannot be negative")
    private Integer numberOfSeats;

    @FutureOrPresent(message = "Time cannot be in past")
    @NotNull(message = "Booking time is required")
    private LocalDateTime bookingTime;

    @NotNull(message = "Price is required")
    @Positive(message = "Price cannot be negative")
    private Double price;

    @NotNull(message = "Booking status is required")
    private BookingStatus bookingStatus;

    private List<String> seatNumbers;

    @NotNull(message = "User id is required")
    private Long userId;

    @NotNull(message = "User id is required")
    private Long showId;
}

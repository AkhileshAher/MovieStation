package dev.akhileshaher.moviestation.dto;

import dev.akhileshaher.moviestation.entity.Theater;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDTO {

    @FutureOrPresent(message = "Show Time cant be in past")
    private LocalDateTime showTime;

    @Positive(message = "Price Cannot be Negative")
    private Double price;

    @NotBlank(message = "Movie Id is Required")
    private Long movieId;

    @NotBlank(message = "Theatre Id is Required")
    private Long theaterId;

}

package dev.akhileshaher.moviestation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterDTO {

    @NotBlank(message = "Theater name is required")
    private String theaterName;
    @NotBlank(message = "Location is required")
    private String theaterLocation;

    @Positive(message = "Theatre capacity should be positive")
    private Integer theaterCapacity;

    @NotBlank(message = "Theatre Screen is required")
    private String theaterScreenType;
}

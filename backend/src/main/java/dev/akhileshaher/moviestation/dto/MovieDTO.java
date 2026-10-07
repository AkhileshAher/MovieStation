package dev.akhileshaher.moviestation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    @NotBlank(message = "Movie name is Required")
    private String name;

    @NotBlank(message = "Movie description is required")
    private String description;

    @NotBlank(message = "Genre is Required")
    private String genre;

    @Positive
    private Integer duration;

    @NotNull(message = "Date is Required")
    private LocalDate releaseDate;

    @NotBlank(message = "Language is Required")
    private String language;
}

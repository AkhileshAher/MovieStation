package dev.akhileshaher.moviestation.dto;

import dev.akhileshaher.moviestation.entity.Theater;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDTO {
    private LocalDateTime showTime;
    private Double price;
    private Long movieId;
    private Long theaterId;

}

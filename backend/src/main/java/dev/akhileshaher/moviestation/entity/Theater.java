package dev.akhileshaher.moviestation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theaterName;
    private String theaterLocation;
    private Integer theaterCapacity;
    private String theaterScreenType;

    @OneToMany(mappedBy = "theater",fetch = FetchType.LAZY)
    private List<Show> shows;

}

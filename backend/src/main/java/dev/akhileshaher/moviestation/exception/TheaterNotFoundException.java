package dev.akhileshaher.moviestation.exception;

public class TheaterNotFoundException extends RuntimeException {
    public TheaterNotFoundException(String message) {
        super(message);
    }
}

package dev.akhileshaher.moviestation.exception;

public class BookedSeatException extends RuntimeException {
    public BookedSeatException(String message) {
        super(message);
    }
}

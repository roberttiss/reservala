package br.com.ada.reservala.exception;

public class ReservationFailedException extends RuntimeException{

    public ReservationFailedException(String message) {
        super(message);
    }
}

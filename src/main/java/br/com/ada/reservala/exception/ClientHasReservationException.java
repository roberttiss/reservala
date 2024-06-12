package br.com.ada.reservala.exception;

public class ClientHasReservationException extends RuntimeException{
    public ClientHasReservationException(String message) {
        super(message);
    }
}

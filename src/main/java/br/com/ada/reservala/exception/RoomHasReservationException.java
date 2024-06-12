package br.com.ada.reservala.exception;

public class RoomHasReservationException extends RuntimeException{
    public RoomHasReservationException(String message) {
        super(message);
    }
}

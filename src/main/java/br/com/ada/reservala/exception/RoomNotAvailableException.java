package br.com.ada.reservala.exception;

public class RoomNotAvailableException extends RuntimeException{

    public RoomNotAvailableException(String message) {
        super(message);
    }
}

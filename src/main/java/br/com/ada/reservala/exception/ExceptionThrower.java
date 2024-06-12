package br.com.ada.reservala.exception;

public class ExceptionThrower {

    public static void throwClientHasReservationException(String message) {
        throw new ClientHasReservationException( message);
    }

    public static void throwClientNotFoundException(String message) {
        throw new ClientNotFoundException(message);
    }

    public static void throwIdAlreadyExistsException(String message) {
        throw new IdAlreadyExistsException(message);
    }

    public static void throwReservationNotFoundException(String message) {
        throw new ReservationNotFoundException(message);
    }

    public static void throwRoomHasReservationException(String message) {
        throw new RoomHasReservationException( message);
    }

    public static void throwRoomNotAvailableException(String message) {
        throw new RoomNotAvailableException( message);
    }

    public static void throwRoomNotFoundException(String message) {
        throw new RoomNotFoundException( message);
    }
    public static void throwReservationFailedException(String message) {
        throw new ReservationFailedException( message);
    }



}
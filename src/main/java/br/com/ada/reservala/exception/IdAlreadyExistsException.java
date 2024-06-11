package br.com.ada.reservala.exception;

public class IdAlreadyExistsException extends RuntimeException{

    public IdAlreadyExistsException(String message){
        super(message);
    }
}

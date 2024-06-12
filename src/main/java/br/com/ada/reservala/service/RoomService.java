package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.exception.IdAlreadyExistsException;
import br.com.ada.reservala.exception.RoomNotFoundException;
import br.com.ada.reservala.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public Room createRoom(Room room){
        roomExists(room.getRoomNumber(),new IdAlreadyExistsException("Room with number " + room.getRoomNumber() + " already exists."));
        return roomRepository.createRoom(room);
    }

    public List<Room> readRoom(){
        return roomRepository.readRoom();
    }

    public List<Room> readRoomByRoomNumber(Integer roomNumber) {
        roomNoExists(roomNumber,new RoomNotFoundException("Room with number " + roomNumber + " not found."));
        return roomRepository.readRoomByRoomNumber(roomNumber);
}

    public Optional<Room> updateRoom(Room room, Integer roomNumber) {
        roomNoExists(roomNumber,new RoomNotFoundException("Room with number " + roomNumber + " not found."));
        return Optional.of(roomRepository.updateRoom(room,roomNumber));
    }

    public void deleteRoom(Integer roomNumber){
        roomNoExists(roomNumber,new RoomNotFoundException("Room with number " + roomNumber + " not found."));
        roomRepository.deleteRoom(roomNumber);
    }

    public void roomExists(Integer roomNumber, RuntimeException exception){
        if (roomRepository.roomExists(roomNumber)){
            throw exception;
        }
    }

    public void roomNoExists(Integer roomNumber, RuntimeException exception){
        if (!roomRepository.roomExists(roomNumber)){
            throw exception;
        }
    }

    public void roomIsAvalaible(Integer roomNumber, RuntimeException exception){
        if (!roomRepository.roomIsAvalaible(roomNumber)){
            throw exception;
        }
    }

    public Double getOcupation(){
        return roomRepository.getOcupation();
    }

    public Double getRevenue(){
        return roomRepository.countRevenue();
    }

}

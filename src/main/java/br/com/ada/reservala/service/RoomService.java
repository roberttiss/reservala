package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.exception.ExceptionThrower;
import br.com.ada.reservala.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final ReservationService reservationService;

    public RoomService(RoomRepository roomRepository, ReservationService reservationService){
        this.roomRepository = roomRepository;
        this.reservationService = reservationService;
    }

    public Room createRoom(Room room){
        checkIfRoomExists(room.getRoomNumber());
        return roomRepository.createRoom(room);
    }

    public List<Room> readRoom(){
        return roomRepository.readRoom();
    }

    public Room readRoomByRoomNumber(Integer roomNumber) {
        checkIfRoomDoesNotExists(roomNumber);
        return roomRepository.readRoomByRoomNumber(roomNumber);
}

    public Optional<Room> updateRoom(Room room, Integer roomNumber) {
        checkIfRoomDoesNotExists(roomNumber);
        return Optional.of(roomRepository.updateRoom(room,roomNumber));
    }

    public void deleteRoom(Integer roomNumber){
        checkIfRoomDoesNotExists(roomNumber);
        reservationService.checkIfRoomHasReservation(roomNumber);
        roomRepository.deleteRoom(roomNumber);
    }

    public void checkIfRoomExists(Integer roomNumber){
        if (roomRepository.roomExists(roomNumber)){
            ExceptionThrower.throwIdAlreadyExistsException("Room with id " + roomNumber + " already exist.");
        }
    }

    public void checkIfRoomDoesNotExists(Integer roomNumber){
        if (!roomRepository.roomExists(roomNumber)){
            ExceptionThrower.throwRoomNotFoundException("Room with id " + roomNumber + " not exist.");
        }
    }

    public void checkIfRoomDoesNotAvailable(Integer roomNumber){
        if (!roomRepository.roomIsAvalaible(roomNumber)){
            ExceptionThrower.throwRoomNotAvailableException("Room with id " + roomNumber + " is not available.");
        }
    }

    public Double getOcupation(){
        return roomRepository.getOcupation();
    }

    public Double getRevenue(){
        return roomRepository.countRevenue();
    }

}

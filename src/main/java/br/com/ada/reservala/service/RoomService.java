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
        if (roomRepository.roomExists(room.getRoomNumber())){
            throw new IdAlreadyExistsException("Room with number " + room.getRoomNumber() + " already exists.");
        }
        return roomRepository.createRoom(room);
    }

    public List<Room> readRoom(){
        return roomRepository.readRoom();
    }

    public Optional<Room> updateRoom(Room room, Integer roomNumber) {
        if (!roomRepository.roomExists(roomNumber)){
            throw new RoomNotFoundException("Room with number " + roomNumber + " not found.");
        }
        return Optional.of(roomRepository.updateRoom(room,roomNumber));
    }

    public void deleteRoom(Integer roomNumber){
        if (!roomRepository.roomExists(roomNumber)){
            throw new RoomNotFoundException("Room with number " + roomNumber + " not found.");
        }
        roomRepository.deleteRoom(roomNumber);
    }

    public Double getOcupation(){
        double allRoms = roomRepository.getNumberOfRooms();
        if(allRoms == 0){
            return 0.0;
        }
        double ocupiedRooms = roomRepository.getNumberOfOccupiedRooms();
        return (ocupiedRooms / allRoms) * 100;
    }

    public Double getRevenue(){
        return roomRepository.countRevenue();
    }

}

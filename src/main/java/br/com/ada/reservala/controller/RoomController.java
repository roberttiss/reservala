package br.com.ada.reservala.controller;

import br.com.ada.reservala.controller.dto.RoomDTORequest;
import br.com.ada.reservala.controller.dto.RoomDTOResponse;
import br.com.ada.reservala.controller.mapper.RoomMapper;
import br.com.ada.reservala.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    public RoomController(RoomService roomService, RoomMapper roomMapper){
        this.roomService = roomService;
        this.roomMapper = roomMapper;
    }

    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @PostMapping
    public ResponseEntity<RoomDTOResponse> createRoom(@RequestBody  RoomDTORequest newRoom){
        var room = roomMapper.toEntity(newRoom);
        var response = roomMapper.toDto(roomService.createRoom(room));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<RoomDTOResponse>> readRoom(){
        var response = roomMapper.toDto(roomService.readRoom());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{roomNumber}")
    public ResponseEntity<RoomDTOResponse> updateRoom(@RequestBody RoomDTORequest newRoom, @PathVariable("roomNumber") Integer roomNumber){
        var roomOptional = roomService.updateRoom(roomMapper.toEntity(newRoom),roomNumber);
        return roomOptional
            .map(room -> {
                    var dto = roomMapper.toDto(room);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    @RequestMapping("/{roomNumber}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("roomNumber") Integer roomNumber){
        roomService.deleteRoom(roomNumber);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ocupation")
    public ResponseEntity<Double> getOcupation(){
        return ResponseEntity.ok(roomService.getOcupation());
    }

    @GetMapping("/revenue")
    public ResponseEntity<Double> getRevenue(){
        return ResponseEntity.ok(roomService.getRevenue());
    }

}

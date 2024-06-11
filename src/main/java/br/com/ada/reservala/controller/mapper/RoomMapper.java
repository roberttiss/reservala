package br.com.ada.reservala.controller.mapper;

import br.com.ada.reservala.controller.dto.RoomDTORequest;
import br.com.ada.reservala.controller.dto.RoomDTOResponse;
import br.com.ada.reservala.domain.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomMapper {

    public Room toEntity(final RoomDTORequest dto){
        return Room.builder()
                .roomNumber(dto.getRoomNumber())
                .type(dto.getType())
                .price(dto.getPrice())
                .available(dto.getAvailable())
                .build();
    }

    public List<RoomDTOResponse> toDto(final List<Room> entities){
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public RoomDTOResponse toDto(final Room entity){
        return RoomDTOResponse.builder()
                .roomNumber(entity.getRoomNumber())
                .type(entity.getType())
                .price(entity.getPrice())
                .available(entity.getAvailable())
                .build();
    }
}

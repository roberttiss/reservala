package br.com.ada.reservala.controller;

import br.com.ada.reservala.controller.dto.ReservationDTORequest;
import br.com.ada.reservala.controller.dto.ReservationDTOResponse;
import br.com.ada.reservala.controller.mapper.ReservationMapper;
import br.com.ada.reservala.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(ReservationMapper reservationMapper, ReservationService reservationService) {
        this.reservationMapper = reservationMapper;
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDTOResponse> createReservation(@Valid @RequestBody ReservationDTORequest newReservation){
        var reservation = reservationMapper.toEntity(newReservation);
        var response = reservationMapper.todto(reservationService.createReservation(reservation));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTOResponse>> readReservation(){
        var response = reservationMapper.todto(reservationService.readReservations());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{idReservation}")
    public ResponseEntity<ReservationDTOResponse> readReservationByReservationId(@PathVariable("idReservation") Integer idReservation){
        var reservationOptional = reservationService.readReservationByReservationId(idReservation);
        if (reservationOptional.isPresent()){
            var response = reservationMapper.todto(reservationOptional.get());
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<ReservationDTOResponse>> readReservationByClientId(@PathVariable("idClient") Integer idClient){
        var response  = reservationMapper.todto(reservationService.readReservationsByClientId(idClient));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{idReservation}")
    public ResponseEntity<ReservationDTOResponse> updateReservation(@RequestBody ReservationDTORequest newReservation, @PathVariable("idReservation") Integer idReservation){
        var reservationOptional = reservationService.updateReservation(reservationMapper.toEntity(newReservation),idReservation);
        return  reservationOptional
                .map(reservation -> {
                    var dto = reservationMapper.todto(reservation);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    @RequestMapping("/{idReservation}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("idReservation") Integer idReservation){
        reservationService.deleteReservation(idReservation);
        return ResponseEntity
                .noContent()
                .build();
    }
}

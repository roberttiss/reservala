package br.com.ada.reservala.controller;

import br.com.ada.reservala.controller.mapper.ReservationMapper;
import br.com.ada.reservala.service.ReservationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;
    private ReservationMapper reservationMapper;
}

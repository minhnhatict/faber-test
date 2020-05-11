package faber.codetest.nhat.controller;

import faber.codetest.nhat.model.ResponseDto;
import faber.codetest.nhat.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/public/flight")
public class FlightController{

    @Autowired
    private FlightService flightService;

    @GetMapping(value = "/search")
    public ResponseEntity<ResponseDto> search() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(Boolean.TRUE);
        responseDto.setData(flightService.getAvailableFlights(null));
        return ResponseEntity.ok(responseDto);
    }

}

package faber.codetest.nhat.controller;

import faber.codetest.nhat.entity.Airport;
import faber.codetest.nhat.entity.Flight;
import faber.codetest.nhat.model.ResponseDto;
import faber.codetest.nhat.service.AirportService;
import faber.codetest.nhat.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/public/airport")
public class AirportController {
    private final Logger LOGGER = LoggerFactory.getLogger(AirportController.class);

    @Autowired
    private AirportService airportService;

    @Autowired
    private FlightService flightService;

    /**
     * search list of airports
     * in available flights (user can book tickets)
     *
     * @return
     */
    @GetMapping(value = "/search-available-departure")
    public ResponseEntity<ResponseDto> searchAvailableDeparture(){
        ResponseDto responseDto = new ResponseDto();

        // search available flights
        List<Flight> availableFlights = flightService.getAvailableFlights(null);
        List<Airport> airports = new ArrayList<>();
        // get departure airport
        for (Flight flight : availableFlights) {
            airports.add(flight.getDepartureAirport());
        }
        responseDto.setData(airports);
        responseDto.setSuccess(Boolean.TRUE);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * search available arrival airport
     * in available flights (user can book tickets)
     * based on departure
     *
     * @param departureAirportId
     * @return
     */
    @GetMapping(value = "/search-available-arrival")
    public ResponseEntity<ResponseDto> searchAvailableArrival(@RequestParam(value = "departureAirportId") Long departureAirportId) {
        Airport departureAirport = airportService.findById(departureAirportId);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(Collections.EMPTY_LIST);
        if (departureAirportId != null) {
            List<Flight> availableFlights = flightService.getAvailableFlights(departureAirport);
            List<Airport> airports = new ArrayList<>();

            // get departure airport
            for (Flight flight : availableFlights) {
                airports.add(flight.getArrivalAirport());
            }
            responseDto.setData(airports);
        }
        responseDto.setSuccess(Boolean.TRUE);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * get list of airports
     * @return
     */
    @GetMapping
    public ResponseEntity<ResponseDto> getList() {
        try {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(Boolean.TRUE);
            responseDto.setData(airportService.findAll());
            return ResponseEntity.ok(responseDto);
        } catch (Exception ex) {
            LOGGER.error("Cannot get airport list", ex);
        }
        return ResponseEntity.notFound().build();
    }
}

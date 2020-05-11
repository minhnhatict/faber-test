package faber.codetest.nhat.controller.admin;

import faber.codetest.nhat.controller.AbstractController;
import faber.codetest.nhat.entity.Flight;
import faber.codetest.nhat.service.AbstractCrudService;
import faber.codetest.nhat.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/admin/flight")
public class AdminFlightController extends AbstractController<Flight, Long> {

    @Autowired
    private FlightService flightService;

    @Override
    public AbstractCrudService<Flight, Long> getCrudService() {
        return flightService;
    }

    @Override
    public Collection<Flight> getDefaultList() {
        return flightService.findAll();
    }
}

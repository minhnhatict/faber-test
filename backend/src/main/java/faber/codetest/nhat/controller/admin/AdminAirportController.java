package faber.codetest.nhat.controller.admin;

import faber.codetest.nhat.controller.AbstractController;
import faber.codetest.nhat.entity.Airport;
import faber.codetest.nhat.entity.Country;
import faber.codetest.nhat.service.AbstractCrudService;
import faber.codetest.nhat.service.AirportService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping(value = "/api/admin/airport")
public class AdminAirportController extends AbstractController<Airport, Long> {


    @Autowired
    private AirportService airportService;

    @Override
    public AbstractCrudService<Airport, Long> getCrudService() {
        return airportService;
    }


    @Override
    public Collection<Airport> getDefaultList() {
        return airportService.findAll();
    }

    /**
     * searchAvailableDepartureAirport Country by Key
     *
     * @param searchKey
     * @return
     */
    @GetMapping(value = "/search")
    public ResponseEntity<List<Airport>> search(@RequestParam(value = "searchKey", required = false) String searchKey) {
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = "";
        }
        return ResponseEntity.ok(airportService.searchByNameAndCountry(searchKey));
    }

}

package faber.codetest.nhat.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import faber.codetest.nhat.entity.Airport;
import faber.codetest.nhat.repository.AirportRepository;

import java.util.Collections;
import java.util.List;

@Service
public class AirportService extends AbstractCrudService<Airport, Long> {

    @Autowired
    private AirportRepository repository;

    @Override
    public AirportRepository getRepository() {
        return repository;
    }

    @Override
    public List<Airport> findAll() {
        return repository.findAll();
    }

    /**
     * searchAvailableDepartureAirport like by airport's name orits country's name or code
     *
     * @param searchKey
     * @return
     */
    public List<Airport> searchByNameAndCountry(String searchKey) {
        if (StringUtils.isNotEmpty(searchKey)) {
            return repository.searchByNameAndCountry("%" + searchKey + "%");
        }
        return Collections.emptyList();
    }
}

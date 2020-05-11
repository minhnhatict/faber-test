package faber.codetest.nhat.service;

import faber.codetest.nhat.entity.Airport;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import faber.codetest.nhat.entity.Flight;
import faber.codetest.nhat.repository.FlightRepository;
import org.thymeleaf.spring5.expression.SPELContextMapWrapper;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Service
public class FlightService extends AbstractCrudService<Flight, Long> {

    @Autowired
    private FlightRepository repository;

    @Autowired
    private EntityManager em;

    /**
     * searchAvailableDepartureAirport list of flights
     *  which are available for user to book ticket
     *
     * @return
     */
    public List<Flight> getAvailableFlights(Airport departureAirport) {
        // build predicate
        CriteriaBuilder cb = em.getCriteriaBuilder();
        Specification<Flight> specification  = (Specification<Flight>) (root, criteriaQuery, criteriaBuilder)
                -> cb.greaterThan(root.get(Flight.DEPARTURE_DATETIME), new Date().getTime());

        // build predicate of departure airport
        if (departureAirport != null) {
            Specification<Flight> departureAirportSpec = (Specification<Flight>) (root, criteriaQuery, criteriaBuilder)
                    -> cb.equal(root.get(Flight.DEPARTURE_AIRPORT), departureAirport);
            specification = Specification.where(specification).and(departureAirportSpec);
        }
        Pageable pageable = PageRequest.of(NumberUtils.INTEGER_ZERO, Integer.MAX_VALUE);
        return repository.findAll(specification, pageable).getContent();
    }

    @Override
    public List<Flight> findAll() {
        return repository.findAll();
    }

    @Override
    public JpaRepository<Flight, Long> getRepository() {
        return repository;
    }
}

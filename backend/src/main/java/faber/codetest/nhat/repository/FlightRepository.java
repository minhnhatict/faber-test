package faber.codetest.nhat.repository;


import faber.codetest.nhat.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Page<Flight> findAll(Specification<Flight> specification, Pageable pageable);

}

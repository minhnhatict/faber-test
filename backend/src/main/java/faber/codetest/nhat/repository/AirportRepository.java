package faber.codetest.nhat.repository;

import faber.codetest.nhat.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository  extends JpaRepository<Airport, Long> {

    @Query(value = "select a from Airport a " +
            " where a.name like :searchKey " +
            " or a.country.code like :searchKey " +
            " or a.country.name like :searchKey ")
    public List<Airport> searchByNameAndCountry(@Param("searchKey") String searchKey);

}

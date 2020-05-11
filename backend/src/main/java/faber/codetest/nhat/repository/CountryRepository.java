package faber.codetest.nhat.repository;

import faber.codetest.nhat.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    @Query(value = "select c from Country c where c.code like :searchKey or c.name like :searchKey")
    public List<Country> searchByCodeAndName(@Param("searchKey") String searchKey);
}

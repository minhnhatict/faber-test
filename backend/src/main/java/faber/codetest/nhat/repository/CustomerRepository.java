package faber.codetest.nhat.repository;

import faber.codetest.nhat.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findFirstByPersonId(String personId);
}

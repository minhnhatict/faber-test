package faber.codetest.nhat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import faber.codetest.nhat.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

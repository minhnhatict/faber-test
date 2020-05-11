package faber.codetest.nhat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import faber.codetest.nhat.entity.Order;
import faber.codetest.nhat.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService extends AbstractCrudService<Order, Long> {

    @Autowired
    private OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public JpaRepository<Order, Long> getRepository() {
        return repository;
    }
}

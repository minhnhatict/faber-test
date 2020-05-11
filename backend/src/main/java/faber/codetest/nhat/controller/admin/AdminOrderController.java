package faber.codetest.nhat.controller.admin;

import faber.codetest.nhat.controller.AbstractController;
import faber.codetest.nhat.entity.Order;
import faber.codetest.nhat.service.AbstractCrudService;
import faber.codetest.nhat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/admin/order")
public class AdminOrderController extends AbstractController<Order, Long> {

    @Autowired
    private OrderService orderService;

    @Override
    public AbstractCrudService<Order, Long> getCrudService() {
        return orderService;
    }

    @Override
    public Collection<Order> getDefaultList() {
        return orderService.findAll();
    }
}

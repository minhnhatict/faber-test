package faber.codetest.nhat.controller;

import faber.codetest.nhat.entity.Order;
import faber.codetest.nhat.model.ResponseDto;
import faber.codetest.nhat.service.CustomerService;
import faber.codetest.nhat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/public/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    /**
     * api for user to book ticket
     */
    @PostMapping(value = "/book-ticket")
    public ResponseEntity<ResponseDto> bookTicket(@RequestBody Order order){

        orderService.save(order);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(responseDto);
    }
}

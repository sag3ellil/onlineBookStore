package org.example.service;

import org.example.entity.Order;
import org.example.model.OrderRequest;
import org.example.repository.OrderRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository)
    {
        this.orderRepository=orderRepository;
    }


    public Order placeOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setBooks(orderRequest.getBooks());
        order.setUserId(orderRequest.getUserId());
        order.setTotalPrice(orderRequest.getTotalPrice());

        return orderRepository.save(order);
    }

    public Order getOrderByOrderId(Integer id) {
        return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found"));
    }
}

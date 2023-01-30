package com.br.apilibrary.service.impls;

import com.br.apilibrary.entity.Order;
import com.br.apilibrary.repository.OrderRepository;
import com.br.apilibrary.service.OrderService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(int orderId) {

        var optional = orderRepository.findById(orderId);

        if(optional.isEmpty()){
           throw new NoResultException("Order not found!");
        }

        return optional.get();
    }
}

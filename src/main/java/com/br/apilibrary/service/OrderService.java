package com.br.apilibrary.service;

import com.br.apilibrary.entity.Order;

import java.util.List;

public interface OrderService {

    public List<Order> findAll();

    public Order findById(int orderId);

}

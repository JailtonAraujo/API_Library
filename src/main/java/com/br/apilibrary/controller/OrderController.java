package com.br.apilibrary.controller;

import com.br.apilibrary.DTO.OrderDTO;
import com.br.apilibrary.entity.Order;
import com.br.apilibrary.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<List<OrderDTO>> findAll (){

        List<Order> orders = orderService.findAll();

        var dtos = orders.stream().map(order -> modelMapper.map(order,OrderDTO.class)).collect(Collectors.toList());

        for( OrderDTO dto : dtos ){
            dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).findById(dto.getId())).withSelfRel());

        }

        return ResponseEntity.ok(dtos);

    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById (@PathVariable(name = "id") int id ){

        var dto = modelMapper.map(this.orderService.findById(id),OrderDTO.class);

        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).findAll()).withRel("orders list"));

        return ResponseEntity.ok(dto);
    }

}

package com.br.apilibrary.service;

import com.br.apilibrary.entity.Book;
import com.br.apilibrary.entity.Customer;
import com.br.apilibrary.entity.Order;
import com.br.apilibrary.service.impls.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class OrderServiceTest {

    @TestConfiguration
    static class OrderServiceTestConfig{

        @Bean
        public OrderService orderService(){
            return new OrderServiceImpl();
        }

    }

    @Autowired
    private BookService bookService;


    @Test
    void checkInOrder() {

        Book book = new Book();
        book.setId(1);
        book.setName("Codigo da Vinci");
        book.setQuant(10);
        book.setYearBook(2015);

        Customer customer = new Customer();
        customer.setId(1);
        customer.setCpf("804.154.567-99");
        customer.setFirstName("Jailton");
        customer.setLastName("Araujo");

        Order order = new Order();
        order.setId(1);
        order.setCheckIn(LocalDate.now());
        order.setCheckOut(LocalDate.now().plusDays(10));
        order.setCustomer(customer);
        order.setBook(book);

        //verify if is available
       //generate checkOutDate
      //decrement book quantity

    }



}
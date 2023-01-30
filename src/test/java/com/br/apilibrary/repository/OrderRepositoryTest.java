package com.br.apilibrary.repository;

import com.br.apilibrary.entity.Book;
import com.br.apilibrary.entity.Customer;
import com.br.apilibrary.entity.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void checkInBook (){

        Book book = Book.builder().name("book_test").yearBook(2015).quant(10).build();
        int bookId = bookRepository.save(book).getId();
        book.setId(bookId);

        Customer customer = Customer.builder().cpf("845.454.254-22").firstName("firstName_customer")
                .lastName("lastName_customer").build();
        int customerId = customerRepository.save(customer).getId();
        customer.setId(customerId);

        Order order = Order.builder().checkIn(LocalDate.now())
                .checkOut(LocalDate.now().plusDays(10)).book(book).customer(customer).build();

        Assertions.assertTrue(orderRepository.save(order).getId() > 0);


    }

}

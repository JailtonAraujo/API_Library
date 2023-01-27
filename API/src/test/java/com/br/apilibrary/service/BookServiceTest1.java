package com.br.apilibrary.service;

import com.br.apilibrary.entity.Book;
import com.br.apilibrary.entity.Customer;
import com.br.apilibrary.entity.Order;
import com.br.apilibrary.repository.BookRepository;
import com.br.apilibrary.repository.OrderRepository;
import com.br.apilibrary.service.impls.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BookServiceTest1 {


   @TestConfiguration
   static class BookServiceTestConfig{

       @Bean
       public BookService bookService(){
           return new BookServiceImpl();
       }

   }

    @Autowired
    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    @MockBean
    OrderRepository orderRepository;

    private Book book;
    private Customer customer;
    private Order order;

    public BookServiceTest1(){ // models examples
        this.book = Book.builder().name("book_test").yearBook(2015).id(1).quant(10).build();

        this.customer = Customer.builder().id(1).cpf("845.454.254-22").firstName("firstName_customer")
                .lastName("lastName_customer").build();

        this.order = Order.builder().id(1).checkIn(LocalDate.now())
                .checkOut(LocalDate.now().plusDays(10)).book(book).customer(customer).build();
    }


    @Test
    public void shouldSaveCheckInBookSuccessful(){

        // verify book is available*
        // generate date checkOut
        // decrement quantity book
        // save checkIn book

        Assertions.assertNotNull(bookService.checkInBook(order).getId());

    }

    @Test
    public void shouldCheckOutBookSuccessful(){

        //find order details
        //verificar data de checkOut
        //calcular taxa por atraso
        //increment  quantity book
        //return value

        var expected = bookService.checkOutBook(order.getId());

        Assertions.assertEquals(10,expected);

    }

    @Test
    @DisplayName("Should return value after decrement book quantity")
    public void shouldDecrementBookQuantity(){

        int valueAfterDecrement = bookService.decrementQuantityBook(book.getId());

        Assertions.assertEquals(9,valueAfterDecrement);

    }

    @Test
    @DisplayName("Should return value after decrement book quantity")
    public void shouldIncrementBookQuantity(){

        int valueAfterIncrement = bookService.incrementQuantityBook(book.getId());

        Assertions.assertEquals(11,valueAfterIncrement);

    }


    @Test
    @DisplayName("Should return true if book is available")
    public void shouldReturnTrueVerifyBookIsAvailable(){

        int id = 1;
        boolean expected = bookService.bookIsAvailable(id);
        Assertions.assertTrue(expected);

    }

    @Test
    @DisplayName("Should throw exception if book is not found or not available")
    public void shouldThrowExceptionBookIsNotAvailable(){
        int id = 2;
        Assertions.assertThrows(Exception.class,
                ()-> bookService.bookIsAvailable(id),"Book is not available!");
    }



    @Test
    @DisplayName("should return late fee for days value")
    public void shouldReturnValueOutLateFee(){

        //taxa R$2,00 / day

        var dateCheckOut= LocalDate.now().minusDays(5);

        float value = bookService.CalculatorLateForDayFee(dateCheckOut);

        Assertions.assertEquals(10.0,value);

    }

    @Test
    @DisplayName("should return 0 when not have late fee")
    public void shouldReturnValueZeroOutLateFee(){

        //taxa R$2,00 / day

        var dateCheckOut= LocalDate.now();

        float value = bookService.CalculatorLateForDayFee(dateCheckOut);

        Assertions.assertEquals(0,value);

    }


    @Before
    public void setup (){

        Mockito.when(bookRepository.isAvailable(book.getId())).thenReturn(true);

        Mockito.when(orderRepository.save(order)).thenReturn(order);


        Order orderCheckOut = Order.builder().id(1).checkIn(LocalDate.now())
                .checkOut(LocalDate.now().minusDays(5)).book(book).customer(customer).build();
        Mockito.when(orderRepository.findById(order.getId())).thenReturn(Optional.of(orderCheckOut));


        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));


        Book bookQuantity = Book.builder().name("book_test").yearBook(2015).id(1).quant(10).build();
        Mockito.when(bookRepository.changeQuantityBook(book.getId(),(book.getQuant()-1))).thenReturn(9);

    }

}

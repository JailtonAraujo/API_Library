package com.br.apilibrary.service;

import com.br.apilibrary.entity.Book;
import com.br.apilibrary.entity.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookService {

    public Book save (Book book) throws Exception;

    public Book findByName(String name);

    public void delete (Integer id);

    public List<Book> findAll();

    public Optional<Book> findById(int id);

    public boolean bookIsAvailable(int id);

    public Order checkInBook(Order order);

    float CalculatorLateForDayFee(LocalDate dateCheckOut);

    public int decrementQuantityBook(int id);

    public Float checkOutBook(Integer id);

    public int incrementQuantityBook(int id);
}

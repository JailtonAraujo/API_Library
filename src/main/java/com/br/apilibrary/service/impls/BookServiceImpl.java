package com.br.apilibrary.service.impls;

import com.br.apilibrary.entity.Book;
import com.br.apilibrary.entity.Order;
import com.br.apilibrary.repository.BookRepository;
import com.br.apilibrary.repository.OrderRepository;
import com.br.apilibrary.service.BookService;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  BookRepository bookRepository;

  @Autowired
  OrderRepository orderRepository;

    @Override
    public Book save(Book book) throws Exception {

        if(bookRepository.findByName(book.getName()).isPresent()){
            throw new Exception("Book already exists!");
        }

        return bookRepository.save(book);
    }

    @Override
    public Book findByName(String name) {

        var optional = bookRepository.findByName(name);

        if(optional.isEmpty()){
            throw new NoResultException("Book not found");
        }

        return optional.get();
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAll() {

        var list = bookRepository.findAll();

        if(list.size() == 0){
            throw new NoResultException("Nothing books found");
        }

        return list;
    }

    @Override
    public Optional<Book> findById(int id) {

        var book = bookRepository.findById(id);

        if(book.isEmpty()){
            throw  new NoResultException("Book not found");
        }

        return book;

    }

    @Override
    public boolean bookIsAvailable(int id) {

        if(!bookRepository.isAvailable(id)){
            throw new NoResultException("Book is not available!");
        }

        return true;
    }

    @Override
    public Order checkInBook(Order order) {

        bookIsAvailable(order.getBook().getId()); // Verify if book is available

        order.setCheckIn(LocalDate.now());
        order.setCheckOut(LocalDate.now().plusDays(10));

        bookRepository.changeQuantityBook(order.getBook().getId(),(order.getBook().getQuant()-1));//Decrement book quantity

        return orderRepository.save(order);
    }

    @Override
    public float CalculatorLateForDayFee(LocalDate dateCheckOut) {

        int days = Period.between(dateCheckOut,LocalDate.now()).getDays();

        if (days <= 0){
            return 0;
        }

        float value = Float.valueOf(days*2);

        return value;
    }

    @Override
    public int decrementQuantityBook(int id) {

        int currentQuantity = bookRepository.findById(id).get().getQuant();

        bookRepository.changeQuantityBook(id,currentQuantity-1);

        return currentQuantity - 1;
    }

    @Override
    public int incrementQuantityBook(int id) {
        int currentQuantity = bookRepository.findById(id).get().getQuant();

        bookRepository.changeQuantityBook(id,currentQuantity+1);

        return currentQuantity + 1;
    }

    @Override
    public Float checkOutBook(Integer id) {

        Optional<Order> optional = orderRepository.findById(id);

        if(optional.isEmpty()){
            throw new NoResultException("Order not found!");
        }

        bookRepository.changeQuantityBook(optional.get().getBook().getId(), (optional.get().getBook().getQuant()+1));

        delete(id);

        return CalculatorLateForDayFee(optional.get().getCheckOut());

    }


}

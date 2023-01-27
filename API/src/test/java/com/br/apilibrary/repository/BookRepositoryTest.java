package com.br.apilibrary.repository;

import com.br.apilibrary.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    @Disabled
    void ExitsBookTest (){

        Book book = new Book();

        //give
        book.setName("Codigo da Vinci");
        book.setYearBook(2015);
        book.setQuant(30);

        //when
        var expected = repository.save(book);

        //then

    }


    @Test
    void BookIsAvailable(){

        Book book = new Book();

        //give
        book.setName("Codigo da Vinci");
        book.setYearBook(2015);
        book.setQuant(10);

        assertThat(repository.isAvailable(repository.save(book).getId())).isTrue();

    }

    @Test
    void BookIsNotAvailable(){

        Book book = new Book();

        //give
        book.setName("Codigo da Vinci");
        book.setYearBook(2015);
        book.setQuant(0);


        assertThat(repository.isAvailable(repository.save(book).getId())).isFalse();

    }

    @Test
    @Disabled
    void TestChangeQuantityBook(){

        Book book = new Book();

        book.setName("Codigo da Vinci");
        book.setYearBook(2015);
        book.setQuant(10);

        var bookSaved = repository.save(book);

        repository.changeQuantityBook(bookSaved.getId(),8);

//        var bookChanged = repository.findById(bookSaved.getId());

        Assertions.assertEquals(8,repository.findById(bookSaved.getId()).get().getQuant());

    }

}
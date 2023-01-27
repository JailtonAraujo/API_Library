package com.br.apilibrary.repository;

import com.br.apilibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    public Optional<Book> findByName(String name);


    @Query("SELECT CASE WHEN b.quant > 0 THEN \n" +
            "            TRUE ELSE FALSE END\n" +
            "            FROM tbl_book b\n" +
            "            WHERE b.id = ?1")
    public boolean isAvailable(int id);

    @Modifying
    @Transactional
    @Query("UPDATE tbl_book SET quant = ?2 WHERE id = ?1")
    public int changeQuantityBook(int bookId, int newQuantity);

}

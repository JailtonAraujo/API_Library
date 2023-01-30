package com.br.apilibrary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ForeignKey;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "tbl_order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 5384196513173905001L;

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    @ForeignKey(name = "FK_tblOrder_tblCustomer")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id")
    @ForeignKey(name = "FK_tblOrder_tblBook")
    private Book book;

    private LocalDate checkIn;

    private LocalDate checkOut;

}

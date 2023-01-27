package com.br.apilibrary.DTO;

import com.br.apilibrary.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends RepresentationModel {

    private Integer id;

    private Book book;

    private LocalDate checkIn;

    private LocalDate checkOut;

}

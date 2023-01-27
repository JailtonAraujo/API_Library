package com.br.apilibrary.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO extends RepresentationModel<BookDTO> {

    private int id;

    private String name;

    private int quant;

    private int yearBook;
}

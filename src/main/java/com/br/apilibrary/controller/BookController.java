package com.br.apilibrary.controller;

import com.br.apilibrary.DTO.BookDTO;
import com.br.apilibrary.DTO.OrderDTO;
import com.br.apilibrary.entity.Book;
import com.br.apilibrary.entity.Order;
import com.br.apilibrary.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<Book> save (@RequestBody Book book) throws Exception {
        return ResponseEntity.ok(this.bookService.save(book));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<BookDTO> findByName (@PathVariable(name = "name") String name ){

        BookDTO dto = modelMapper.map(this.bookService.findByName(name),BookDTO.class);

        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).findAll()).withRel("Book list"));

        return  ResponseEntity.ok( dto);
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDTO>> findAll (){

        List<Book> listBooks = this.bookService.findAll();

        List<BookDTO> dtos = listBooks.stream().map( book -> this.modelMapper.map(book,BookDTO.class)).collect(Collectors.toList());

        for( BookDTO dto : dtos ) {
            dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).findById(dto.getId())).withSelfRel());
        }

        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById( @PathVariable(name = "id") int id){

        var dto = this.modelMapper.map(this.bookService.findById(id).get(), BookDTO.class);

        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).findAll()).withRel("Books list"));

        return ResponseEntity.ok(dto);

    }

    @PostMapping("/check-in")
    public ResponseEntity<OrderDTO> checkInBook( @RequestBody Order order ){

        var dto = this.modelMapper.map(this.bookService.checkInBook(order), OrderDTO.class);

        return  ResponseEntity.ok(dto);

    }

    @PutMapping("/check-out/{id}")
    public ResponseEntity<Float> checkOutBook( @PathVariable(name = "id") int id ){

        return ResponseEntity.ok(this.bookService.checkOutBook(id));
    }

}

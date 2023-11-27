package exercise.controller;

import java.net.URI;
import java.util.List;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BookDTO> show(@PathVariable Long id) {
        var bookDTO = bookService.findById(id);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<BookDTO>> index() {
        var books = bookService.getAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(books.size()))
                .body(books);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<BookDTO> create(@Valid @RequestBody BookCreateDTO dto) {
        var url = URI.create("/books");
        var bookDTO = bookService.create(dto);
        return ResponseEntity.created(url)
                .body(bookDTO);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BookDTO> update(@RequestBody @Valid BookUpdateDTO dto, @PathVariable Long id) {
        var bookDTO = bookService.update(dto, id);
        return ResponseEntity.ok(bookDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }
    // END
}

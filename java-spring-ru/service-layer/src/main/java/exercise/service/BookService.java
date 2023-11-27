package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository repo;

    @Autowired
    private BookMapper mapper;

    public BookDTO findById(Long id) {
        var book = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        return mapper.map(book);
    }

    public List<BookDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::map)
                .toList();
    }

    public BookDTO create(BookCreateDTO dto) {
        var book = mapper.map(dto);
        repo.save(book);
        return mapper.map(book);
    }

    public BookDTO update(BookUpdateDTO dto, Long id) {
        var book = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        mapper.update(dto, book);
        repo.save(book);
        return mapper.map(book);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
    // END
}

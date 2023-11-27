package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository repo;

    @Autowired
    private AuthorMapper mapper;

    public AuthorDTO findById(Long id) {
        var author = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        return mapper.map(author);
    }

    public List<AuthorDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::map)
                .toList();
    }

    public AuthorDTO create(AuthorCreateDTO dto) {
        var author = mapper.map(dto);
        repo.save(author);
        return mapper.map(author);
    }

    public AuthorDTO update(AuthorUpdateDTO dto, Long id) {
        var author = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        mapper.update(dto, author);
        repo.save(author);
        return mapper.map(author);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
    // END
}

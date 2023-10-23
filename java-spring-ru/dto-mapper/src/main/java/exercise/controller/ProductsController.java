package exercise.controller;

import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    // BEGIN
    @GetMapping("")
    public List<ProductDTO> index() {
        return productRepository.findAll().stream()
                .map(p -> mapper.map(p))
                .toList();
    }

    @GetMapping("/{id}")
    public ProductDTO show(@PathVariable Long id) {
        return mapper.map(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id)));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO productCreateDTO) {
        var product = mapper.map(productCreateDTO);
        productRepository.save(product);
        return mapper.map(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update(@RequestBody ProductUpdateDTO updateDTO, @PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        mapper.update(updateDTO, product);
        productRepository.save(product);
        return mapper.map(product);
    }
    // END
}

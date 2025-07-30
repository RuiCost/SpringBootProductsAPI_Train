package com.example.produtoisapi.productManagement.api;

import com.example.produtoisapi.exceptions.ConflictException;
import com.example.produtoisapi.exceptions.NotFoundException;
import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.repositories.CategoryRepository;
import com.example.produtoisapi.userManagement.model.Role;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@Tag(name="Products",description = "Products Endpoints")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {


    private final CategoryRepository categoryRepository;

    private final CategoryViewMapper categoryViewMapper;

    @RolesAllowed({Role.ADMIN})
    @PostMapping(value = "/name")
    public ResponseEntity<CategoryView> create(@PathVariable("name") String name) {

        if (!name.equals(name.toUpperCase())) {
            throw new IllegalArgumentException("Category name must be in uppercase.");
        }

        final var category = categoryRepository.findByName(name);

        if (category.isPresent()) {
            throw new ConflictException("Category already exists!");
        }

        Category category1 = new Category(name);
        categoryRepository.save(category1);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryViewMapper.toCategoryView(category1));
    }

    @GetMapping
    public Iterable<CategoryView> findAll() {
        return categoryViewMapper.toCategoryView(categoryRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryView> findById(@PathVariable("id") final Long id) {

        final var category =categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(Category.class, id));
        return ResponseEntity.ok().body(categoryViewMapper.toCategoryView(category));
    }


    @RolesAllowed({Role.ADMIN})
    @DeleteMapping(value = "{id}")
    public ResponseEntity<CategoryView> delete(@PathVariable("id")  final Long id) {
        final var category =categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(Category.class, id));
        categoryRepository.deleteById(id);
        return  ResponseEntity.noContent().build();
    }















}

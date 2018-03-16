package com.nevermind.bu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nevermind.bu.entity.Category;
import com.nevermind.bu.service.interfaces.CategoryService;
import com.nevermind.bu.utils.GenericEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller for Category
 *
 * @author Roman Kovaliov
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    private ObjectMapper mapper = new ObjectMapper();
    private Gson gson = new Gson();

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getCategoryById(@PathVariable int id) throws GenericEngineException {
        Category categoryById = categoryService.getById(id);
        if (categoryById != null) {
            JsonObject jsonObject;
            try {
                jsonObject = gson.fromJson(mapper.writeValueAsString(categoryById), JsonObject.class);
            } catch (Exception e) {
                throw new GenericEngineException(e);
            }
            assert jsonObject != null;
            jsonObject.addProperty("total", categoryById.getProducts().size());
            return new ResponseEntity<>(String.valueOf(jsonObject), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category Id not found :  " + id, HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<String> getCategoryByParam(@RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "description", required = false) String description)
            throws GenericEngineException {

        Category category;
        JsonObject jsonObject;

        try {
            if (name != null) {
                category = categoryService.getByName(name);
                jsonObject = gson.fromJson(mapper.writeValueAsString(category), JsonObject.class);
                assert jsonObject != null;
                jsonObject.addProperty("total", category.getProducts().size());
                return new ResponseEntity<>(String.valueOf(jsonObject), HttpStatus.OK);
            }
            if (description != null) {
                category = categoryService.getByDescription(description);
                jsonObject = gson.fromJson(mapper.writeValueAsString(category), JsonObject.class);
                assert jsonObject != null;
                jsonObject.addProperty("total", category.getProducts().size());
                return new ResponseEntity<>(String.valueOf(jsonObject), HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new GenericEngineException(e);
        }
        return null;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        categoryService.save(category);
        Category newCategory = categoryService.getById(category.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newCategory.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void updateCategory(@RequestBody Category category, @PathVariable int id) {
        Category categoryById = categoryService.getById(id);

        categoryById.setName(category.getName());
        categoryById.setDescription(category.getDescription());
        categoryById.setProducts(category.getProducts());

        categoryService.save(categoryById);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable int id) {
        categoryService.delete(id);
    }
}

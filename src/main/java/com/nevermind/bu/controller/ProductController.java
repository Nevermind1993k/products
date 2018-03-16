package com.nevermind.bu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevermind.bu.entity.Product;
import com.nevermind.bu.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller for Product
 *
 * @author Roman Kovaliov
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ProductService productService;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getProductById(@PathVariable int id) throws JsonProcessingException {
        Product productById = productService.getById(id);
        if (productById != null) {
            return new ResponseEntity<>(mapper.writeValueAsString(productById), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product id not found :  " + id, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<String> getProductByParam(@RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value = "description", required = false) String description)
            throws JsonProcessingException {

        Product product;

        if (name != null) {
            product = productService.getByName(name);
            if (product != null) {
                return new ResponseEntity<>(mapper.writeValueAsString(product), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product name not found :  " + name, HttpStatus.NOT_FOUND);
            }
        }
        if (description != null) {
            product = productService.getByDescription(description);
            if (product != null) {
                return new ResponseEntity<>(mapper.writeValueAsString(product), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product description not found :  " + description, HttpStatus.NOT_FOUND);
            }
        }
        return null;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        productService.save(product);
        Product newProduct = productService.getById(product.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody Product product, @PathVariable int id) {
        Product productById = productService.getById(id);

        productById.setName(product.getName());
        productById.setDescription(product.getDescription());
        productById.setPrice(product.getPrice());
        productById.setCategory(product.getCategory());

        productService.save(productById);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable int id) {
        productService.delete(id);
    }
}

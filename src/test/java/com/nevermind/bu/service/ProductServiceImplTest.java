package com.nevermind.bu.service;

import com.nevermind.bu.entity.Category;
import com.nevermind.bu.entity.Product;
import com.nevermind.bu.service.interfaces.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

/**
 * Tests for Product Service
 * @author Roman Kovaliov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Mock
    @Autowired
    ProductService productService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById() throws Exception {
        Product product = new Product();
        product.setName("Name");
        product.setDescription("Description");
        product.setCategory(new Category());
        product.setPrice(99.99);

        when(productService.getById(anyInt())).thenReturn(product);

        Product byId = productService.getById(555);
        assertEquals("Name", byId.getName());

        verify(productService).getById(555);
    }

    @Test
    public void getByName() throws Exception {
        Product product = new Product();
        product.setName("Name");
        product.setDescription("Description");
        product.setCategory(new Category());
        product.setPrice(99.99);
        when(productService.getByName(anyString())).thenReturn(product);

        Product byName = productService.getByName("Name");
        assertEquals("Name", byName.getName());

        verify(productService).getByName("Name");
    }

    @Test
    public void getByDescription() throws Exception {
        Product product = new Product();
        product.setName("Name");
        product.setDescription("Description");
        product.setCategory(new Category());
        product.setPrice(99.99);
        when(productService.getByDescription(anyString())).thenReturn(product);

        Product byDescription = productService.getByDescription("Description");
        assertEquals("Description", byDescription.getDescription());

        verify(productService).getByDescription("Description");
    }

    @Test
    public void getAll() throws Exception {
        List<Product> all = new ArrayList<>();
        all.add(new Product());
        all.add(new Product());
        when(productService.getAll()).thenReturn(all);

        List<Product> result = productService.getAll();
        verify(productService).getAll();
    }

    @Test
    public void save() throws Exception {
        Product product = new Product();
        product.setName("New Product");
        productService.save(product);
        verify(productService).save(product);
    }

    @Test
    public void delete() throws Exception {
        productService.delete(5);
        verify(productService).delete(5);
    }

    @Test
    public void update() throws Exception {
        Product product = new Product();
        when(productService.getById(anyInt())).thenReturn(product);
        product = productService.getById(3);
        product.setName("New Name");

        productService.update(product);
        verify(productService).update(product);
    }

    @Test
    public void testServiceCalledOnlyOnce() {
        List all = new ArrayList();

        when(productService.getAll()).thenReturn(all);

        productService.getAll();

        verify(productService,times(1)).getAll();
        verify(productService,atMost(1)).getAll();
        verify(productService,atLeast(1)).getAll();
    }

}

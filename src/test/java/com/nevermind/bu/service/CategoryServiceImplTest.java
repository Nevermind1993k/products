package com.nevermind.bu.service;

import com.nevermind.bu.entity.Category;
import com.nevermind.bu.service.interfaces.CategoryService;
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
 * Tests for Category Service
 * @author Roman Kovaliov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Mock
    @Autowired
    CategoryService categoryService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById() throws Exception {
        Category category = new Category();
        category.setName("testCategory");
        category.setDescription("testCategoryDesc");
        when(categoryService.getById(anyInt())).thenReturn(category);

        Category byId = categoryService.getById(5);
        assertEquals("testCategory", byId.getName());

        verify(categoryService).getById(5);
    }

    @Test
    public void getByName() throws Exception {
        Category category = new Category();
        category.setName("HelloCategory");
        category.setDescription("testCategoryDesc");
        when(categoryService.getByName(anyString())).thenReturn(category);

        Category byName = categoryService.getByName("HelloCategory");
        assertEquals("HelloCategory", byName.getName());

        verify(categoryService).getByName("HelloCategory");
    }

    @Test
    public void getByDescription() throws Exception {
        Category category = new Category();
        category.setName("HelloCategory");
        category.setDescription("Test Description");
        when(categoryService.getByDescription(anyString())).thenReturn(category);

        Category byDescription = categoryService.getByDescription("Test Description");
        assertEquals("Test Description", byDescription.getDescription());

        verify(categoryService).getByDescription("Test Description");
    }

    @Test
    public void getAll() throws Exception {
        List<Category> all = new ArrayList<>();
        all.add(new Category());
        all.add(new Category());
        when(categoryService.getAll()).thenReturn(all);

        List<Category> result = categoryService.getAll();
        verify(categoryService).getAll();
    }

    @Test
    public void save() throws Exception {
        Category category = new Category();
        category.setName("New Category");
        categoryService.save(category);
        verify(categoryService).save(category);
    }

    @Test
    public void delete() throws Exception {
        categoryService.delete(5);
        verify(categoryService).delete(5);
    }

    @Test
    public void update() throws Exception {
        Category category = new Category();
        when(categoryService.getById(anyInt())).thenReturn(category);
        category = categoryService.getById(3);
        category.setName("New Name");

        categoryService.update(category);
        verify(categoryService).update(category);
    }

    @Test
    public void testServiceCalledOnlyOnce() {
        List all = new ArrayList();

        when(categoryService.getAll()).thenReturn(all);

        categoryService.getAll();

        verify(categoryService,times(1)).getAll();
        verify(categoryService,atMost(1)).getAll();
        verify(categoryService,atLeast(1)).getAll();
    }
}

package org.demo.springJdbcBookshop.context;

import org.demo.springJdbcBookshop.BookServices;
import org.demo.springJdbcBookshop.BookshopBookRelServices;
import org.demo.springJdbcBookshop.BookshopServices;
import org.demo.springJdbcBookshop.CategoryServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ContextTest {

    @Autowired
    BookshopServices bookshopServices;
    @Autowired
    BookServices bookServices;
    @Autowired
    BookshopBookRelServices bookshopBookRelServices;
    @Autowired
    CategoryServices categoryServices;


    @Test
    public void ensureBookshopServiceNotNull(){
        assertThat(bookshopServices).isNotNull();
    }

    @Test
    public void ensureBookServiceNotNull(){
        assertThat(bookServices).isNotNull();
    }

    @Test
    public void ensureBookshopBookRelServiceNotNull(){
        assertThat(bookshopBookRelServices).isNotNull();
    }

    @Test
    public void ensureCategoryServiceNotNull(){
        assertThat(categoryServices).isNotNull();
    }
}

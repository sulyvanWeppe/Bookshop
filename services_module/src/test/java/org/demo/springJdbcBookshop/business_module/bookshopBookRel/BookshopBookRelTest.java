package org.demo.springJdbcBookshop.business_module.bookshopBookRel;

import junit.framework.Assert;
import org.assertj.core.api.Assertions;
import org.demo.springJdbcBookshop.BookManagerImpl;
import org.demo.springJdbcBookshop.BookshopBookRelManagerImpl;
import org.demo.springJdbcBookshop.BookshopManagerImpl;
import org.demo.springJdbcBookshop.CategoryManagerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookshopBookRelTest {

    @Autowired
    BookshopBookRelManagerImpl bookshopBookRelManager;
    @Autowired
    BookshopManagerImpl bookshopManager;
    @Autowired
    BookManagerImpl bookManager;
    @Autowired
    CategoryManagerImpl categoryManager;

    @Test
    public void BBRCreationThenDeletion(){
        String bookshopName = "bookshopTestName";
        String bookshopAddress = "bookshopTestAddress";
        String bookTitle = "bookTestTitle";
        String bookAuthor = "bookTestAuthor";
        String categoryName = "categoryTestName";

        //Create category
        categoryManager.createEntity(categoryName);
        int categoryId = categoryManager.getEntity(categoryName).getId();
        //Create bookshop
        bookshopManager.createEntity(bookshopName, bookshopAddress);
        int bookshopId = bookshopManager.getEntity(bookshopName, bookshopAddress).getId();
        //Create book
        bookManager.createEntity(bookTitle, bookAuthor, categoryId);
        int bookId = bookManager.getEntity(bookTitle, bookAuthor, categoryId).getId();
        //Create bookshop book relation
        bookshopBookRelManager.createEntity(bookshopId, bookId);
        //Check the relation was correctly created
        Assertions.assertThat(bookshopBookRelManager.exists(bookshopId, bookId));
        //Delete the book (Rk: this will automatically delete the relation + bookshop + category, because they will be orphans)
        bookManager.deleteEntity(bookId);
    }
}

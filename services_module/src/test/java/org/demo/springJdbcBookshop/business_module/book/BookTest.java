package org.demo.springJdbcBookshop.business_module.book;

import org.assertj.core.api.Assertions;
import org.demo.springJdbcBookshop.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookTest {

    @Autowired
    BookManagerImpl bookManager;
    @Autowired
    CategoryManagerImpl categoryManager;

    @Test
    public void bookCreationThenDeletion()
    {
        String categoryName = "categoryTestName";
        String bookTitle = "bookTestTitle";
        String bookAuthor = "bookTestAuthor";

        //Create the category
        categoryManager.createEntity(categoryName);
        Category category = categoryManager.getEntity(categoryName);
        int categoryId = category.getId();
        //Create the Book
        bookManager.createEntity(bookTitle,bookAuthor,categoryId);
        //Check the book was created correctly
        Book book = bookManager.getEntity(bookTitle,bookAuthor,categoryId);
        Assertions.assertThat(book).isNotNull();
        int bookId = book.getId();
        //Delete the book
        bookManager.deleteEntity(bookId);
        //Check the book was correctly deleted
        Assertions.assertThat(!bookManager.exists(bookId));
        //Rk: no need to delete the category as the created one is considered orphan and then deleted when deleting the book
    }

}

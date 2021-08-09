package org.demo.springJdbcBookshop.business_module.bookshop;

import org.demo.springJdbcBookshop.Bookshop;
import org.demo.springJdbcBookshop.BookshopManagerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;

@SpringBootTest
public class BookshopTest {

    @Autowired
    BookshopManagerImpl bookshopManager;

    @Test
    public void bookshopCreationThenDeletion()
    {
        String name = "bookshopTestName";
        String address = "bookshopTestAddress";
        //Create bookshop
        bookshopManager.createEntity(name, address);
        //Check the bookshop was correctly created
        Bookshop newBookshop = bookshopManager.getEntity(name, address);
        Assertions.assertThat(newBookshop).isNotNull();
        int newBookshopId = newBookshop.getId();
        //Delete the newly created bookshop
        bookshopManager.deleteEntity(newBookshopId);
        //Check the bookshop was correctly deleted
        Assertions.assertThat(!bookshopManager.exists(newBookshopId));
    }
}

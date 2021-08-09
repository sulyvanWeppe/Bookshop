package org.demo.springJdbcBookshop.business_module.category;

import org.assertj.core.api.Assertions;
import org.demo.springJdbcBookshop.Category;
import org.demo.springJdbcBookshop.CategoryManagerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryTest {

    @Autowired
    CategoryManagerImpl categoryManager;

    @Test
    public void categoryCreationThenDeletion(){
        String name = "categroyTestName";

        //Create the category
        categoryManager.createEntity(name);
        //Check the category was correctly created
        Category newCategory = categoryManager.getEntity(name);
        Assertions.assertThat(newCategory).isNotNull();
        int newCategoryId = newCategory.getId();
        //Delete the new category
        categoryManager.deleteEntity(newCategoryId);
        //Check the category was correctly deleted
        Assertions.assertThat(!categoryManager.exists(newCategoryId));
    }
}

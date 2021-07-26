package org.demo.springJdbcBookshop;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public interface CategoryDAO {

    /*Create a new Category*/
    public void createCategory(String name);

    /*Get the category with the given id*/
    public Category getCategory(int id);

    /*Get the category with the given name*/
    public Category getCategory(String name);

    /*Delete the category with the given id*/
    public void deleteCategory(int id);

    /*Update the name of the category with the given id*/
    public void updateCategoryName(int id, String name);

    /*Delete all Category which are not referenced by any book*/
    public void cleanUp();

    /*Indicates if there is a category with the given id*/
    public boolean exists(int id);
    /*Indicates if there is a category with the given name*/
    public boolean exists(String name);
}

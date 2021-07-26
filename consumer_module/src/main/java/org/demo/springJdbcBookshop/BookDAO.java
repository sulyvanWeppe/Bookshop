package org.demo.springJdbcBookshop;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public interface BookDAO {
    /*Create book with the given title, author and category*/
    public void createBook(String title, String author, int catId);

    /*Delete the book with the given id*/
    public void deleteBook(int id);

    /*Get the book with the given id*/
    public Book getBook(int id);
    /*Get the book with the given title, author and category*/
    public Book getBook(String title, String author, int catId);

    /*Change to title the current title of the book with the given id*/
    public void updateBookTitle(int id, String title);
    /*Change to author the current author of the book with the given id*/
    public void updateBookAuthor(int id, String author);
    /*Change to title the current title of the book with the given id*/
    public void updateBookCatId(int id, int catId);

    /*Indicates if there is a book with the given id*/
    public boolean exists(int id);
    /*Indicates if there is a book with the given title, author, categoryId*/
    public boolean exists(String title, String author, int categoryId);
    /*Indicates if there is a book with the given category*/
    public boolean existsWCategory(int categoryId);

    /*Delete all orphan books*/
    public void cleanUp();
}

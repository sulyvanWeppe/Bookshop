package org.demo.springJdbcBookshop;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public interface BookshopDAO {
    /*Create bookshop with the given name and address*/
    public void createBookshop(String name, String address);

    /*Get bookshop with the given id*/
    public Bookshop getBookshop(int id);

    /*Get bookshop with the given name and address*/
    public Bookshop getBookshop(String name, String address);

    /*Delete bookshop with the given id*/
    public void deleteBookshop(int id);

    /*Update the name of the bookshop with the given id*/
    public void updateBookshopName(int id, String name);

    /*Update the address of the bookshop with the given id*/
    public void updateBookshopAddress(int id, String address);

    /*Indicates if there is a bookshop with the given id*/
    public boolean exists(int id);
    /*Indicates if there is a bookshop with the given name and address*/
    public boolean exists(String name, String address);

    /*Clean up all the orphans bookshop*/
    public void cleanUp();
}

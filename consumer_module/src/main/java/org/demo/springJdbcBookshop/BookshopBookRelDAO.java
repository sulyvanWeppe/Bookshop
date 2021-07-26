package org.demo.springJdbcBookshop;

import java.util.List;

public interface BookshopBookRelDAO {

    /*Create Bookshop-Book relation with the given Ids*/
    public void createBookshopBookRel(int bookshopId, int bookId);

    /*Create Bookshop-Book relation with the given Ids*/
    public void createBookshopBookRel(int bookshopId, int bookId, BookshopDAOImpl bookshopDAO, BookDAOImpl bookDAO);

    /*Delete Bookshop-book relation with the given Ids*/
    public void deleteBookshopBookRel(int bookshopId, int bookId);

    /*Delete Bookshop-Book relation with the given Ids*/
    public void deleteBookshopBookRel(int bookshopId, int bookId, BookshopDAOImpl bookshopDAO, BookDAOImpl bookDAO);

    /*Delete all relations with the given book id*/
    public void deleteWBook(int bookId);

    /*Delete all relations with the given bookshop id*/
    public void deleteWBookshop(int bookshopId);

    /*Indicates if there is a relation between the given Ids*/
    public boolean exists(int bookshopId, int bookId);

    /*Indicates if there is at least one book linked to the given bookshop*/
    public boolean existsWBookshop(int bookshopId);

    /*Indicates if there is at least one bookshop linked to the given book*/
    public boolean existsWBook(int BookId);

    /*Get bookshop-book relation with the given bookshopId and bookId*/
    public BookshopBookRel getBookshopBookRel(int bookshopId, int bookId);

    /*Get all Books linked to the given bookshop*/
    public List<Book> getLinkedBook(int bookshopId);

    /*Get the nb of relation which include the given book id*/
    public int getNbRelWBook(int bookId);

    /*Get the nb of relation which include the given bookshop id*/
    public int getNbRelWBookshop(int bookshopId);

    /*Indicates if the given book is linked to more than one bookshop*/
    public boolean isLinkedToMultipleBookshop(int bookId);
    public boolean isLinkedToMultipleBookshop(Book book);

    /*Indicates if the given bookshop is linked to more than one book*/
    public boolean isLinkedToMultipleBook(int bookshopId);
    public boolean isLinkedToMultipleBook(Bookshop bookshop);
}

package org.demo.springJdbcBookshop;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class BookshopManagerImpl {
    @Getter
    @Setter
    private BookshopDAO bookshopDAO;
    @Getter
    @Setter
    private BookDAO bookDAO;
    @Getter
    @Setter
    private BookshopBookRelDAO bookshopBookRelDAO;
    @Getter
    @Setter
    private CategoryDAO categoryDAO;

    public void createEntity(String name, String address) {
        //1. Check if the bookshop does not already exist
        boolean bookshopAlreadyExists = bookshopDAO.exists(name, address);
        if (bookshopAlreadyExists)
        {
            log.error("ERROR : got an error when trying to create bookshop: "+name+" located: "+address+" because it already exists");
            return;
        }

        //2. If not create it
        bookshopDAO.createBookshop(name, address);
    }

    public Bookshop getEntity(int entityId) {
        Bookshop bookshop = bookshopDAO.getBookshop(entityId);
        //Check the got bookshop is OK
        if (bookshop != null)
            {return bookshop;}
        else
            {return null;}
    }

    public Bookshop getEntity(String name, String address)
    {
        Bookshop bookshop = bookshopDAO.getBookshop(name, address);

        return bookshop;
    }

    public void updateBookshopName(int id, String name)
    {
        bookshopDAO.updateBookshopName(id, name);
    }

    public void updateBookshopAddress(int id, String address)
    {
        bookshopDAO.updateBookshopName(id, address);
    }

    public void updateBookshop(int id, String name, String address)
    {
        //Update its name
        bookshopDAO.updateBookshopName(id, name);
        //Update its address
        bookshopDAO.updateBookshopAddress(id, address);
    }

    public void deleteEntity(int entityId) {
        //1. Check if it is link to any book (bookshop_book_rel)
        boolean existsRelWBookshop = bookshopBookRelDAO.existsWBookshop(entityId);
        if (!existsRelWBookshop)
        {//No book link to that bookshop => it can be safely removed
            bookshopDAO.deleteBookshop(entityId);
        }
        else{//Books link to that bookshop
            List<Book> books = bookshopBookRelDAO.getLinkedBook(entityId);
            //1. Get books only linked to that bookshop (the one to be deleted)
            List<Book> bookOnlyLinkedTo1Bookshop = books.stream().filter(Predicate.not(bookshopBookRelDAO::isLinkedToMultipleBookshop)).collect(Collectors.toList());
            //2. Delete all bookshop-book rel which include the bookshop: entityId
            this.bookshopBookRelDAO.deleteWBookshop(entityId);
            //3. Delete all bookOnlyLinkedTo1Bookshop
            bookOnlyLinkedTo1Bookshop.stream().map(Book::getId).forEach(bookDAO::deleteBook);
            //4. Clean up the orphans categories
            this.categoryDAO.cleanUp();
            //5. Delete the bookshop itself
            bookshopDAO.deleteBookshop(entityId);
        }
    }

    public boolean exists(int entityId) {
        return bookshopDAO.exists(entityId);
    }
}

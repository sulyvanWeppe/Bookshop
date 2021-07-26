package org.demo.springJdbcBookshop;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookshopBookRelManagerImpl {
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

    public void createEntity(int bookshopId, int bookId) {
        //1. Check if the bookshop-book relation does not already exist
        boolean relationAlreadyExists = bookshopBookRelDAO.exists(bookshopId, bookId);
        if (relationAlreadyExists) {
            log.error("ERROR : got an error when trying to create the relation between bookshop: '" + bookshopDAO.getBookshop(bookshopId).getName() + "' and book: '" + bookDAO.getBook(bookId).getTitle() + " because it already exists");
            return;
        }

        //2. If not create it
        bookshopBookRelDAO.createBookshopBookRel(bookshopId, bookId, (BookshopDAOImpl) bookshopDAO, (BookDAOImpl) bookDAO);
    }

    public BookshopBookRel getEntity(int bookshopId, int bookId) {
        BookshopBookRel bookshopBookRel = bookshopBookRelDAO.getBookshopBookRel(bookshopId, bookId);
        boolean bookshopBookRelNotNull = (bookshopBookRel != null);

        if (bookshopBookRelNotNull)
        {return bookshopBookRel;}
        else{
            return null;
        }
    }

    public void deleteEntity(int bookshopId, int bookId) {
        //1. Delete the relation
        bookshopBookRelDAO.deleteBookshopBookRel(bookshopId, bookId);
        //2. Clean up orphan bookshops
        bookshopDAO.cleanUp();
        //3. Clean up orphan books
        bookDAO.cleanUp();
        //4. Clean up orphan categories
        categoryDAO.cleanUp();
    }

    public boolean exists(int bookshopId, int bookId) {
        return bookshopBookRelDAO.exists(bookshopId,bookId);
    }
}

package org.demo.springJdbcBookshop;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookManagerImpl {
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

    public void createEntity(String title, String author, int categoryId) {
        //1. Check if the book does not already exist
        boolean bookAlreadyExists = bookDAO.exists(title, author, categoryId);
        if (bookAlreadyExists)
        {
            log.error("ERROR : got an error when trying to create book: '"+title+"' written by: "+author+" of category: "+categoryDAO.getCategory(categoryId).getName()+" because it already exists");
            return;
        }

        //2. If not create it
        bookDAO.createBook(title, author, categoryId);
    }

    public Book getEntity(int entityId) {
        Book book = bookDAO.getBook(entityId);
        boolean bookNotNull = (book != null);

        if (bookNotNull)
        {
            return book;
        }
        else
        {
            return null;
        }
    }

    public Book getEntity(String title, String author, int catId) {
        Book book = bookDAO.getBook(title, author, catId);
        boolean bookNotNull = (((Integer)book.getId()) != null);

        if (bookNotNull)
        {
            return book;
        }
        else
        {
            return null;
        }
    }

    public void updateBookTitle(int id, String title)
    {
        bookDAO.updateBookTitle(id, title);
    }

    public void updateBookAuthor(int id, String author)
    {
        bookDAO.updateBookAuthor(id, author);
    }

    public void updateBookCatId(int id, int catId)
    {
        bookDAO.updateBookCatId(id, catId);
    }

    public void updateBook(int id, String title, String author, int catId)
    {
        //Update its title
        bookDAO.updateBookTitle(id, title);
        //Update its author
        bookDAO.updateBookAuthor(id, author);
        //Update its category id
        bookDAO.updateBookCatId(id, catId);
    }


    public void deleteEntity(int entityId) {
        //1. Delete all bookshop-book relations with that book(entityId)
        bookshopBookRelDAO.deleteWBook(entityId);
        //2. Clean up orphans bookshops
        bookshopDAO.cleanUp();
        //3. Delete the book (entityId)
        bookDAO.deleteBook(entityId);
        //4. Clean up orphans categories
        categoryDAO.cleanUp();
    }

    public boolean exists(int entityId) {
        return bookDAO.exists(entityId);
    }
}

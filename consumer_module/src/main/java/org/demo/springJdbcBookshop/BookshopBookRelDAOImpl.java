package org.demo.springJdbcBookshop;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class BookshopBookRelDAOImpl implements BookshopBookRelDAO {

    @Getter
    @Setter
    private DataSource dataSource;
    @Getter
    @Setter
    private JdbcTemplate jdbcTemplate;
    @Getter
    @Setter
    private BookDAO bookDAO;

    @Override
    public void createBookshopBookRel(int bookshopId, int bookId) {
        //Creating the new Bookshop book relation
        String sql = "insert into bookshop_book_rel values(?, ?);";
        jdbcTemplate.update(sql, bookshopId, bookId);

        log.info("Creation of the bookshop-book relation with bookshop's Id: "+bookshopId+" and book's Id: "+bookId);
    }

    @Override
    public void createBookshopBookRel(int bookshopId, int bookId, BookshopDAOImpl bookshopDAO, BookDAOImpl bookDAO) {
        //Getting Bookshop and book info
        Bookshop bookshop = bookshopDAO.getBookshop(bookshopId);
        Book book = bookDAO.getBook(bookId);

        //Creating the new Bookshop book relation
        String sql = "insert into bookshop_book_rel values(?, ?);";
        jdbcTemplate.update(sql, bookshopId, bookId);

        log.info("Creation of the bookshop-book relation with bookshop: "+bookshop.getName()+" and book: "+book.getTitle());
    }

    @Override
    public void deleteBookshopBookRel(int bookshopId, int bookId, BookshopDAOImpl bookshopDAO, BookDAOImpl bookDAO) {
        //Getting the info of the relation to be deleted
        Bookshop bookshop = bookshopDAO.getBookshop(bookshopId);
        Book book = bookDAO.getBook(bookId);

        //Deletion of the relation
        String sql = "delete from bookshop_book_rel where bookshop_id = ? and book_id = ?;";
        jdbcTemplate.update(sql, bookshopId, bookId);

        log.info("Deletion of the bookshop-book relation with bookshop: "+bookshop.getName()+" and book: "+book.getTitle());
    }

    @Override
    public void deleteBookshopBookRel(int bookshopId, int bookId) {
        String sql = "delete from bookshop_book_rel where bookshop_id = ? and book_id = ?;";
        jdbcTemplate.update(sql, bookshopId, bookId);

        log.info("Deletion of the bookshop-book relation with bookshop's ID: "+bookshopId+" and book's Id: "+bookId);
    }

    @Override
    public void deleteWBook(int bookId) {
        String sql = "delete from bookshop_book_rel where book_id = ?;";
        jdbcTemplate.update(sql, bookId);

        log.info("Deletion of the bookshop-book relations with book's Id: "+bookId);
    }

    @Override
    public void deleteWBookshop(int bookshopId) {
        String sql = "delete from bookshop_book_rel where bookshop_id = ?;";
        jdbcTemplate.update(sql, bookshopId);

        log.info("Deletion of the bookshop-book relation with bookshop's ID: "+bookshopId);
    }

    @Override
    public boolean exists(int bookshopId, int bookId) {
        String sql = "select count(*) from bookshop_book_rel where bookshop_id = ? and book_id = ?;";
        int nbCorrespondingRel = jdbcTemplate.queryForObject(sql, Integer.class, bookshopId, bookId);

        return nbCorrespondingRel > 0;
    }

    @Override
    public boolean existsWBookshop(int bookshopId) {
        String sql = "select count(*) from bookshop_book_rel where bookshop_id = ?;";
        int nbCorrespondingRel = jdbcTemplate.queryForObject(sql, Integer.class, bookshopId);

        return nbCorrespondingRel > 0;
    }

    @Override
    public boolean existsWBook(int bookId) {
        String sql = "select count(*) from bookshop_book_rel where book_id = ?;";
        int nbCorrespondingRel = jdbcTemplate.queryForObject(sql, Integer.class, bookId);

        return nbCorrespondingRel > 0;
    }

    @Override
    public BookshopBookRel getBookshopBookRel(int bookshopId, int bookId) {
        BookshopBookRel bookshopBookRel  = null;

        //Check if the relation exists
        if (exists(bookshopId, bookId))
        {
            String sql = "select * from bookshop_book_rel where bookshop_id = ? and book_id = ?;";
            bookshopBookRel = (BookshopBookRel) jdbcTemplate.queryForObject(sql, new BookshopBookRelMapper(), bookshopId,bookId);
        }

        return bookshopBookRel;
    }

    @Override
    public List<Book> getLinkedBook(int bookshopId) {
        ArrayList<Integer> booksIds = new ArrayList<>();
        LinkedList<Book> books = null;

        //Check if there is at least one relation
        if (existsWBookshop(bookshopId))
        {
            books = new LinkedList<>();
            String sql = "select book_id from bookshop_book_rel where bookshop_id = ?;";
            booksIds = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class, bookshopId);

            Iterator<Integer> booksIdsIt = booksIds.iterator();
            while(booksIdsIt.hasNext())
            {
                books.add(bookDAO.getBook(booksIdsIt.next()));
            }
        }

        return books;
    }

    @Override
    public int getNbRelWBook(int bookId) {
        String sql = "select count(*) from bookshop_book_rel where book_id = ?;";
        int nbRel = jdbcTemplate.queryForObject(sql, Integer.class, bookId);

        return nbRel;
    }

    @Override
    public int getNbRelWBookshop(int bookshopId) {
        String sql = "select count(*) from bookshop_book_rel where bookshop_id = ?;";
        int nbRel = jdbcTemplate.queryForObject(sql, Integer.class, bookshopId);

        return nbRel;
    }

    @Override
    public boolean isLinkedToMultipleBookshop(int bookId) {
        return getNbRelWBookshop(bookId)>0;
    }

    @Override
    public boolean isLinkedToMultipleBook(int bookshopId) {
        return getNbRelWBook(bookshopId)>0;
    }

    @Override
    public boolean isLinkedToMultipleBookshop(Book book) {
        return isLinkedToMultipleBookshop(book.getId());
    }

    @Override
    public boolean isLinkedToMultipleBook(Bookshop bookshop) {
        return isLinkedToMultipleBook(bookshop.getId());
    }
}

package org.demo.springJdbcBookshop;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
public class BookDAOImpl implements BookDAO {

    @Getter
    @Setter
    private DataSource dataSource;
    @Getter
    @Setter
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createBook(String title, String author, int catId) {
        String sql = "insert into book(title, author, category_id) values(?, ?, ?);";
        this.jdbcTemplate.update(sql, title, author, catId);
        log.info("Creation of the book '"+title+"' from "+author);
    }

    @Override
    public void deleteBook(int id) {
        //Getting info from the book to be deleted
        String sql = "select * from book where id = ?;";
        Book book = (Book) jdbcTemplate.queryForObject(sql, new BookMapper(), id);

        //Deletion of the book
        sql = "delete from book where id = ?;";
        jdbcTemplate.update(sql, book.getId());

        log.info("Deletion of the book '"+book.getTitle()+" from "+book.getAuthor());
    }

    @Override
    public Book getBook(int id) {
        Book book = null;

        //Check if the book exists
        if (exists(id))
        {
            String sql = "select * from book where id = ?;";
            book = (Book) jdbcTemplate.queryForObject(sql, new BookMapper(), id);
        }

        return book;
    }

    @Override
    public Book getBook(String title, String author, int catId) {
        Book book = null;

        //Check if the book exists
        if (exists(title, author, catId))
        {
            String sql = "select * from book where title = ? and author = ? and category_id = ?;";
            book = (Book) jdbcTemplate.queryForObject(sql, new BookMapper(), title, author, catId);
        }

        return book;
    }

    @Override
    public void updateBookTitle(int id, String title) {
        //1. Get before info of the book to be updated
        String sql = "select * from book where id = ?;";
        Book book = (Book) jdbcTemplate.queryForObject(sql, new BookMapper(), id);

        //2.Perform the title update
        sql = "update book set title = ? where id = ?;";
        jdbcTemplate.update(sql, title, id);

        log.info("Update of book (id="+id+"): title was '"+book.getTitle()+"' and is now '"+title+"'");
    }

    @Override
    public void updateBookAuthor(int id, String author) {
        //1. Get before info of the book to be updated
        String sql = "select * from book where id = ?;";
        Book book = (Book) jdbcTemplate.queryForObject(sql, new BookMapper(), id);

        //2.Perform the author update
        sql = "update book set author = ? where id = ?;";
        jdbcTemplate.update(sql, author, id);

        log.info("Update of book (id="+id+"): author was '"+book.getAuthor()+"' and is now '"+author+"'");
    }

    @Override
    public void updateBookCatId(int id, int catId) {
        //1. Get before info of the book to be updated
        String sql = "select * from book where id = ?;";
        Book book = (Book) jdbcTemplate.queryForObject(sql, new BookMapper(), id);

        //2.Perform the category update
        sql = "update book set category_id = ? where id = ?;";
        jdbcTemplate.update(sql, catId, id);

        log.info("Update of book (id="+id+"): category id was '"+book.getCat_id()+"' and is now '"+catId+"'");
    }

    @Override
    public boolean exists(int id) {
        String sql = "select count(*) from book where id = ?;";
        int nbBook = jdbcTemplate.queryForObject(sql,Integer.class,id);

        return nbBook>0;
    }

    @Override
    public boolean exists(String title, String author, int categoryId) {
        String sql = "select count(*) from book where title = ? and author = ? and category_id = ?;";
        int nbBook = jdbcTemplate.queryForObject(sql,Integer.class,title,author,categoryId);

        return nbBook>0;
    }

    @Override
    public boolean existsWCategory(int categoryId) {
        String sql = "select count(*) from book where category_id = ?;";
        int nbBook = jdbcTemplate.update(sql,categoryId);

        return nbBook>0;
    }

    @Override
    public void cleanUp() {
        String sql = "delete from book b" +
                " where not exists (select * from bookshop_book_rel bbr where bbr.book_id = b.id);";
        jdbcTemplate.update(sql);
    }
}

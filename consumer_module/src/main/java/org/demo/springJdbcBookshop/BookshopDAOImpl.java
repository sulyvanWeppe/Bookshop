package org.demo.springJdbcBookshop;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
public class BookshopDAOImpl implements BookshopDAO {

    @Getter
    @Setter
    private DataSource dataSource;
    @Getter
    @Setter
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createBookshop(String name, String address) {
        String sql = "insert into bookshop(name, address) values(?, ?);";
        jdbcTemplate.update(sql, name, address);

        log.info("Creation of the bookshop: "+name+" located at: "+address);
    }

    @Override
    public Bookshop getBookshop(int id) {
        Bookshop bookshop = null;

        //Check if the bookshop exists
        if (exists(id))
        {
            String sql = "select * from bookshop where id = ?;";
            bookshop = (Bookshop) jdbcTemplate.queryForObject(sql, new BookshopMapper(), id);
        }

        return bookshop;
    }

    @Override
    public Bookshop getBookshop(String name, String address) {
        Bookshop bookshop = null;

        //Check if the bookshop exists
        if (exists(name, address))
        {
            String sql = "select * from bookshop where name = ? and address = ?;";
            bookshop = (Bookshop) jdbcTemplate.queryForObject(sql, new BookshopMapper(), name, address);
        }

        return bookshop;
    }

    @Override
    public void deleteBookshop(int id) {
        //Get info of the bookshop to be deleted
        String sql = "select * from bookshop where id = ?;";
        Bookshop bookshop = (Bookshop) jdbcTemplate.queryForObject(sql, new BookshopMapper(), id);

        //Delete the bookshop
        sql = "delete from bookshop where id = ?;";
        jdbcTemplate.update(sql, id);

        log.info("Deletion of bookshop: "+bookshop.getName()+" located at: "+bookshop.getAddress());
    }

    @Override
    public void updateBookshopName(int id, String name) {
        //1. Get before info of the bookshop to be updated
        String sql = "select * from bookshop where id = ?;";
        Bookshop bookshop = (Bookshop) jdbcTemplate.queryForObject(sql, new BookshopMapper(), id);

        //2.Perform the name update
        sql = "update bookshop set name = ? where id = ?;";
        jdbcTemplate.update(sql, name, id);

        log.info("Update of bookshop (id="+id+"): name was '"+bookshop.getName()+"' and is now '"+name+"'");
    }

    @Override
    public void updateBookshopAddress(int id, String address) {
        //1. Get before info of the bookshop to be updated
        String sql = "select * from bookshop where id = ?;";
        Bookshop bookshop = (Bookshop) jdbcTemplate.queryForObject(sql, new BookshopMapper(), id);

        //2.Perform the name update
        sql = "update bookshop set address = ? where id = ?;";
        jdbcTemplate.update(sql, address, id);

        log.info("Update of bookshop (id="+id+"): address was '"+bookshop.getAddress()+"' and is now '"+address+"'");
    }

    @Override
    public boolean exists(int id)
    {
        String sql = "select count(*) from bookshop where id = ?;";
        int nbCorrespondingBookshop = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return nbCorrespondingBookshop > 0;
    }

    @Override
    public boolean exists(String name, String address)
    {
        String sql = "select count(*) from bookshop where name = ? and address = ?;";
        int nbCorrespondingBookshop = jdbcTemplate.queryForObject(sql, Integer.class, name, address);

        return nbCorrespondingBookshop > 0;
    }

    @Override
    public void cleanUp() {
        String sql = "delete from bookshop b" +
                " where not exists (select * from bookshop_book_rel bbr where bbr.bookshop_id = b.id);";
        jdbcTemplate.update(sql);
    }
}

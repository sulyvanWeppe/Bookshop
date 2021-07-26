package org.demo.springJdbcBookshop;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookshopBookRelMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
       BookshopBookRel bookshopBookRel = new BookshopBookRel();

       bookshopBookRel.setBookshopId(resultSet.getInt("bookshop_id"));
       bookshopBookRel.setBookId(resultSet.getInt("book_id"));

        return bookshopBookRel;
    }
}

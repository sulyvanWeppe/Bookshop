package org.demo.springJdbcBookshop;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookshopMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Bookshop bookshop = new Bookshop();

        bookshop.setId(resultSet.getInt("id"));
        bookshop.setAddress(resultSet.getString("address"));
        bookshop.setName(resultSet.getString("name"));

        return bookshop;
    }
}

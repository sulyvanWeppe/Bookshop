package org.demo.springJdbcBookshop;

import java.util.List;

public interface CsvParser {

    public <T> List<T> parse();

}

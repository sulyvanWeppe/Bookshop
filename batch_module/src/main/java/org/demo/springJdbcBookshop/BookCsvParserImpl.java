package org.demo.springJdbcBookshop;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class BookCsvParserImpl implements CsvParser {

    private static char separator = ';';
    @Value("${path.csv.entity_creation.book}")
    private String filePath;

    @Override
    public List<Book> parse() {
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBeanBuilder<>(fileReader).withSeparator(separator)
                                                                .withType(Book.class)
                                                                .build();

        List<Book> books = csvToBean.parse();

        return books;
    }
}

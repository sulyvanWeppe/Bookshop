package org.demo.springJdbcBookshop;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@PropertySource("classpath: application.properties")
public class BookshopBookRelCsvParserImpl implements CsvParser {

    private static char separator = ';';
    @Setter
    @Value("${path.csv.entity_creation.bookshopbookrel}")
    private String filePath;

    @Override
    public List<BookshopBookRel> parse() {
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CsvToBean csvToBean = new CsvToBeanBuilder<>(fileReader).withSeparator(separator).withType(BookshopBookRel.class).build();

        List<BookshopBookRel> bookshopBookRels = csvToBean.parse();

        return bookshopBookRels;
    }
}

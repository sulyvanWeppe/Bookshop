package org.demo.springJdbcBookshop;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.expression.EnvironmentAccessor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@PropertySource("classpath:application.properties")
public class BookshopCsvParserImpl implements CsvParser {

    private static char separator = ';';
    @Setter
    @Value("${path.csv.entity_creation.bookshop:default}")
    private String filePath;

    @Override
    public List<Bookshop> parse() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(this.filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CsvToBean csvToBean = new CsvToBeanBuilder<>(fileReader).withSeparator(separator)
                .withType(Bookshop.class)
                .build();
        List<Bookshop> bookshops;
        bookshops = csvToBean.parse();

        return bookshops;
    }
}

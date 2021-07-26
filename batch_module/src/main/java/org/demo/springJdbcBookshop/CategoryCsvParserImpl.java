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

@PropertySource("classpath:application.properties")
public class CategoryCsvParserImpl implements CsvParser {

    private static char separator = ';';
    @Setter
    @Value("${path.csv.entity_creation.category}")
    private String filePath;

    @Override
    public List<Category> parse() {
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CsvToBean csvToBean = new CsvToBeanBuilder<>(fileReader).withSeparator(separator)
                                                                .withType(Category.class)
                                                                .build();

        List<Category> categories = csvToBean.parse();

        return categories;
    }
}

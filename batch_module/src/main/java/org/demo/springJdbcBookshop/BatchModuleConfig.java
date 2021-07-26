package org.demo.springJdbcBookshop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class BatchModuleConfig {

    @Bean
    public BookshopCsvParserImpl bookshopCsvParser(){
        BookshopCsvParserImpl bookshopCsvParser = new BookshopCsvParserImpl();

        return bookshopCsvParser;
    };

    @Bean
    public BookCsvParserImpl bookCsvParser(){
        BookCsvParserImpl bookCsvParser = new BookCsvParserImpl();

        return bookCsvParser;
    };

    @Bean
    public CategoryCsvParserImpl categoryCsvParser(){
        CategoryCsvParserImpl categoryCsvParser = new CategoryCsvParserImpl();

        return categoryCsvParser;
    };

    @Bean
    public BookshopBookRelCsvParserImpl bookshopBookRelCsvParser(){
        BookshopBookRelCsvParserImpl bookshopBookRelCsvParser = new BookshopBookRelCsvParserImpl();

        return bookshopBookRelCsvParser;
    };
}

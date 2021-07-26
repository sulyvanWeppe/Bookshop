package org.demo.springJdbcBookshop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.demo.springJdbcBookshop");
        context.refresh();

        ///////////////////
        //Get Beans
        ///////////////////
        //Entity manager beans
        BookshopManagerImpl bookshopManager = (BookshopManagerImpl) context.getBean("bookshopManager");
        BookshopBookRelManagerImpl bookshopBookRelManager = (BookshopBookRelManagerImpl) context.getBean("bookshopBookRelManager");
        BookManagerImpl bookManager = (BookManagerImpl) context.getBean("bookManager");
        CategoryManagerImpl categoryManager = (CategoryManagerImpl) context.getBean("categoryManager");
        //Csv parser beans
        BookshopCsvParserImpl bookshopCsvParser = (BookshopCsvParserImpl) context.getBean("bookshopCsvParser");
        BookCsvParserImpl bookCsvParser = (BookCsvParserImpl) context.getBean("bookCsvParser");
        CategoryCsvParserImpl categoryCsvParser = (CategoryCsvParserImpl) context.getBean("categoryCsvParser");
        BookshopBookRelCsvParserImpl bookshopBookRelCsvParser = (BookshopBookRelCsvParserImpl) context.getBean("bookshopBookRelCsvParser");

        ///////////////////
        //Parse Csv file and create the corresponding entity element
        ///////////////////
        List<Bookshop> bookshops = bookshopCsvParser.parse();
        List<Book> books = bookCsvParser.parse();
        List<Category> categories = categoryCsvParser.parse();
        List<BookshopBookRel> bookshopBookRels = bookshopBookRelCsvParser.parse();

        bookshops.stream().forEach(b -> bookshopManager.createEntity(b.getName(),b.getAddress()));
        categories.stream().forEach(c -> categoryManager.createEntity(c.getName()));
        books.stream().forEach(b -> bookManager.createEntity(b.getTitle(), b.getAuthor(), b.getCat_id()));
        bookshopBookRels.stream().forEach(r -> bookshopBookRelManager.createEntity(r.getBookshopId(), r.getBookId()));
    }
}

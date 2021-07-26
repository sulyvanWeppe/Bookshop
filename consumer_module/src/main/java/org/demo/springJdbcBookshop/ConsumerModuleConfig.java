package org.demo.springJdbcBookshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
public class ConsumerModuleConfig {
    @Bean
    public BookshopDAOImpl bookshopDAO(){
        BookshopDAOImpl bookshopDAOImpl = new BookshopDAOImpl();
        bookshopDAOImpl.setDataSource(dataSource()); //Set dataSource
        bookshopDAOImpl.setJdbcTemplate(jdbcTemplate()); //Set jdbcTemplate

        return bookshopDAOImpl;
    }

    @Bean
    public BookDAOImpl bookDAO(){
        BookDAOImpl bookDAOImpl = new BookDAOImpl();
        bookDAOImpl.setDataSource(dataSource()); //Set datasource
        bookDAOImpl.setJdbcTemplate(jdbcTemplate()); //Set jdbcTemplate

        return bookDAOImpl;
    }

    @Bean
    public CategoryDAOImpl categoryDAO(){
        CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
        categoryDAOImpl.setDataSource(dataSource()); //Set dataSource
        categoryDAOImpl.setJdbcTemplate(jdbcTemplate()); //Set jdbcTemplate

        return categoryDAOImpl;
    }

    @Bean
    public BookshopBookRelDAOImpl bookshopBookRelDAO(){
        BookshopBookRelDAOImpl bookshopBookRelDAO = new BookshopBookRelDAOImpl();
        bookshopBookRelDAO.setDataSource(dataSource());
        bookshopBookRelDAO.setJdbcTemplate(jdbcTemplate());
        bookshopBookRelDAO.setBookDAO(bookDAO());

        return bookshopBookRelDAO;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springJdbcBookshop");
        dataSource.setUsername("sulyvan");
        dataSource.setPassword("toto");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate()
    {
        return new JdbcTemplate(dataSource());
    }

}

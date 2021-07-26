package org.demo.springJdbcBookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.demo.springJdbcBookshop.ConsumerModuleConfig;

@Configuration
public class BusinessModuleConfig {
    @Autowired ConsumerModuleConfig consumerModuleConfig;

    @Bean
    public BookshopManagerImpl bookshopManager(){
        BookshopManagerImpl bookshopManager = new BookshopManagerImpl();
        bookshopManager.setBookshopDAO(consumerModuleConfig.bookshopDAO());
        bookshopManager.setBookshopBookRelDAO(consumerModuleConfig.bookshopBookRelDAO());
        bookshopManager.setBookDAO(consumerModuleConfig.bookDAO());
        bookshopManager.setCategoryDAO(consumerModuleConfig.categoryDAO());

        return bookshopManager;
    }

    @Bean
    public BookManagerImpl bookManager(){
        BookManagerImpl bookManager = new BookManagerImpl();
        bookManager.setBookshopDAO(consumerModuleConfig.bookshopDAO());
        bookManager.setBookshopBookRelDAO(consumerModuleConfig.bookshopBookRelDAO());
        bookManager.setBookDAO(consumerModuleConfig.bookDAO());
        bookManager.setCategoryDAO(consumerModuleConfig.categoryDAO());

        return bookManager;
    }

    @Bean
    public BookshopBookRelManagerImpl bookshopBookRelManager(){
        BookshopBookRelManagerImpl bookshopBookRelManager = new BookshopBookRelManagerImpl();
        bookshopBookRelManager.setBookshopDAO(consumerModuleConfig.bookshopDAO());
        bookshopBookRelManager.setBookshopBookRelDAO(consumerModuleConfig.bookshopBookRelDAO());
        bookshopBookRelManager.setBookDAO(consumerModuleConfig.bookDAO());
        bookshopBookRelManager.setCategoryDAO(consumerModuleConfig.categoryDAO());

        return bookshopBookRelManager;
    }

    @Bean
    public CategoryManagerImpl categoryManager(){
        CategoryManagerImpl categoryManager = new CategoryManagerImpl();
        categoryManager.setBookshopDAO(consumerModuleConfig.bookshopDAO());
        categoryManager.setBookshopBookRelDAO(consumerModuleConfig.bookshopBookRelDAO());
        categoryManager.setBookDAO(consumerModuleConfig.bookDAO());
        categoryManager.setCategoryDAO(consumerModuleConfig.categoryDAO());

        return categoryManager;
    }
}

package org.demo.springJdbcBookshop;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

public class Book extends Entity{
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    @CsvBindByName(column = "title")
    private String title;
    @Getter
    @Setter
    @CsvBindByName(column = "author")
    private String author;
    @Getter
    @Setter
    @CsvBindByName(column = "cat_id")
    private int cat_id;

    public Book()
    {
        System.out.println("3.75");
        this.setEntityType(EntityType.BOOK);
    }

    public Book(int id, String title, String author, int cat_id)
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.cat_id = cat_id;

        this.setEntityType(EntityType.BOOK);
    }
}

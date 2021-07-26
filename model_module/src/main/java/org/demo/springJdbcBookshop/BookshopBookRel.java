package org.demo.springJdbcBookshop;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

public class BookshopBookRel extends Entity{
    @Getter
    @Setter
    @CsvBindByName(column = "bookshopId")
    private int bookshopId;
    @Getter
    @Setter
    @CsvBindByName(column = "bookId")
    private int bookId;

    public BookshopBookRel()
    {
        this.entityType = EntityType.BOOKSHOPBOOKREL;
    }

    public BookshopBookRel(int bookshopId, int bookId)
    {
        this.bookshopId = bookshopId;
        this.bookId = bookId;

        this.entityType = EntityType.BOOKSHOPBOOKREL;
    }
}

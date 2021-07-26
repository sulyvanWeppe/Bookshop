package org.demo.springJdbcBookshop;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public enum EntityType {
    BOOKSHOP("Bookshop"),
    BOOK("Book"),
    BOOKSHOPBOOKREL("Bookshop Book Relation"),
    CATEGORY("Category");

    @Getter
    @Setter
    private String name;

    EntityType(String name)
    {
        this.name = name;
    }

}

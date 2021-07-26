package org.demo.springJdbcBookshop;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

public class Category extends Entity{
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    @CsvBindByName(column = "name")
    private String name;

    public Category()
    {
        this.entityType = EntityType.CATEGORY;
    }

    public Category(int id, String name)
    {
        this.id = id;
        this.name = name;

        this.entityType = EntityType.CATEGORY;
    }
}

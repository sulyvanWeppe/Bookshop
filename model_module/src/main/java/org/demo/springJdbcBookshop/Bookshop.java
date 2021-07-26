package org.demo.springJdbcBookshop;

import com.opencsv.bean.CsvBindAndJoinByName;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

public class Bookshop extends Entity{
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    @CsvBindByName(column = "name")
    private String name;
    @Getter
    @Setter
    @CsvBindByName(column = "address")
    private String address;

    public Bookshop()
    {
        this.setEntityType(EntityType.BOOKSHOP);
    }

    public Bookshop(int id, String name, String address)
    {
        this.id = id;
        this.name = name;
        this.address = address;

        this.entityType = EntityType.BOOKSHOP;
    }
}

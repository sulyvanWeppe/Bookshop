package org.demo.springJdbcBookshop.exceptions;

import lombok.Getter;
import lombok.Setter;

public class BookshopAppException extends RuntimeException{
    @Getter
    @Setter
    protected String exceptionType = "Generic";
    @Getter
    @Setter
    protected String label = "Bookshop Application Exception";
}

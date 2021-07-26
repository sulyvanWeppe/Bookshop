package org.demo.springJdbcBookshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BookshopAppExceptionController {

    @ExceptionHandler(value = BookshopAppException.class)
    public ResponseEntity<BookshopAppException> bookshopAppException(BookshopAppException e)
    {
        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = EntityException.class)
    public ResponseEntity<EntityException> entityException(EntityException e)
    {
        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundException> entityNotFoundException(EntityNotFoundException e)
    {
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }
}

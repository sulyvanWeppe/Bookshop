package org.demo.springJdbcBookshop;

import lombok.extern.slf4j.Slf4j;
import org.demo.springJdbcBookshop.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("bookshop_app")
public class BookServices {

    @Autowired
    BookManagerImpl bookManager;

    ///////////////////////////////////
    //GET API
    ///////////////////////////////////
    @GetMapping(value = "/book/{id}", produces = "application/json")
    @ResponseBody
    public Book getBook(@PathVariable int id) throws EntityNotFoundException
    {
        log.info("Book service - GET : (Request) get book with id = "+id);
        Book book = bookManager.getEntity(id);

        if (book != null)
        {
            log.info("Book service - GET : (Response) get book with id = "+id+"/"+book.getTitle()+"/"+book.getAuthor()+"/"+book.getCat_id());
            return book;
        }
        else
        {
            log.error("Book service - GET : error when trying to get book with id = "+id);
            throw new EntityNotFoundException(EntityType.BOOK, id);
        }
    }

    ///////////////////////////////////
    //POST API
    ///////////////////////////////////
    @PostMapping(value = "/book", produces = "application/json")
    @ResponseBody
    public Book postBook(@RequestBody Book book)
    {
        log.info("Book service - POST : (Request) create new book = "+book.getTitle()+"/"+book.getAuthor()+"/"+book.getCat_id());

        //Create new Book
        bookManager.createEntity(book.getTitle(), book.getAuthor(), book.getCat_id());
        //Get newly created book
        Book newBook = bookManager.getEntity(book.getTitle(), book.getAuthor(), book.getCat_id());
        //return newly created book;
        log.info("Book service - POST : (Response) new book created = "+book.getTitle()+"/"+book.getAuthor()+"/"+book.getCat_id());
        return newBook;
    }

    ///////////////////////////////////
    //DELETE API
    ///////////////////////////////////
    @DeleteMapping(value = "book/{id}", produces = "application/json")
    public ResponseEntity<String> deleteBook(@PathVariable int id) throws EntityNotFoundException
    {
        log.info("Book service - DELETE : (Request) delete book with id = "+id);
        if (bookManager.exists(id))
        {
            bookManager.deleteEntity(id);
        }
        else
        {
            log.error("Book service - DELETE : error when trying to delete book = "+id);
            throw new EntityNotFoundException(EntityType.BOOK, id);
        }

        log.info("Book service - DELETE : (Response) book "+id+" was deleted");
        return new ResponseEntity<>("Result: Book correctly deleted", HttpStatus.OK);
    }

    ///////////////////////////////////
    //PUT API
    ///////////////////////////////////
    @PutMapping(value = "/book", produces = "application/json")
    @ResponseBody
    public Book putBook(@RequestBody Book book) throws EntityNotFoundException
    {
        log.info("Book service - PUT : (Request) update bookshop with id = "+book.getId());
        if (bookManager.exists(book.getId())) {
            bookManager.updateBook(book.getId(), book.getTitle(), book.getAuthor(), book.getCat_id());
        }
        else
        {
            log.error("Book service - PUT : error when trying to update bookshop = "+book.getId());
            throw new EntityNotFoundException(EntityType.BOOK, book.getId());
        }
        log.error("Book service - PUT : (Response) bookshop was updated = "+book.getId()+"/"+book.getTitle()+"/"+book.getAuthor()+"/"+book.getCat_id());
        return book;
    }
}

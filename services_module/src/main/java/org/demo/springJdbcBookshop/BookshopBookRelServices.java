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
public class BookshopBookRelServices {

    @Autowired
    BookshopBookRelManagerImpl bookshopBookRelManager;

    ///////////////////////////////////
    //GET API
    ///////////////////////////////////
    @GetMapping(value = "/bookshopbookrel/{bookshopid}/{bookid}", produces = "application/json")
    @ResponseBody
    public BookshopBookRel getBookshopBookRel(@PathVariable int bookshopid, @PathVariable int bookid) throws EntityNotFoundException
    {
        log.info("BookshopBookRel service - GET : (Request) get bookshopbookrel with bookshopid = "+bookshopid+" and bookid = "+bookid);

        BookshopBookRel bookshopBookRel = bookshopBookRelManager.getEntity(bookshopid, bookid);

        if (bookshopBookRel != null)
        {
            log.info("BookshopBookRel service - GET : (Response) get bookshopbookrel with bookshopid = "+bookshopBookRel.getBookshopId()+" and bookid = "+bookshopBookRel.getBookId());
            return bookshopBookRel;
        }
        else
        {
            log.error("BookshopBookRel service - GET : error when trying to get bookshopbookrel with bookshopid = "+bookshopid+" and bookid = "+bookid);
            throw new EntityNotFoundException(EntityType.BOOKSHOPBOOKREL);
        }
    }

    ///////////////////////////////////
    //POST API
    ///////////////////////////////////
    @PostMapping(value = "/bookshopbookrel", produces = "application/json")
    @ResponseBody
    public BookshopBookRel postBookshopBookRel(@RequestBody BookshopBookRel bookshopBookRel)
    {
        log.info("BookshopBookRel service - POST : (Request) create new bookshopbookrel = "+bookshopBookRel.getBookshopId()+"/"+bookshopBookRel.getBookId());

        //Create new BookshopBookRel
        bookshopBookRelManager.createEntity(bookshopBookRel.getBookshopId(), bookshopBookRel.getBookId());
        //Get newly created bookshopBookRel
        BookshopBookRel newBookshopBookRel = bookshopBookRelManager.getEntity(bookshopBookRel.getBookshopId(), bookshopBookRel.getBookId());
        //return newly created bookshopBookRel;
        log.info("BookshopBookRel service - POST : (Response) new bookshopbookrel created = "+newBookshopBookRel.getBookshopId()+"/"+newBookshopBookRel.getBookId());
        return newBookshopBookRel;
    }

    ///////////////////////////////////
    //DELETE API
    ///////////////////////////////////
    @DeleteMapping(value = "/bookshopbookrel/{bookshopid}/{bookid}", produces = "application/json")
    public ResponseEntity<String> deleteBookshopBookRel(@PathVariable int bookshopid, @PathVariable int bookid) throws EntityNotFoundException
    {
        log.info("BookshopBookRel service - DELETE : (Request) delete bookshopbookrel with bookshopid = "+bookshopid+" and bookid = "+bookid);
        if (bookshopBookRelManager.exists(bookshopid, bookid))
        {
            bookshopBookRelManager.deleteEntity(bookshopid, bookid);
        }
        else
        {
            log.error("BookshopBookRel service - DELETE : error when trying to delete bookshopbookrel with bookshopid = "+bookshopid+" and bookid = "+bookid);
            throw new EntityNotFoundException(EntityType.BOOKSHOPBOOKREL);
        }

        log.info("BookshopBookRel service - DELETE : (Response) bookshopbookrel with bookshopid = "+bookshopid+" and bookid = "+bookid+" was deleted");
        return new ResponseEntity<>("Result: Book-Book relation correctly deleted", HttpStatus.OK);
    }

}

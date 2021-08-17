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
public class BookshopServices {

    @Autowired
    private BookshopManagerImpl bookshopManager;

    ///////////////////////////////////
    //GET API
    ///////////////////////////////////
    @GetMapping(value = "/bookshop/{id}", produces = "application/json")
    @ResponseBody
    public Bookshop getBookshop(@PathVariable int id) throws EntityNotFoundException
    {
        log.info("Bookshop service - GET : (Request) get bookshop with id = "+id);

        Bookshop bookshop = (Bookshop) bookshopManager.getEntity(id);
        if (bookshop == null)
        {
            log.error("Bookshop service - GET : error when trying to get bookshop with id = "+id);
            throw new EntityNotFoundException(EntityType.BOOKSHOP, id);
        }
        else
        {
            log.info("Bookshop service - GET : (Response) get bookshop with id = "+id+"/"+bookshop.getName()+"/"+bookshop.getAddress());
            return bookshop;
        }
    }

    ///////////////////////////////////
    //POST API
    ///////////////////////////////////
    @PostMapping(value = "/bookshop", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Bookshop> postBookshop(@RequestBody Bookshop bookshop)
    {
        log.info("Bookshop service - POST : (Request) create new bookshop = "+bookshop.getName()+"/"+bookshop.getAddress());

        //Create new bookshop
        bookshopManager.createEntity(bookshop.getName(), bookshop.getAddress());
        //Get newly created bookshop
        Bookshop newBookshop = bookshopManager.getEntity(bookshop.getName(), bookshop.getAddress());
        //Return newly created bookshop
        log.info("Bookshop service - POST : (Response) new booskhop created = "+newBookshop.getId()+"/"+newBookshop.getName()+"/"+newBookshop.getAddress());
        return new ResponseEntity<>(newBookshop, HttpStatus.CREATED);
    }

    ///////////////////////////////////
    //DELETE API
    ///////////////////////////////////
 //   @CrossOrigin(origins = "http://localhost:9000")
    @DeleteMapping(value = "/bookshop/{id}", produces = "application/json")
    public ResponseEntity<String> deleteBookshop(@PathVariable int id) throws EntityNotFoundException
    {
        log.info("Bookshop service - DELETE : (Request) delete bookshop with id = "+id);
        if (bookshopManager.exists(id))
        {
            bookshopManager.deleteEntity(id);
        }
        else
        {
            log.error("Bookshop service - DELETE : error when trying to delete bookshop = "+id);
            throw new EntityNotFoundException(EntityType.BOOKSHOP, id);
        }

        return new ResponseEntity<>("Result: Bookshop correctly deleted", HttpStatus.OK);
    }

    ///////////////////////////////////
    //PUT API
    ///////////////////////////////////
    @PutMapping(value = "/bookshop", produces = "application/json")
    public ResponseEntity<Bookshop> putBookshop(@RequestBody Bookshop bookshop) throws EntityNotFoundException
    {
        log.info("Bookshop service - PUT : (Request) update bookshop with id = "+bookshop.getId());
        if (bookshopManager.exists(bookshop.getId())) {
            bookshopManager.updateBookshop(bookshop.getId(), bookshop.getName(), bookshop.getAddress());
        }
        else
        {
            log.error("Bookshop service - PUT : error when trying to update bookshop = "+bookshop.getId());
            throw new EntityNotFoundException(EntityType.BOOKSHOP, bookshop.getId());
        }
        log.error("Bookshop service - PUT : (Response) bookshop was updated = "+bookshop.getId()+"/"+bookshop.getName()+"/"+bookshop.getAddress());
        return new ResponseEntity<>(bookshop, HttpStatus.OK);
    }

}

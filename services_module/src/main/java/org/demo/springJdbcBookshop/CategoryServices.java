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
public class CategoryServices {

    @Autowired
    CategoryManagerImpl categoryManager;

    ///////////////////////////////////
    //GET API
    ///////////////////////////////////
    @GetMapping(value = "/category/{id}", produces = "application/json")
    @ResponseBody
    public Category getCategory(@PathVariable int id) throws EntityNotFoundException
    {
        log.info("Category service - GET : (Request) get category with id = "+id);

        Category category = categoryManager.getEntity(id);

        if (category != null)
        {
            log.info("Category service - GET : (Response) get category with id = "+id+"/"+category.getName());
            return category;
        }
        else
        {
            log.error("Category service - GET : error when trying to get category with id = "+id);
            throw new EntityNotFoundException(EntityType.CATEGORY, id);
        }
    }

    ///////////////////////////////////
    //POST API
    ///////////////////////////////////
    //@CrossOrigin(origins = "http://localhost:9090")
    @PostMapping(value = "/category", produces = "application/json")
    @ResponseBody
    public Category postCategory(@RequestBody Category category)
    {
        log.info("Category service - POST : (Request) create new category = "+category.getName());

        //Create new Category
        categoryManager.createEntity(category.getName());
        //Get newly created category
        Category newCategory = categoryManager.getEntity(category.getName());
        //return newly created category;
        log.info("Category service - POST : (Response) new category created = "+newCategory.getId()+"/"+ newCategory.getName());
        return newCategory;
    }

    ///////////////////////////////////
    //DELETE API
    ///////////////////////////////////
    @DeleteMapping(value = "category/{id}", produces = "application/json")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) throws EntityNotFoundException
    {
        log.info("Category service - DELETE : (Request) delete category with id = "+id);
        if (categoryManager.exists(id))
        {
            categoryManager.deleteEntity(id);
        }
        else
        {
            log.error("Category service - DELETE : error when trying to delete category = "+id);
            throw new EntityNotFoundException(EntityType.CATEGORY, id);
        }

        log.info("Category service - DELETE : (Response) category "+id+" was deleted");
        return new ResponseEntity<>("Result: Category correctly deleted", HttpStatus.OK);
    }

    ///////////////////////////////////
    //PUT API
    ///////////////////////////////////
    @PutMapping(value = "/category", produces = "application/json")
    @ResponseBody
    public Category putCategory(@RequestBody Category category) throws EntityNotFoundException
    {
        log.info("Category service - PUT : (Request) update category with id = "+category.getId());
        if (categoryManager.exists(category.getId())) {
            categoryManager.updateCategoryName(category.getId(), category.getName());
        }
        else
        {
            log.error("Category service - PUT : error when trying to update category = "+category.getId());
            throw new EntityNotFoundException(EntityType.CATEGORY, category.getId());
        }

        log.error("Category service - PUT : (Response) category was updated = "+category.getId()+"/"+category.getName());
        return category;
    }
}

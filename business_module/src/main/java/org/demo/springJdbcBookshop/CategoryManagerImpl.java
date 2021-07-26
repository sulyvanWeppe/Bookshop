package org.demo.springJdbcBookshop;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryManagerImpl {
    @Getter
    @Setter
    private BookshopDAO bookshopDAO;
    @Getter
    @Setter
    private BookDAO bookDAO;
    @Getter
    @Setter
    private BookshopBookRelDAO bookshopBookRelDAO;
    @Getter
    @Setter
    private CategoryDAO categoryDAO;

    public void createEntity(String name) {
        //1. Check if the category does not already exist
        boolean categoryAlreadyExists = categoryDAO.exists(name);
        if (categoryAlreadyExists)
        {
            log.error("ERROR : got an error when trying to create category: '"+name+"' because it already exists");
            return;
        }

        //2. If not create it
        categoryDAO.createCategory(name);
    }

    public Category getEntity(int entityId) {
        Category category = categoryDAO.getCategory(entityId);
        boolean categoryNotNull = (category != null);

        if (categoryNotNull)
            {return category;}
        else
            {return null;}
    }

    public Category getEntity(String name) {
        Category category = categoryDAO.getCategory(name);
        boolean categoryNotNull = (category != null);

        if (categoryNotNull)
        {return category;}
        else
        {return null;}
    }

    public void updateCategoryName(int id, String name)
    {
        categoryDAO.updateCategoryName(id, name);
    }

    public void deleteEntity(int entityId) {
        //1. Ensure there is no book with the given category
        if (bookDAO.existsWCategory(entityId))
        {
            log.error("ERROR : not possible to delete category: "+categoryDAO.getCategory(entityId).getName()+" because there are still book of this latest");
        }
        else
        {
            //2. Delete the category
            categoryDAO.deleteCategory(entityId);
        }
    }

    public boolean exists(int entityId) {
        return categoryDAO.exists(entityId);
    }
}

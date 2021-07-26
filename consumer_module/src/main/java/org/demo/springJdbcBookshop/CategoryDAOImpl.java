package org.demo.springJdbcBookshop;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
public class CategoryDAOImpl implements CategoryDAO {

    @Getter
    @Setter
    private DataSource dataSource;
    @Getter
    @Setter
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createCategory(String name) {

        String sql = "insert into category(name) values(?);";
        jdbcTemplate.update(sql, name);
        log.info("Creation of the category: "+name);
    }

    @Override
    public Category getCategory(int id) {
        Category category = null;

        //Check if the category exists
        if (exists(id))
        {
            String sql = "select * from category where id = ?;";
            category = (Category) jdbcTemplate.queryForObject(sql,new CategoryMapper(), id);
        }

        return category;
    }

    @Override
    public Category getCategory(String name) {
        Category category = null;

        //Check if the category exists
        if (exists(name))
        {
            String sql = "select * from category where name = ?;";
            category = (Category) jdbcTemplate.queryForObject(sql,new CategoryMapper(), name);
        }

        return category;
    }

    @Override
    public void updateCategoryName(int id, String name) {
        //1. Get before info of the category to be updated
        String sql = "select * from category where id = ?;";
        Category category = (Category) jdbcTemplate.queryForObject(sql, new CategoryMapper(), id);

        //2.Perform the name update
        sql = "update category set name = ? where id = ?;";
        jdbcTemplate.update(sql, name, id);

        log.info("Update of category (id="+id+"): name was '"+category.getName()+"' and is now '"+name+"'");
    }

    @Override
    public void deleteCategory(int id) {
        //Get info of the category to be deleted
        String sql = "select * from category where id = ?;";
        Category category = (Category) jdbcTemplate.queryForObject(sql,new CategoryMapper(), id);

        //Deletion of the category
        sql = "delete from category where id = ?;";
        jdbcTemplate.update(sql, id);

        log.info("Deletion of the category: "+category.getName());
    }

    @Override
    public void cleanUp() {
        //Get the list of orphan categories
        String sql = "select c.id" +
                    " from category c" +
                    " where not exists (select b.id from book b where b.category_id = c.id);";
        List<Integer> orphansCategories = jdbcTemplate.queryForList(sql, Integer.class);

        //Delete those orphans categories
        orphansCategories.stream().forEach(this::deleteCategory);
    }

    @Override
    public boolean exists(int id) {
        String sql = "select count(*) from category where id = ?;";
        int nbCategory = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return nbCategory>0;
    }

    @Override
    public boolean exists(String name) {
        String sql = "select count(*) from category where name = ?;";
        int nbCategory = jdbcTemplate.queryForObject(sql, Integer.class, name);

        return nbCategory>0;
    }
}

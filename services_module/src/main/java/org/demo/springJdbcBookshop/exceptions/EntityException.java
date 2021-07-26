package org.demo.springJdbcBookshop.exceptions;

import lombok.Data;
import lombok.Getter;
import org.demo.springJdbcBookshop.*;

@Data
public class EntityException extends BookshopAppException {
    private int triggeringEntityId;
    private EntityType triggeringEntityType;

    public EntityException(Entity entity)
    {
        //Setting of the entity id
        //Applicable for Bookshop, Book and Category
        if (entity instanceof Bookshop)
        {
            this.triggeringEntityId = ((Bookshop) entity).getId();
        }
        else if (entity instanceof Book)
        {
            this.triggeringEntityId = ((Book) entity).getId();
        }
        else if (entity instanceof Category)
        {
            this.triggeringEntityId = ((Category) entity).getId();
        }

        this.triggeringEntityType = entity.getEntityType();
    }

    public EntityException(EntityType entityType)
    {
        this.triggeringEntityType = entityType;
    }

    public EntityException(EntityType entityType, int entityId)
    {
        this.triggeringEntityType = entityType;
        this.triggeringEntityId = entityId;
    }
}

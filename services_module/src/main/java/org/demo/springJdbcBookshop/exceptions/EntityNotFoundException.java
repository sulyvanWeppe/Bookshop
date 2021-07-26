package org.demo.springJdbcBookshop.exceptions;

import org.demo.springJdbcBookshop.Entity;
import org.demo.springJdbcBookshop.EntityType;

public class EntityNotFoundException extends EntityException{

    public EntityNotFoundException(EntityType entityType) {
        super(entityType);
        this.setExceptionType("Entity not found");
        this.setLabel(this.getTriggeringEntityType().getName()+" could not be found");
    }

    public EntityNotFoundException(EntityType entityType, int entityId) {
        super(entityType, entityId);
        this.setExceptionType("Entity not found");
        this.setLabel(this.getTriggeringEntityType()+" could not be found");
    }
}

package edu.bbte.idde.zdim1981.backend.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseEntity {
    protected Long id;
    public static final Logger LOG = LoggerFactory.getLogger(BaseEntity.class);

    public long getId() {
        LOG.info("getId() called.");
        return id;
    }

    public void setId(long id) {
        LOG.info("setId() called.");
        this.id = id;
    }
}

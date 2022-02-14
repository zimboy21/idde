package edu.bbte.idde.zdim1981.backend.model;

public class BaseEntity {
    protected Long id;

    protected Boolean deleted;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
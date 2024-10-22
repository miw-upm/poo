package upm.appentrega2.data.models;

import java.util.Objects;

public class Entity {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object entity) {
        return this == entity || entity != null && getClass() == entity.getClass() && (this.id.equals(((Entity) entity).id));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
package upm.app.data.repositories;


import upm.app.data.models.Tag;

public class TagRepository extends GenericRepository<Tag> {

    @Override
    public Integer getId(Tag entity) {
        return entity.getId();
    }

    @Override
    public void setId(Tag entity, Integer id) {
        entity.setId(id);
    }

}

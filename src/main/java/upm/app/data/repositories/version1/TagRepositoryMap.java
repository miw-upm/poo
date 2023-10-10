package upm.app.data.repositories.version1;


import upm.app.data.models.Tag;

public class TagRepositoryMap extends GenericRepositoryMap<Tag> {

    @Override
    protected Integer getId(Tag entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Tag entity, Integer id) {
        entity.setId(id);
    }

}

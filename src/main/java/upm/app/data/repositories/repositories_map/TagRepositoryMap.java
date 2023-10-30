package upm.app.data.repositories.repositories_map;


import upm.app.data.models.Tag;
import upm.app.data.repositories.TagRepository;

import java.util.Optional;

public class TagRepositoryMap extends GenericRepositoryMap<Tag> implements TagRepository {

    @Override
    protected Integer getId(Tag entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Tag entity, Integer id) {
        entity.setId(id);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        for (Tag tag : this.findAll()) {
            if (tag.getName().equals(name)) {
                return Optional.of(tag);
            }
        }
        return Optional.empty();
    }
}

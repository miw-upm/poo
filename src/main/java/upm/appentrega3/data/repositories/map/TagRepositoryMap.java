package upm.appentrega3.data.repositories.map;

import upm.appentrega3.data.models.Tag;
import upm.appentrega3.data.repositories.TagRepository;

import java.util.Optional;

public class TagRepositoryMap extends GenericRepositoryMap<Tag> implements TagRepository {

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
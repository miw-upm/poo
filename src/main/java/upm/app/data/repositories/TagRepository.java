package upm.app.data.repositories;

import upm.app.data.models.Tag;
import upm.app.data.models.User;

import java.util.Optional;

public interface TagRepository extends GenericRepository<Tag> {
    Optional<Tag> findByName(String name);
}

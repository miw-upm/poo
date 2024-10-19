package upm.app2023.data.repositories;

import upm.app2023.data.models.Tag;

import java.util.Optional;

public interface TagRepository extends GenericRepository<Tag> {
    Optional<Tag> findByName(String name);
}

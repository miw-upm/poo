package upm.appentrega2.data.repositories;

import upm.appentrega2.data.models.Tag;

import java.util.Optional;

public interface TagRepository extends GenericRepository<Tag> {
    Optional<Tag> findByName(String name);
}
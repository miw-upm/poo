package upm.appentrega3.data.repositories;

import upm.appentrega3.data.models.Tag;

import java.util.Optional;

public interface TagRepository extends GenericRepository<Tag> {
    Optional<Tag> findByName(String name);
}
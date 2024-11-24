package upm.appentrega4.data.repositories;

import upm.appentrega4.data.models.Tag;

import java.util.Optional;

public interface TagRepository extends GenericRepository<Tag> {
    Optional<Tag> findByName(String name);
}
package upm.app.data.repositories.repositories_map;

import org.junit.jupiter.api.Test;
import upm.app.DependencyInjector;
import upm.app.data.models.Tag;
import upm.app.data.repositories.ArticleRepository;
import upm.app.data.repositories.TagRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TagRepositoryMapTest {

    private final TagRepository tagRepository = DependencyInjector.getDependencyInjector().getTagRepository();
    private final ArticleRepository articleRepository = DependencyInjector.getDependencyInjector().getArticleRepository();

    @Test
    void testCreateAndRead() {
        Optional<Tag> tag = this.tagRepository.read(1);
        assertTrue(tag.isPresent());
        assertEquals("tag1", tag.get().getName());
    }

    @Test
    void testFindByMobile() {
        Optional<Tag> foundTag = tagRepository.findByName("tag2");
        assertTrue(foundTag.isPresent());
        assertEquals("tag 2", foundTag.get().getDescription());
    }

    @Test
    void testFindByNameNotFound() {
        assertFalse(tagRepository.findByName("kk").isPresent());
    }

    @Test
    void testUpdate() {
        Tag tag = this.tagRepository.read(4).get();
        tag.addArticle(this.articleRepository.read(1).get());
        tagRepository.update(tag);

        Optional<Tag> retrievedTag = tagRepository.read(4);
        assertTrue(retrievedTag.isPresent());
        assertEquals(3, retrievedTag.get().getArticles().size());

        //TODO BD modificada

    }

    @Test
    void testDelete() {
        Tag createdTag = this.tagRepository.create(new Tag("Not", "Not"));
        this.tagRepository.deleteById(createdTag.getId());
        assertFalse(this.tagRepository.read(createdTag.getId()).isPresent());
    }
}

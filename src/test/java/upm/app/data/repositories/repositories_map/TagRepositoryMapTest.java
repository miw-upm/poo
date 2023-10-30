package upm.app.data.repositories.repositories_map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upm.app.data.models.Tag;
import upm.app.data.repositories.ArticleRepository;
import upm.app.data.repositories.TagRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TagRepositoryMapTest {

    private TagRepository tagRepository;
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setUp() {
        ShopSeeder shopSeeder = new ShopSeeder();
        shopSeeder.seed();
        this.tagRepository = shopSeeder.getTagRepository();
        this.articleRepository = shopSeeder.getArticleRepository();
    }

    @Test
    public void testCreateAndRead() {
        Optional<Tag> tag = this.tagRepository.read(1);
        assertTrue(tag.isPresent());
        assertEquals("tag1", tag.get().getName());
    }

    @Test
    public void testFindByMobile() {
        Optional<Tag> foundTag = tagRepository.findByName("tag2");
        assertTrue(foundTag.isPresent());
        assertEquals("tag 2", foundTag.get().getDescription());
    }

    @Test
    public void testFindByNameNotFound() {
        assertFalse(tagRepository.findByName("kk").isPresent());
    }

    @Test
    public void testUpdate() {
        Tag tag = this.tagRepository.read(4).get();
        tag.addArticle(this.articleRepository.read(1).get());
        tagRepository.update(tag);

        Optional<Tag> retrievedTag = tagRepository.read(4);
        assertTrue(retrievedTag.isPresent());
        assertEquals(3, retrievedTag.get().getArticles().size());
    }

    @Test
    public void testDelete() {
        Tag createdTag = this.tagRepository.create(new Tag("Not", "Not"));
        this.tagRepository.deleteById(createdTag.getId());
        assertFalse(this.tagRepository.read(createdTag.getId()).isPresent());
    }
}

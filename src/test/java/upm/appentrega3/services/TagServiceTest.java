package upm.appentrega3.services;

import org.junit.jupiter.api.Test;
import upm.appentrega3.DependencyInjector;
import upm.appentrega3.data.models.Tag;
import upm.appentrega3.data.repositories.TagRepository;
import upm.appentrega3.services.exceptions.DuplicateException;
import upm.appentrega3.services.exceptions.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TagServiceTest {
    private final TagService tagService;

    private final TagRepository tagRepository;

    TagServiceTest() {
        this.tagService = DependencyInjector.getInstance().getTagService();
        this.tagRepository = DependencyInjector.getInstance().getTagRepository();
    }

    @Test
    void testCreate() {
        this.tagService.create(new Tag("tag-test1", "tag-test1"));
        assertTrue(this.tagRepository.findByName("tag-test1").isPresent());
    }

    @Test
    void testCreateDuplicatedNameException() {
        Tag tag = new Tag("tag1", "error");
        assertThrows(DuplicateException.class, () -> this.tagService.create(tag));
    }

    @Test
    void testAddArticle() {
        this.tagService.create(new Tag("tag-test2", "tag-test2"));
        this.tagService.addArticle("tag-test2", "8412345123490");
        assertTrue(this.tagRepository.findByName("tag-test2").isPresent());
        assertEquals("8412345123490", this.tagRepository.findByName("tag-test2").get().getArticles().get(0).getBarcode());
    }

    @Test
    void testAddArticleTagNameNotFoundException() {
        assertThrows(NotFoundException.class, () -> this.tagService.addArticle("kk", "8412345123490"));
    }

    @Test
    void testAddArticleBarcodeNotFoundException() {
        assertThrows(NotFoundException.class, () -> this.tagService.addArticle("tag1", "kk"));
    }

    @Test
    void testFindByArticleBarcode() {
        List<Tag> tags = this.tagService.findByArticleBarcode("8412345123450");
        assertEquals(2, tags.size());
        for (Tag tag : tags) {
            assertTrue(List.of("tag1", "tag2").contains(tag.getName()));
        }
    }

}

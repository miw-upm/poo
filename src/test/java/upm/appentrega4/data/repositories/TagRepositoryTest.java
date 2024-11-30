package upm.appentrega4.data.repositories;

import org.junit.jupiter.api.Test;
import upm.appentrega4.DependencyInjector;
import upm.appentrega4.data.models.Tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TagRepositoryTest {

    private final TagRepository tagRepository = DependencyInjector.getInstance().getTagRepository();
    private final ArticleRepository articleRepository = DependencyInjector.getInstance().getArticleRepository();


    @Test
    void testDelete() {
        Tag tag = new Tag("deleted", "description");
        tag.addArticle(this.articleRepository.findByBarcode("8412345123440").orElseThrow());
        tag = this.tagRepository.create(tag);
        assertTrue(this.tagRepository.findByName("deleted").isPresent());
        this.tagRepository.deleteById(tag.getId());
        assertFalse(this.tagRepository.findByName("deleted").isPresent());
    }

}

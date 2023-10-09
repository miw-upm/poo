package upm.app.data.repositories;

import upm.app.data.models.Article;

public class ArticleRepository extends GenericRepository<Article> {

    @Override
    public Integer getId(Article entity) {
        return entity.getId();
    }

    @Override
    public void setId(Article entity, Integer id) {
        entity.setId(id);
    }

}

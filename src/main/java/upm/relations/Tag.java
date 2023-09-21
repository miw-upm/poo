package upm.relations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Tag {
    private Integer id;
    private String name;
    private String description;
    private List<Article> articles;

    public Tag(String name, String description) {
        this.name = name;
        this.description = description;
        this.articles = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Article> getArticles() {
        return this.articles;
    }

    public void addArticle( Article article){
        this.articles.add(article);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", articles=" + articles +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object tag) {
        return this == tag || tag != null && getClass() == tag.getClass() && (this.equals(((Tag) tag).id));
    }
}

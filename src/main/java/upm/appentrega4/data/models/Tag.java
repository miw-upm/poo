package upm.appentrega4.data.models;

import java.util.ArrayList;
import java.util.List;

public class Tag extends Entity {
    private String name;
    private String description;
    private List<Article> articles;

    public Tag(String name, String description) {
        this.name = name;
        this.description = description;
        this.articles = new ArrayList<>();
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

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void addArticle(Article article) {
        this.articles.add(article);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", articles=" + articles +
                "} " + super.toString();
    }
}
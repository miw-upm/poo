package upm.appentrega4.gui.fx.components;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.ComboBox;
import upm.appentrega4.services.ArticleService;

import java.util.List;

public class ArticleComboBox extends ComboBox<KeyValue<String>> {

    public ArticleComboBox(ArticleService articleService) {
        List<KeyValue<String>> keyValues = articleService.findAll()
                .map(article -> new KeyValue<>(article.getBarcode(), article.getSummary()))
                .toList();
        this.getItems().addAll(keyValues);
        this.setPromptText("Select an article");
    }

    public BooleanBinding observableNotSelect() {
        return this.valueProperty().isNull();
    }
}

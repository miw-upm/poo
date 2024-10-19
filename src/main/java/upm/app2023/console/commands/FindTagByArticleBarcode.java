package upm.app2023.console.commands;

import upm.app2023.console.Command;
import upm.app2023.console.View;
import upm.app2023.console.version2.CommandNames;
import upm.app2023.data.models.Tag;
import upm.app2023.services.TagService;

import java.util.List;

public class FindTagByArticleBarcode implements Command {

    private static final String NAME = "find-tag-by-article-barcode";
    private static final String HELP = ":<codigo-barras> Presenta las atiquetas asociadas al artículo";
    private final TagService tagService;
    private final View view;

    public FindTagByArticleBarcode(TagService tagService, View view) {
        this.tagService = tagService;
        this.view = view;
    }

    @Override
    public void execute(String[] values) {
        if (values.length != 1) {
            throw new BadCommandException(CommandNames.FIND_ARTICLE_BY_TAG_NAME.getHelp());
        }
        List<Tag> tags = this.tagService.findByArticleBarcodeFunctional(values[0]);
        this.view.show(tags.toString());
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String help() {
        return NAME + HELP;
    }
}

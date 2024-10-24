package upm.appentrega3.console.commands;

import upm.appentrega3.console.Command;
import upm.appentrega3.console.View;
import upm.appentrega3.data.models.Rol;
import upm.appentrega3.data.models.Tag;
import upm.appentrega3.services.TagService;

import java.util.List;

public class CreateTag implements Command {
    private final View view;
    private final TagService tagService;

    public CreateTag(View view, TagService tagService) {
        this.view = view;
        this.tagService = tagService;
    }

    @Override
    public String name() {
        return "create-tag";
    }

    @Override
    public List<String> params() {
        return List.of("name", "description");
    }

    @Override
    public List<Rol> allowedRoles() {
        return Rol.authenticated();
    }

    @Override
    public String helpMessage() {
        return "Se crea una etiqueta";
    }

    @Override
    public void execute(String[] params) {
        Tag createdTag = this.tagService.create(new Tag(params[0], params[1]));
        this.view.show(createdTag.toString());
    }
}

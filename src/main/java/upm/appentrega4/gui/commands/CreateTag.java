package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.CreationTag;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.Tag;
import upm.appentrega4.gui.Command;
import upm.appentrega4.services.TagService;

import java.util.List;

public class CreateTag implements Command {
    private final TagService tagService;

    public CreateTag(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    public String name() {
        return "create-tag";
    }

    @Override
    public List<String> params() {
        return List.of("name", "description", "barcode");
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
    public List<Object> execute(String[] params) {
        Tag createdTag = this.tagService.create(new CreationTag(params[0], params[1], params[2]));
        return List.of(createdTag);
    }
}

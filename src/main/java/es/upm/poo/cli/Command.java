package es.upm.poo.cli;

import java.util.ArrayList;
import java.util.List;

public interface Command {
    String COMMAND_SEPARATOR = "#";
    String PARAM_SEPARATOR = ",";

    String name();

    List<String> obligatoryParams();

    List<String> optionalParams();

    String helpMessage();

    void execute(String[] params);

    default String help() {
        StringBuilder result = new StringBuilder(this.name());
        List<String> params = new ArrayList<>(this.obligatoryParams());
        params.addAll(this.optionalParams().stream()
                .map(param -> "[" + param + "]")
                .toList());
        if (!params.isEmpty()) {
            result.append(COMMAND_SEPARATOR).append(String.join(PARAM_SEPARATOR, params));
        }
        return result.append(". ").append(this.helpMessage()).append(".").toString();
    }
}

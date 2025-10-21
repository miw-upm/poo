package upm.app2.presentation.cli;

import java.util.List;

public interface Command {

    String name();

    List<String> params();

    String helpMessage();

    void execute(String[] params);

    default String help() {
        StringBuilder result = new StringBuilder(this.name());
        if (!this.params().isEmpty()) {
            result.append(Delimiters.COMMAND_PARAM_SEPARATOR.getValue())
                    .append(String.join(Delimiters.PARAM_SEPARATOR.getValue(), this.params()));
        }
        return result.append(". ")
                .append(this.helpMessage())
                .append(".").toString();
    }
}

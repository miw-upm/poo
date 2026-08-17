package es.upm.poo.cli;

import java.util.function.Function;

public class CommandParams {
    private final String[] params;

    public CommandParams(String[] params) {
        this.params = params;
    }

    public String getString(Integer index) {
        return this.getParam(index, value -> value);
    }

    public Integer getInteger(Integer index) {
        return this.getParam(index, Integer::valueOf);
    }

    public Long getLong(Integer index) {
        return this.getParam(index, Long::valueOf);
    }

    public Boolean getBoolean(Integer index) {
        return this.getParam(index, Boolean::valueOf);
    }

    private <T> T getParam(Integer index, Function<String, T> converter) {
        if (this.params.length <= index) {
            return null;
        }
        return converter.apply(this.params[index]);
    }
}

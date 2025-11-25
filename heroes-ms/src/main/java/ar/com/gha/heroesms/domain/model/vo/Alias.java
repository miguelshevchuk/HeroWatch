package ar.com.gha.heroesms.domain.model.vo;

import java.util.Objects;

public record Alias(String value) {
    public Alias {
        if (value == null) {
            throw new IllegalArgumentException("alias requerido");
        }
        String v = value.trim();
        if (v.isEmpty()) {
            throw new IllegalArgumentException("alias requerido");
        }
        value = v;
    }

    @Override
    public String toString() {
        return value;
    }
}
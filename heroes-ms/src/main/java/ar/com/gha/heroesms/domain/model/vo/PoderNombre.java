package ar.com.gha.heroesms.domain.model.vo;

public record PoderNombre(String value) {
    public PoderNombre {
        if (value == null) {
            throw new IllegalArgumentException("poder requerido");
        }
        String v = value.trim();
        if (v.isEmpty()) {
            throw new IllegalArgumentException("poder requerido");
        }
        value = v;
    }

    @Override
    public String toString() {
        return value;
    }
}
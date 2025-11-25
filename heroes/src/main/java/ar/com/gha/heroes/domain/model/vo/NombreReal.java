package ar.com.gha.heroes.domain.model.vo;

public record NombreReal(String value) {
    public NombreReal {
        if (value == null) {
            throw new IllegalArgumentException("nombreReal requerido");
        }
        String v = value.trim();
        if (v.isEmpty()) {
            throw new IllegalArgumentException("nombreReal requerido");
        }
        value = v;
    }

    @Override
    public String toString() {
        return value;
    }
}
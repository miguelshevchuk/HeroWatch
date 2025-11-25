package ar.com.gha.heroes.domain.model.vo;

public record NivelEnergia(Integer value) {
    public NivelEnergia {
        if (value == null) {
            throw new IllegalArgumentException("nivelEnergia es requerido");
        }
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("nivelEnergia debe estar entre 0 y 100");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
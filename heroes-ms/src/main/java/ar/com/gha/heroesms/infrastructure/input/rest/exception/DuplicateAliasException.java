package ar.com.gha.heroesms.infrastructure.input.rest.exception;

public class DuplicateAliasException extends GenericGHAException {
    public DuplicateAliasException(String message) {
        super(message);
    }
}

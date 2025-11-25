package ar.com.gha.heroes.infrastructure.input.rest.exception;

public class DuplicateAliasException extends GenericGHAException {
    public DuplicateAliasException(String message) {
        super(message);
    }
}

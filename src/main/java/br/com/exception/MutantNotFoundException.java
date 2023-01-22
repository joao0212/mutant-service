package br.com.exception;

public class MutantNotFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Mutant not found";
    }
}

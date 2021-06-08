package com.challenge.exception;

public class PromotionalNotFound extends RuntimeException {

    public PromotionalNotFound(Integer id) {
        super("Nenhum produto promocional foi encontrado para o Vendedor " + id + ".");
    }
}

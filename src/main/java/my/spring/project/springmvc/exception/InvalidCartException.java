package my.spring.project.springmvc.exception;

import lombok.Getter;

@Getter
public class InvalidCartException extends RuntimeException {
    private final String cartId;

    public InvalidCartException(final String cartId) {
        this.cartId = cartId;
    }
}
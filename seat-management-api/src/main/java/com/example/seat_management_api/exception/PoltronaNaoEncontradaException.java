package com.example.seat_management_api.exception;

public class PoltronaNaoEncontradaException extends RuntimeException {

    public PoltronaNaoEncontradaException(String message) {
        super(message);
    }
}
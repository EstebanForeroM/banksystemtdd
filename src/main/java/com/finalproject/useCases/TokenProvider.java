package com.finalproject.useCases;

public interface TokenProvider {

    public Token getToken(String password);

    public void destroy(Token token);

    public boolean validate(Token token);
}

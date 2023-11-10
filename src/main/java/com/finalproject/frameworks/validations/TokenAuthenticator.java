package com.finalproject.frameworks.validations;

import java.util.Set;

import com.finalproject.useCases.Token;

public interface TokenAuthenticator {

    boolean validateToken(Token token);

    void addToken(Token token);

    void deleteToken(Token token);

    public void revalidate(Set<String> ids);
}

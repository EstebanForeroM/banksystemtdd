package com.finalproject.frameworks.validations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.finalproject.useCases.Token;

public class TokenValidator implements TokenAuthenticator {

    private HashMap<String, String> tokens;
    Set<String> tokenIds;

    public TokenValidator() {
        tokens = new HashMap<>();
        tokenIds = new HashSet<>();
    }

    @Override
    public boolean validateToken(Token token) {
        return tokens.containsKey(token.getKey()) && tokens.get(token.getKey()).equals(token.getClientId());
    }

    @Override
    public void addToken(Token token) {
        tokens.put(token.getKey(), token.getClientId());
        tokenIds.add(token.getClientId());
    }

    @Override
    public void deleteToken(Token token) {
        tokens.remove(token.getKey());
    }

    @Override
    public void revalidate(Set<String> ids) {
        tokens.keySet().removeIf(key -> !ids.contains(tokens.get(key)));
        System.out.println("fd");
    }
}

package com.finalproject.useCases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;
import com.finalproject.frameworks.validations.TokenGenerator;
import com.finalproject.frameworks.validations.TokenValidator;

public class TokenProviderTest {

    private ClientRepository clientRepository;
    private TokenAuthenticationService tokenProvider;

    @BeforeEach
    public void tokenProviderCreation() {
        clientRepository = new FakeClientRepository();
        tokenProvider = new TokenGenerator(new TokenValidator(), clientRepository);
    }

    @Test
    public void testTokenValidation() {
        clientRepository.saveClient(new Client("12345", "thisisPassword", Gender.OTHER, "12345"));
        Token token = tokenProvider.getToken("12345");

        assert tokenProvider.validate(token);

        Token fakeToken = new Token("12345", "12345");

        assert !tokenProvider.validate(fakeToken);

        clientRepository.deleteClient("12345");

        assert !tokenProvider.validate(token);
    }
}

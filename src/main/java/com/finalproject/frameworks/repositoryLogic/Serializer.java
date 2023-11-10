package com.finalproject.frameworks.repositoryLogic;

public interface Serializer<T> {
    String serialize(T object);

    T deserialize(String string);
}

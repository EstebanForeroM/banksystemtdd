package com.finalproject.frameworks.repositoryLogic;

import java.io.*;

public class AppendObjectOutputStream extends ObjectOutputStream {
    public AppendObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}
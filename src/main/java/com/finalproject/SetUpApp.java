package com.finalproject;

import com.finalproject.controllers.AppController;
import com.finalproject.frameworks.TextFileClientRepository;

/**
 * Hello world!
 *
 */
public class SetUpApp {
    public static void main(String[] args) {
        AppController appController = new AppController(new TextFileClientRepository());
    }
}

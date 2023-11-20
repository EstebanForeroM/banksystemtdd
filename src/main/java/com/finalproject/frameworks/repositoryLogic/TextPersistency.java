package com.finalproject.frameworks.repositoryLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.finalproject.entities.Identifiable;

public class TextPersistency<T extends Identifiable> {

    private String filePath;
    private String fileName;
    private Serializer<T> objectSerializer;
    private List<Runnable> changeListeners;

    public HashMap<String, String> objects = new HashMap<>();

    public TextPersistency(String filePath, String fileName, Serializer<T> objectSerializer) {

        this.objectSerializer = objectSerializer;
        this.fileName = fileName;

        File file = new File(filePath + "/" + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Error creating file");
            }
        }

        this.filePath = filePath;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] objectData = data.split(",");
                objects.put(objectData[1], data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        changeListeners = new ArrayList<>();
    }

    public void saveObject(T object) {
        objects.put(object.getId(), objectSerializer.serialize(object));
        saveObjects();
        notifyChangeListeners();
    }

    public T getObject(String id) {
        String clientString = objects.get(id);
        if (clientString == null) {
            return null;
        }
        return objectSerializer.deserialize(clientString);
    }

    public void deleteObject(String id) {
        objects.remove(id);
        saveObjects();
        notifyChangeListeners();
    }

    public void updateObject(String id, T client) {
        objects.put(id, objectSerializer.serialize(client));
        saveObjects();
        notifyChangeListeners();
    }

    private void saveObjects() {

        try (FileWriter fileWriter = new FileWriter(filePath + "/" + fileName)) {
            fileWriter.write("");
            for (String client : objects.values()) {
                fileWriter.append(client + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<T> getObjectsList() {
        Set<T> objectsList = new HashSet<T>();

        for (String desirializedString : objects.values()) {
            objectsList.add(objectSerializer.deserialize(desirializedString));
        }

        return objectsList;
    }

    public void addChangeListener(Runnable callback) {
        changeListeners.add(callback);
    }

    private void notifyChangeListeners() {
        for (Runnable runnable : changeListeners) {
            runnable.run();
        }
    }
}

package org.example.task2;

import org.example.TaskThree;

import java.io.*;
import java.util.*;

public class TaskTwo {
    private static final String absolutePath = ".src\\main\\resources\\task2\\file.txt";
    private static final String absoluteJSONPath = ".src\\main\\resources\\task2\\user.json";
    public static void main(String[] args) {

        File file = new File(absolutePath);
        File fileJSON = new File(absoluteJSONPath);

        TaskThree.makeFile(file);
        TaskThree.makeFile(fileJSON);

        List<User> users = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            if (bufferedReader.read() == -1){
                return;
            }
            User.setKeys(bufferedReader.readLine().split(" "));

            String[] line;
            User user;
            while (bufferedReader.read() != -1) {
                line = bufferedReader.readLine().split(" ");
                user = new User(line);
                users.add(user);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileJSON))){
            String data = User.usersToString(users);
            bufferedWriter.write(data);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

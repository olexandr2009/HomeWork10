package org.example.tasktwo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.TaskThree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskTwo {
    public static final String PATH = "./src/main/resources/task2/file.txt";
    public static final String JSON_PATH = "./src/main/resources/task2/user.json";
    public static void main(String[] args) {
        File file = new File(PATH);
        File fileJSON = new File(JSON_PATH);

        TaskThree.makeFile(file);
        TaskThree.makeFile(fileJSON);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileJSON)))
        {

            String line = bufferedReader.readLine();
            String[] parts;

            List<User> users = new ArrayList<>();
            while (line != null) {
                line = bufferedReader.readLine();
                if (line == null){
                    break;
                }
                removeSpaces(line);
                parts = line.split(" ");
                users.add(new User(parts[0],Integer.parseInt(parts[1])));
            }
            bufferedWriter.write(gson.toJson(users));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static String removeSpaces(String toRemove){
        if (toRemove == null){
            return null;
        }
        String last;
        do {
            last = toRemove;
            toRemove = toRemove.replace("  ", " ");
        }while (!toRemove.equals(last));
        return toRemove;
    }

}

package org.example;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TaskOne {
    private static final String absolutePath = ".src\\main\\resources\\task1\\file.txt";
    public static void main(String[] args) {
        File file = new File(absolutePath);

        TaskThree.makeFile(file);

        try(BufferedInputStream buffInputStream = new BufferedInputStream(new FileInputStream(file))){
            printTelNumber(buffInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void printTelNumber(BufferedInputStream buffInputStream) throws IOException {
        byte[] data = buffInputStream.readAllBytes();
        String allNumbers = new String(data);
        String[] splitedNumbers = allNumbers.split("\r\n");
        for (String telNumber:splitedNumbers) {
            if (telNumber.matches("^\\d{3}-\\d{3}-\\d{4}$") || telNumber.matches("^[(]\\d{3}[)] \\d{3}-\\d{4}$")){
                System.out.println(telNumber);
            }
        }
    }
}
package org.example;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class TaskThree {
    private static final String absolutePath = "C:\\IdeaProgects\\HomeWork10\\src\\main\\resources\\task3\\words.txt";
    public static void main(String[] args) {
        File file = new File(absolutePath);
        makeFile(file);
        try(BufferedInputStream buffInputStream = new BufferedInputStream(new FileInputStream(file))){
            printWordFreqFile(buffInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void printWordFreqFile(BufferedInputStream buffInputStream) throws IOException {
        byte[] data = buffInputStream.readAllBytes();

        String all = new String(data);
        String words[] = removeSpaces(all).replace("\r\n", " ").split("\\W");

        Map<String, Integer> mp = new HashMap<>();

        for (String word : words) {
            if (mp.containsKey(word)) {
                mp.put(word, mp.get(word) + 1);
            } else {
                mp.put(word, 1);
            }
        }

        mp = sortByValue(mp);

        String[] arrKeys = new LinkedHashSet<>(mp.keySet()).toArray(new String[0]);
        for (int i = 0; i < mp.size(); i++) {
            System.out.println(arrKeys[i] + " " + mp.get(arrKeys[i]));
        }
    }


    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(new Comparator<>() {
            @Override
            public int compare(V o1, V o2) {
                Integer i1 = (Integer) o1;
                Integer i2 = (Integer) o2;
                if (i1 < i2){
                    return 1;
                } else if (i1 > i2) {
                    return -1;
                }
                return 0;
            }
        }));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    private static String removeSpaces(String toRemove){
        String last;
        do {
            last = toRemove;
            toRemove = toRemove.replace("  ", " ");
        }while (!toRemove.equals(last));
        return toRemove;
    }
    public static void makeFile(File file){
        if (!file.exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
/*
the 4
is 3
sunny 2
day 1
 */

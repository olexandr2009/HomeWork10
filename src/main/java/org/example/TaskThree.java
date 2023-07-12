package org.example;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class TaskThree {
    public static void main(String[] args) {
        String absolutePath = "C:\\IdeaProgects\\HomeWork10\\src\\main\\resources\\words.txt";
        File file = new File(absolutePath);
        if (!file.exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try(BufferedInputStream buffInputStream = new BufferedInputStream(new FileInputStream(file))){
            printWordFreqFile(buffInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void printWordFreqFile(BufferedInputStream buffInputStream) throws IOException {
        byte[] data = buffInputStream.readAllBytes();

        String all = new String(data);
        String words[] = all.replace("\r\n", " ").split("\\W");

        Map<String, Integer> mp = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            if (mp.containsKey(words[i])) {
                mp.put(words[i], mp.get(words[i]) + 1);
            } else {
                mp.put(words[i], 1);
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
        list.sort(Map.Entry.comparingByValue(new Comparator<V>() {
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
}
/*
the 4
is 3
sunny 2
day 1
 */

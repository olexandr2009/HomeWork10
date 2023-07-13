package org.example.task2;

import java.util.*;

public class User{


    private static String[] keys;
    private String[] values;


    public User(String[] values) {
        this.values = Arrays.copyOf(values,keys.length);
    }
    public static void setKeys(String[] keys){
        User.keys = Arrays.copyOf(keys,keys.length);
    }

    public static String usersToString(List<User> users ) {
        Iterator it = users.iterator();
        if (!it.hasNext())
            return "[]";
        StringBuilder sb = new StringBuilder();

        while (it.hasNext()){
            sb.append(it.next() + ",");
        }
        sb.deleteCharAt(sb.length()-1);

        return  "[" + sb + "\n]";
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\t{\n");
        for (int i = 0; i < keys.length;i++) {
            if (i == keys.length - 1){
                sb.append(String.format("\t\t\"%s\": \"%s\"\n",keys[i],values[i]));
            }else {
                sb.append(String.format("\t\t\"%s\": \"%s\",\n",keys[i],values[i]));
            }
        }
        sb.append("\t}");
        return sb.toString();
    }
}


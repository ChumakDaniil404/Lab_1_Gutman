package ua.com.prologistic;

import java.io.*;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class DictionaryClassSecond implements IDictionaryInterface {
    Dictionary<String, String> dict = new Hashtable<>();
    String key;
    Enumeration<String> keys = dict.keys();
    Enumeration<String> values;
    String value;
    public void readFileLineByLine(String way, Boolean invert) {
        try {
            File file = new File(way);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println(line);
                if (line.contains(" ")) {
                    if (invert == true){
                    Boolean line_right = line.matches("\\w{4}\\s\\w{5}");
                    if (line_right) {
                        String[] parts = line.split(" ");
                        String key = parts[0];
                        String value = parts[1];
                        check(key, value);
                        System.out.println("Строка соответствует всем условиям");
                        System.out.println("---");
                    }
                    else {System.out.println("Строка не соответствует условию"); System.out.println("---");}
                }
                    else
                    {
                        Boolean line_right = line.matches("\\w{5}\\s\\w{4}");
                        if (line_right) {
                            String[] parts = line.split(" ");
                            String key = parts[1];
                            String value = parts[0];
                            System.out.println(key + " Приверка " + value);
                            check(key, value);
                            System.out.println("Строка соответствует всем условиям");
                            System.out.println("---");
                        }
                        else {System.out.println("Строка не соответствует условию"); System.out.println("---");}
                    }
                }
                else {System.out.println("Строка не содержит пробел");System.out.println("---");}
            }
        } catch (Exception e) {                                        //так чи нет?
            System.out.println("Не удалось считать файл");
        }
    }
    public void check(String key, String value)
    {
        Boolean key_right = key.matches("[a-zA-Z]{4}");
        Boolean value_right = value.matches("\\d{5}");
        if (key_right & value_right)
        {
            dict.put(key,value);
        }
        else
        {
            System.out.println("Неверно введенные данные(");
        }
    }
    public void Chtenie() {
        keys = dict.keys();
        while (keys.hasMoreElements()) {
            key = keys.nextElement();
            System.out.println("Key: " + key + ", Value: " + dict.get(key));
        }
    }
    public void PerevernytSlovar()
    {
        keys = dict.keys();
        values = dict.elements();
        Dictionary<String, String> time = new Hashtable<>();

        while (keys.hasMoreElements()) {
            key = values.nextElement();
            value = keys.nextElement();
            time.put(key, value);
            dict.remove(value);
            dict = time;
            //System.out.println("Key: " + key + ", Value: " + value);
        }
    }
    public void Poisk(String key)
    {
        System.out.print(dict.get(key));
    }
    public void Ydalenie(String key)
    {
        System.out.print(dict.remove(key));
    }

    public void writeUsingOutputStream(String way) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(way));
            os.write(SlovarToString().getBytes(), 0, SlovarToString().length());
        } catch (IOException var12) {
            var12.printStackTrace();
        }
    }
    public String SlovarToString()
    {
        String string = dict.toString();
        string = string.substring(1, string.length() - 1);
        string.replace("=", " ");
        string.replace(", ", "\n");
        return string;
    }
}

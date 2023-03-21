/*
1)чтение списка пар из файла
2)удаление записи по ключу+
3)поиск записи по ключу+
4)добавление записей при условии соответствия требованиям конкретного словаря+
5)словарь один при смене значение ключа переходит в значение о наоборот!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! и проверки в 3
*/
//количество символов строго 5 цифр(ключ) и строго 4 у значения (латиница)

package ua.com.prologistic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Main {
    static Boolean flags = true;
    public static void main(String[] args) throws IOException {
        Dictionary<String, String> dict = new Hashtable<>();
        String key;
        String value;
        Boolean flags = true;

        Scanner w = new Scanner(System.in);
        boolean exit = false;
        String way = ""; //путь
        while(!exit) {
            System.out.println("Ваши действия: \n1. Вывод(блокнота) и запись в словарь\n2. Вывод(словаря) \n3. Добавление записи\n4. Поиск записи по ключу\n5. Удаленние записи по ключу\n" +
                    "6. Поменять словари местами\n7. Выход ");
            int number = w.nextInt();
            switch (number) {
                case 1://считывает блокнот и записывает в словарь
                    System.out.println("Введите путь к файлу ");
                    way = w.next();
                    System.out.println("Содержимое файла: ");
                    ReadFileLineByLine(way, dict);
                    System.out.println("\n");
                    break;
                case 2://вывод того что в словаре
                    Enumeration<String> keys = dict.keys();
                    while (keys.hasMoreElements()) {
                        key = keys.nextElement();
                        System.out.println("Key: " + key + ", Value: " + dict.get(key));
                    }
                    break;
                case 3://добавление записи
                    try {
                        System.out.println("Введите ключ");
                        key = w.next();
                        System.out.println("Введите значение");
                        value = w.next();
                        ProverOchka(key,value,dict);
                    }//добавить проверки
                    catch (Exception e) {
                        e.printStackTrace();}
                    break;
                case 4://поиск записи по ключу
                    System.out.println("Введите ключ");
                    key = w.next();
                    System.out.print(dict.get(key));
                    System.out.println("\n");
                    break;
                case 5:// удаление записи по ключу
                    System.out.println("Введите ключ");
                    key = w.next();
                    System.out.print(dict.remove(key));
                    System.out.println("\n");
                    break;
                case 6: //Перевернуть словарь
                    keys = dict.keys();
                    Enumeration<String> values = dict.elements();
                    Dictionary<String, String> time = new Hashtable<>();

                    while (keys.hasMoreElements()) {
                        key = values.nextElement();
                        value = keys.nextElement();
                        time.put(key, value);
                        dict.remove(value);
                        dict = time;
                        System.out.println("Key: " + key + ", Value: " + value/*dict.get(key)*/);
                    }
                    flags = !flags;
                    break;
                case 7://выход
                    exit = true;
            }
        }
    }
    //not using
    private static void writeUsingOutputStream(String way, String str) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(way));
            os.write(str.getBytes(), 0, str.length());
        } catch (IOException var12) {
            var12.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException var11) {
                var11.printStackTrace();
            }
        }
    }
    public static void ReadFileLineByLine(String way, Dictionary dictionary) {
        try {
            File file = new File(way);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println(line);
                if (line.contains(" ")) {
                    Boolean line_right = line.matches("\\w{4,5}\\s\\w{4,5}");
                    //Boolean right_line = true;
                    if (line_right) {
                        String[] parts = line.split(" ");
                        String key = parts[0];
                        String value = parts[1];
                        ProverOchka(key, value, dictionary/*flags*/);
                    }
                    else {System.out.println("Строка не соответствует условию");}
                }
                else {System.out.println("Строка не содержит пробел");}
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }
    public static void ProverOchka(String key, String value, Dictionary dictionary /*Boolean flags*/)
    {
        if (flags)
        {
            Boolean key_right = key.matches("\\d{5}");
            Boolean value_right = value.matches("[a-zA-Z]{4}");
            if (key_right & value_right)
            {
                dictionary.put(key,value);
            }
            else
            {
                System.out.println("Неверно введенные данные(");
            }
        }
        else
        {
            Boolean key_right = value.matches("\\d{5}");
            Boolean value_right = key.matches("[a-zA-Z]{4}");
            if (key_right & value_right)
            {
                dictionary.put(key,value);
            }
            else
            {
                System.out.println("Неверно введенные данные(");
            }
        }
    }
}

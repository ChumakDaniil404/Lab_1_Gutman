/*
1)чтение списка пар из файла
2)удаление записи по ключу+
3)поиск записи по ключу+
4)добавление записей при условии соответствия требованиям конкретного словаря+
5)словарь один при смене значение ключа переходит в значение о наоборот!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! и проверки в 3
*/
//количество символов строго 5 цифр(ключ) и строго 4 у значения (латиница)

package ua.com.prologistic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Main {
    //static Boolean flags = true;

    public static void main(String[] args) throws IOException {
        //Dictionary<String, String> dict = new Hashtable<>();
        String key;
        String value;
        //Boolean flags = true;
        DictionaryClass dicclass = new DictionaryClass();
        DictionaryClassSecond dicclass2 = new DictionaryClassSecond();
        Boolean flags = true; //для первого словаря (правда)
        Enumeration<String> keys;

        Scanner w = new Scanner(System.in);
        boolean exit = false;
        String way = ""; //путь
        try {
            while (!exit) {
                System.out.println("Ваши действия: \n1. Вывод(блокнота) и запись в словарь\n2. Вывод(словаря) \n3. Добавление записи\n4. Поиск записи по ключу\n5. Удаленние записи по ключу\n" +
                        "6. Поменять словари местами\n7. Выход ");
                int number = w.nextInt();
                switch (number) {
                    case 1://считывает блокнот и записывает в словарь
                        System.out.println("Введите путь к файлу ");
                        way = w.next();
                        System.out.println("Содержимое файла: ");
                        if (flags) {dicclass.readFileLineByLine(way, true);}
                        else {dicclass2.readFileLineByLine(way,true);}
                        System.out.println("\n");
                        break;
                    case 2://вывод того что в словаре
                        if (flags) {dicclass.Chtenie();}
                        else {dicclass2.Chtenie();}
                        break;
                    case 3://добавление записи
                        try {
                            System.out.println("Введите ключ");
                            key = w.next();
                            System.out.println("Введите значение");
                            value = w.next();
                            if (flags) {dicclass.check(key, value);}
                            else {dicclass2.check(key, value);}
                        }                                           //добавить проверки
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4://поиск записи по ключу
                        System.out.println("Введите ключ");
                        key = w.next();
                        if (flags) {dicclass.Poisk(key);}
                        else {dicclass2.Poisk(key);}
                        System.out.println("\n");
                        break;
                    case 5:// удаление записи по ключу
                        System.out.println("Введите ключ");
                        key = w.next();
                        if (flags){dicclass.Ydalenie(key);}
                        else {dicclass2.Ydalenie(key);}
                        System.out.println("\n");
                        break;
                    case 6: //Перевернуть словарь
                        flags = !flags;
                        if(flags)
                        {   //dicclass.clear();
                            dicclass2.PerevernytSlovar();
                            System.out.println("Введите путь");
                            way = w.next();
                            dicclass2.writeUsingOutputStream(way);
                            dicclass.readFileLineByLine(way, true);
                            dicclass.Chtenie();
                         System.out.println("Вы работаете с первым словарем");}
                        else
                        {
                        // dicclass2.clear(); очистить словарь
                         System.out.println("Введите путь");
                         way = w.next();
                         dicclass.writeUsingOutputStream(way);
                         dicclass2.readFileLineByLine(way, false);
                         dicclass2.Chtenie();
                         System.out.println("Вы работаете со вторым словарем");}
                        break;
                    case 7://выход
                        exit = true;
                    default:
                        System.out.println("Введена неверная команда");
                }
            }
        } catch (Exception e) {
            System.out.println("Данные введены неверно");
        }
    }
}
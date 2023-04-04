package ua.com.prologistic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String key;
        String value;
        DictionaryClass d1 = new DictionaryClass();
        DictionaryClassSecond d2 = new DictionaryClassSecond();
        boolean flags = true; //для первого словаря (правда)
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        String path = ""; //путь
        try {
            while (!exit) {
                System.out.println("Ваши действия: \n1. Вывод(блокнота) и запись в словарь\n2. Вывод(словаря) \n3. Добавление записи\n4. Поиск записи по ключу\n5. Удаление записи по ключу\n" +
                        "6. Поменять словари местами\n7. Выход ");
                int number = checkingForASymbol();
                switch (number) {
                    case 1://считывает блокнот и записывает в словарь
                        System.out.println("Введите путь к файлу ");
                        path = sc.next();
                        System.out.println("Содержимое файла: ");
                        if (flags) {
                            d1.readFileLineByLine(path, true);
                        } else {
                            d2.readFileLineByLine(path, true);
                        }
                        System.out.println("\n");
                        break;
                    case 2://вывод того что в словаре
                        if (flags) {
                            d1.outputFromTheDictionary();
                        } else {
                            d2.outputFromTheDictionary();
                        }
                        break;
                    case 3://добавление записи
                        try {
                            System.out.println("Введите ключ");
                            key = sc.next();
                            System.out.println("Введите значение");
                            value = sc.next();
                            if (flags) {
                                d1.check(key, value);
                            } else {
                                d2.check(key, value);
                            }
                        } catch (Exception e) {
                            System.out.println("Не удалось добавить запись");
                        }
                        break;
                    case 4://поиск записи по ключу
                        System.out.println("Введите ключ");
                        key = sc.next();
                        if (flags) {
                            d1.search(key);
                        } else {
                            d2.search(key);
                        }
                        System.out.println("\n");
                        break;
                    case 5:// удаление записи по ключу
                        System.out.println("Введите ключ");
                        key = sc.next();
                        if (flags) {
                            d1.removal(key);
                        } else {
                            d2.removal(key);
                        }
                        System.out.println("\n");
                        break;
                    case 6: //Перевернуть словарь
                        flags = !flags;
                        if (flags) {
                            d1.clear();
                            d2.flipTheDictionary();
                            System.out.println("Введите путь");
                            path = sc.next();
                            d2.writeUsingOutputStream(path);
                            d1.readFileLineByLine(path, true);
                            d1.outputFromTheDictionary();
                            System.out.println("Вы работаете с первым словарем");
                        } else {
                            d2.clear();
                            System.out.println("Введите путь");
                            path = sc.next();
                            d1.writeUsingOutputStream(path);
                            d2.readFileLineByLine(path, false);
                            d2.outputFromTheDictionary();
                            System.out.println("Вы работаете со вторым словарем");
                        }
                        break;
                    case 7://выход
                        exit = true;
                        break;
                    default:
                        System.out.println("Введена неверная команда");
                }
            }
        } catch (Exception e) {
            System.out.println("Данные введены неверно");
        }
    }

    public static int checkingForASymbol() {
        System.out.println("Выберите пункт меню");
        try {
            Scanner sc2 = new Scanner(System.in);
            return sc2.nextInt();
        } catch (Exception e) {
            System.out.println("Данные введены неверно. Пожалуйста повторите ввод команды из пунктов меню.");
            return -1;
        }
    }
}
package me.ssagan.dorothyapp._main;

import me.ssagan.dorothyapp.util.FileUtils;
import me.ssagan.dorothyapp.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class _Main {
    public static void main(String[] args) {
        String fairyTale = "";
        try {
            fairyTale = FileUtils.readStringFromFile("src/main/resources/text_txt.txt");
        } catch (IOException e) {
            System.out.println("Возникло исключение при чтении из файла: " + e.toString());
        }

        //получим массив слов из сказки
        String[] stringArray = StringUtil.split(fairyTale);

        //получим массив глючных слов
        ArrayList<String> failedWordsArray = new ArrayList();
        for (int i = 0; i < stringArray.length; i++) {
            if (StringUtil.isFailure(stringArray[i])) {
                failedWordsArray.add(stringArray[i]);
            }
        }
        //названия файлов
        String fileName1 = failedWordsArray.get(0) + ".txt";
        String fileName2 = failedWordsArray.get(1) + ".txt";
        String fileName3 = "punctuation.txt";

        //удалим глючные слова из сказки
        ArrayList<String> arr = StringUtil.deleteFailureWords(stringArray);

        //подготовоим директорию
        File dir = new File("data");
        if (dir.exists()) {
            FileUtils.recursiveDelete(dir);
        }
        dir = new File("data/" + LocalDate.now().toString());
        dir.mkdirs();


        //запишем первый файл
        String firstFileString = StringUtil.getEvenVowelsWords(arr);
        try {
            FileUtils.writeStringToFile(dir + "/" + fileName1, firstFileString);
        } catch (IOException e) {
            System.out.println("Возникло исключение при записи в файл: " + e.toString());
        }

        //запишем второй файл
        String secondFileString = StringUtil.getNotEvenVowelsWords(arr);
        try {
            FileUtils.writeStringToFile(dir + "/" + fileName2, secondFileString);
        } catch (IOException e) {
            System.out.println("Возникло исключение при записи в файл: " + e.toString());
        }

        //запишем третий файл
        String punctuationString = StringUtil.getPunctuationMap(fairyTale);
        try {
            FileUtils.writeStringToFile(dir + "/" + fileName3, punctuationString);
        } catch (IOException e) {
            System.out.println("Возникло исключение при записи в файл: " + e.toString());
        }
    }
}
package me.ssagan.dorothyapp.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtil {
    public static boolean isFailure(String string) {
        return string.matches(".*[~&^*].*");
    }

    public static String[] split(String string) {
        //return string.split("[ ,.!?\n]");

        return string.split("\\s*(\\s|,|:|;|!|//?|\\n|\\.)\\s*");
    }

    public static int countVowel(String string) {
        int countVowel = 0;
        //Pattern pattern = Pattern.compile(".*[aeiouy].*");
        Pattern pattern = Pattern.compile("[aeiouy]");
        Matcher matcher = pattern.matcher(string.toLowerCase());
        while (matcher.find()) {
            countVowel++;
        }
        return countVowel;
    }

    public static ArrayList<String> deleteFailureWords(String[] stringArray) {
        return (ArrayList<String>) Arrays
                .stream(stringArray)
                .filter((str) -> !StringUtil.isFailure(str))
                .collect(Collectors.toList());
    }

    public static String getEvenVowelsWords(ArrayList<String> stringArray) {
        StringBuilder sb = new StringBuilder();
        stringArray
                .stream()
                .filter((str) -> (str.length() > 0) && (StringUtil.countVowel(str) % 2 == 0))
                .distinct()
                .forEach(str -> sb.append(str + " "));
        return sb.toString();
    }

    public static String getNotEvenVowelsWords(ArrayList<String> stringArray) {
        StringBuilder sb = new StringBuilder();
        stringArray
                .stream()
                .filter((str) -> (str.length() > 0) && (StringUtil.countVowel(str) % 2 != 0))
                .distinct()
                .forEach(str -> sb.append(str + " "));
        return sb.toString();
    }

    public static String getPunctuationString(String str) {
        HashMap<Character, Integer> punctuationMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            if (ch.equals('.') || ch.equals(',') || ch.equals('?') || ch.equals('!') || ch.equals(':') || ch.equals(';')) {
                Integer n = punctuationMap.get(str.charAt(i));
                if (n == null) {punctuationMap.put(str.charAt(i), 1);}
                else {punctuationMap.put(str.charAt(i), ++n);}
            }
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Character, Integer>> itr = punctuationMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<Character, Integer> item = itr.next();
            sb.append(item.getKey() + " " + item.getValue() + "\n");
        }
        return sb.toString();
    }
}

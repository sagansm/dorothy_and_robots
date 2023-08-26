package me.ssagan.dorothyapp.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    public static String readStringFromFile(String fileName) throws IOException {
        String str = "";
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(fileName)) {
            int inChar = reader.read();
            while (inChar != -1) {
                stringBuilder.append((char) inChar);
                inChar = reader.read();
            }
            str = stringBuilder.toString();
        }
        return str;
    }

    public static void writeStringToFile(String fileName, String str) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(str);
        }
    }

    public static void recursiveDelete(File file) {
        // до конца рекурсивного цикла
        if (!file.exists()) return;
        //если это папка, то идем внутрь этой папки и вызываем рекурсивное удаление всего, что там есть
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                // рекурсивный вызов
                recursiveDelete(f);
            }
        }
        // вызываем метод delete() для удаления файлов и пустых(!) папок
        file.delete();
    }
}

package task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
/*
        1. Вернемся к домашнему заданию занятия номер 12 и модифицируем его.+
Программа должна получать имена файлов с номерами документов с консоли:
каждая новая строка - это путь к файлу и имя файла.+
Для завершения ввода списка файлов следует ввести 0.+
После получения списка документов программа должна обработать
каждый документ: вычитать из файла номера документов и провалидировать их. +
В конце работы создать один файл отчет с выходной информаций: номер
документа - комментарий(валиден или не валиден и по какой причине).
Пусть каждый файл содержит каждый номер документа с новой строки и
в строке никакой другой информации, только номер документа.
Валидный номер документа должен иметь длину 15 символов и
начинаться с последовательности docnum(далее любая последовательность букв/цифр)
или kontract(далее любая последовательность букв/цифр).
Учесть, что номера документов могут повторяться в пределах одного
файла и так же разные документы могут содержать одни и те же номера документов.
Если номера документов повторяются, то повторные номера документов не проверять, не валидировать.
/////////////////////////////
Немного технических деталей
1) Считать с консоли список документов: раз список - то это коллекции типа List,
никаких других условий нет - значит все имена файлов с консоли сохраняем в структуру данных ArrayList.
2) Номера документов могут повторяться, но повторные документы
обрабатывать не надо и валидировать не надо,т.е. по сути дубликаты
нам не нужны - значит, надо считать номера документов из файлов и все
номера документов сохранять в коллекцию типа Set. Других условий нет, значит можно использовать HashSet.
3) В конце должна быть структура номер документа - комментарий - т.е.
эта структура типа ключ-значений, значит это коллекция типа Мар.
Других условий нет - значит, можно использовать HashMap.
Создать такую структуру и уже потом сделать цикл по этой структуре и записать
всю информацию из этой мапы в файл-отчет.
         */
        BufferedWriter bufferedWriter;
        BufferedWriter bufferedWriter2;
        BufferedReader bufferedReader;
        Scanner sc = new Scanner(System.in);
        ArrayList<FileInfo> fileInfos = new ArrayList<>();
        while (true) {
            System.out.println("Ведите имя файла и путь к файлу: ");
            String fileName = sc.nextLine();
            String path = sc.nextLine();
            if (fileName.equals("0")) {
                break;
            } else {
                FileInfo fileInfo = new FileInfo(fileName, path);
                System.out.println(fileInfo);
                fileInfos.add(fileInfo);
            }
        }
        System.out.println(fileInfos);
        HashSet<String> lines = new HashSet<>();
        for (FileInfo fileInfo : fileInfos) {
            bufferedReader = new BufferedReader(new FileReader(fileInfo.getFileName()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
        }

        HashMap<String, String> docMap = new HashMap<>();
        for (String line : lines) {
            if (line.length() == 15 && (line.startsWith("docnum") || line.startsWith("contract")) && line.matches("\\p{Alnum}*")) {
                docMap.put(line, "Valid document");
            } else if (line.length() != 15) {
                docMap.put(line, "Invalid document: Строка не имеет длину 15");
            } else if (!line.startsWith("docnum") || !line.startsWith("contract")) {
                docMap.put(line, "Invalid document: Строка не начинается с docnum или contract");
            } else if (!line.matches("\\p{Alnum}*")) {
                docMap.put(line, "Invalid document: Строка не содержит ни букв, ни цифр");
            } else {
                throw new RuntimeException();
            }
        }
        bufferedWriter = new BufferedWriter(new FileWriter("reportFile.txt"));
        for (Map.Entry<String, String> doc : docMap.entrySet()) {
           bufferedWriter.write(doc.getKey() + " " + doc.getValue()+ "\n");
        }
        bufferedWriter.close();
    }
}


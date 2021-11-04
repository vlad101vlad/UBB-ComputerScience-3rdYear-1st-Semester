package com.company.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileOperationsUtils {
    public static List<String> readTokens() throws FileNotFoundException {
        File tokensFile = new File("src/com/company/input/tokens.in");
        Scanner scanner = new Scanner(tokensFile);

        List<String> tokens = computeTokens(scanner);

        return tokens;
    }

    public static List<String> computeTokens(Scanner tokensScanner){
        List<String> tokens;

        String lines = "";
        while (tokensScanner.hasNextLine())
            lines += tokensScanner.nextLine();
        tokensScanner.close();

        String[] tokensArray = lines.split(",");
        tokens = new ArrayList<>(Arrays.asList(tokensArray));
        return tokens;
    }

    public static String readApplication() throws FileNotFoundException {
        String applicationText = "";

        File applicationFile = new File("src/com/company/input/app2.vlad");
        Scanner scanner = new Scanner(applicationFile);
        while(scanner.hasNextLine()){
            applicationText += scanner.nextLine();
        }
        scanner.close();
        return applicationText;
    }

    public static void writeToFile(String fileName, Object toBeWritten) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(toBeWritten);
        printWriter.close();
    }
}

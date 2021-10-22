package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperationsUtils {
    public static List<String> readTokens(){
        List<String> tokens = new ArrayList<>();

        File tokensFile = new File("src/com/company/input/tokens.in");
        //TODO: finish implementing this

        return tokens;
    }

    public static String readApplication() throws FileNotFoundException {
        String applicationText = "";

        File applicationFile = new File("src/com/company/input/app.vlad");
        Scanner scanner = new Scanner(applicationFile);
        while(scanner.hasNextLine()){
            applicationText += scanner.nextLine();
        }
        scanner.close();
        return applicationText;
    }
}

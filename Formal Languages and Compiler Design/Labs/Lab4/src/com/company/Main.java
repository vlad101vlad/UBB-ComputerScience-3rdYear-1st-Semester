package com.company;

import com.company.service.MainService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        List<String> tokens = FileOperationsUtils.readTokens();
        MainService mainService = new MainService(tokens);

        String applicationText = FileOperationsUtils.readApplication();
        mainService.run(applicationText);

    }
}
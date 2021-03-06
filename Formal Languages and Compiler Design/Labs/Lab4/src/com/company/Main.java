package com.company;

import com.company.service.FileOperationsUtils;
import com.company.service.MainService;

import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        List<String> tokens = FileOperationsUtils.readTokens();
        MainService mainService = new MainService(tokens);

        String applicationText = FileOperationsUtils.readApplication();
        mainService.run(applicationText);

    }
}

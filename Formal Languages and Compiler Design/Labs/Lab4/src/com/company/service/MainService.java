package com.company.service;

import java.util.List;

public class MainService {
    private List<String> tokens;

    public MainService(List<String> tokens) {
        this.tokens = tokens;
    }

    public void run(String applicationText){
        boolean endOfFile = false;
        int currentIndex = 0;

        while(!endOfFile){
            int nextBlankSpace = applicationText.indexOf(' ');
            if(nextBlankSpace < 0){
                break;
            }
            String possbileToken = applicationText.substring(currentIndex, nextBlankSpace);

            if(checkReservedOperatorSeparator()){
                //TODO: generate PIF
            }else{
                if(checkIdentifierOrConstant()){
                    //TODO: generate PIF
                }
                else{
                    //TODO: throw lexical error
                }
            }
        }
    }

    private boolean checkIdentifierOrConstant() {
        //TODO: finish implementing this
        return true;
    }

    private boolean checkReservedOperatorSeparator() {
        //TODO: finish implementing this
        return true;
    }
}

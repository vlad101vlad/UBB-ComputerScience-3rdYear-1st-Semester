package com.company.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainService {
    private List<String> tokens;

    public MainService(List<String> tokens) {
        this.tokens = tokens;
    }

    public void run(String applicationText){
        boolean endOfFile = false;
        int currentIndex = 0;

        List<String> candidateTokens = generateCandidateTokens(applicationText);

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

    /**
     * This function takes the source code and it splits it in all the possible words/separators to be further interpreted
     * by the
     * @param applicationSourceCode - the source code in the language
     * @return - List<String> possible token candidates
     */
    private List<String> generateCandidateTokens(String applicationSourceCode){
        String[] tokens = applicationSourceCode.trim().split("\\s+");
        return new ArrayList<>(Arrays.asList(tokens));
    }

    private List<String> cleanupCandidateTokens(List<String> candidateTokens){
        List<String> finalCandidateTokens = new ArrayList<>();

        candidateTokens.forEach(candidateToken -> {

        });

        return finalCandidateTokens;
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

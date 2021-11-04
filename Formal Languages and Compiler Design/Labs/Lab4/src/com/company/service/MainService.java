package com.company.service;

import com.company.domain.PIF;
import com.company.domain.SymbolTable;

import java.io.IOException;
import java.util.*;

public class MainService {
    private List<String> tokens;
    private List<String> separators = new ArrayList<>();
    private PIF pif;
    private SymbolTable symbolTable;
    private String LEXICAL_ERROR = "";

    public MainService(List<String> tokens) {
        this.tokens = tokens;
        initSeparators(tokens);

        this.pif = new PIF();
        this.symbolTable = new SymbolTable(31);
    }

    private void initSeparators(List<String> tokens){
        tokens.forEach(token -> {
            if(token.length() <= 2)
                separators.add(token);
        });
    }

    public void run(String applicationText) throws IOException {

        List<String> candidateTokens = generateCandidateTokens(applicationText);

        for(String candidateToken: candidateTokens){
            TokenChecker tokenChecker = new TokenChecker(candidateToken, tokens);
            String result = tokenChecker.checkToken();


            switch (result){
                case "token":
                    this.pif.addToPIF(candidateToken, -1);
                    break;
                case "constant":
                case "identifier":
                    int position = symbolTable.addToTable(result, candidateToken);
                    this.pif.addToPIF(candidateToken, position);
                    break;
                default:
                    LEXICAL_ERROR += "Lexical error at: " + candidateToken;
                    break;
            }
        }

        FileOperationsUtils.writeToFile("src/com/company/output/ST.out", this.symbolTable);
        FileOperationsUtils.writeToFile("src/com/company/output/PIF.out", this.pif);
        if(!LEXICAL_ERROR.equals(""))
            System.out.println(LEXICAL_ERROR);
        else
            System.out.println("Lexically correct");
    }

    /**
     * This function takes the source code and it splits it in all the possible words/separators to be further interpreted
     * by the
     * @param applicationSourceCode - the source code in the language
     * @return - List<String> possible token candidates
     */
    private List<String> generateCandidateTokens(String applicationSourceCode){
        String[] tokens = applicationSourceCode.trim().split("\\s+");
        List<String> generatedTokens =  new ArrayList<>(Arrays.asList(tokens));
        return cleanupCandidateTokens(generatedTokens);

    }

    /**
     * Separators cand be next to identifiers/constants/other tokens, thus we need to split the words
     * from possible separators
     * @param candidateTokens
     * @return List<String> finalCandidatesTokens
     */
    private List<String> cleanupCandidateTokens(List<String> candidateTokens){
        List<String> finalCandidateTokens = new ArrayList<>();
        final String[] separators = {""};
        this.separators.forEach(separator -> separators[0] += separator);
        separators[0] = StringOperationUtils.removeDuplicates(separators[0]);


        candidateTokens.forEach(candidateToken -> {
            String auxiliaryToken = candidateToken;
            boolean foundSeparator = false;

            for(int index = 0; index < candidateToken.length(); index++){
                char c = candidateToken.charAt(index);
                int nextIndex = separators[0].indexOf(c);
                if(nextIndex > 0){
                    auxiliaryToken = auxiliaryToken.substring(0, index)
                            + "~" + c + "~";
                    if(index+1 < candidateToken.length()-1)
                        auxiliaryToken += candidateToken.substring(index+1);
                    foundSeparator = true;
                }
            }
            if(foundSeparator){
                String[] split = auxiliaryToken.split("~");
                Arrays.stream(split).forEach(newSplit -> finalCandidateTokens.add(newSplit));
            }else{
                finalCandidateTokens.add(candidateToken);
            }
        });

        StringOperationUtils.removeSpaces(finalCandidateTokens)
                .forEach(toBeRemoved -> finalCandidateTokens.remove(toBeRemoved));

        return finalCandidateTokens;
    }
}

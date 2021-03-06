package com.company.service;

import java.util.List;

public class TokenChecker {
    private String tokenCandidate;
    private final List<String> tokens;

    public TokenChecker(String tokenCandidate, List<String> tokens) {
        this.tokenCandidate = tokenCandidate;
        this.tokens = tokens;
    }

    public String checkToken(){
        boolean isToken = tokens.contains(tokenCandidate);
        if(isToken)
            return "token";
        if(checkNumber(tokenCandidate) || checkString(tokenCandidate))
            return "constant";
        if(this.checkIdentifier(tokenCandidate))
            return "identifier";

        return "";
    }

    public boolean checkNumber(String numberCandidate){
        if(numberCandidate.equals("0"))
            return true;
        String numberRegex = "^[-+]?[1-9]+[0-9]*$";
        return numberCandidate.matches(numberRegex);
    }

    public boolean checkString(String stringCandidate){
        String stringRegex = "^[\"\\'][_a-zA-Z0-9]+[\"\\']$";
        return stringCandidate.matches(stringRegex);
    }

    public boolean checkIdentifier(String identifierCandidate){
        String identifierRegex = "^[_a-zA-Z][_a-zA-Z0-9]*$";
        return identifierCandidate.matches(identifierRegex);
    }

    public String getTokenCandidate() {
        return tokenCandidate;
    }

    public void setTokenCandidate(String tokenCandidate) {
        this.tokenCandidate = tokenCandidate;
    }
}

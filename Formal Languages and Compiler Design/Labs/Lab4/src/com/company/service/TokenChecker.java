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
        return "";
    }

    public String getTokenCandidate() {
        return tokenCandidate;
    }

    public void setTokenCandidate(String tokenCandidate) {
        this.tokenCandidate = tokenCandidate;
    }
}

package com.company.service;

import java.util.ArrayList;
import java.util.List;

public class StringOperationUtils {

    /**
     * Method which generates all the tokens which are blank spaces and are needed to be removed
     * @param tokenCandidates - the list of all the tokens extracted from the source code
     * @return {@code} List<String> toBeRemoved - a list of all the spaces which need to be removed from the candidates
     */
    public static List<String> removeSpaces(List<String> tokenCandidates){
        List<String> toBeRemoved = new ArrayList<>();
        tokenCandidates.forEach(candidate -> {
            if(candidate.equals(""))
                toBeRemoved.add(candidate);
        });
        return toBeRemoved;
    }
}

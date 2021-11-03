package com.company.domain;

import java.util.*;

public class PIF {
    private List<Map.Entry<String, Integer>> table;

    public PIF() {
        this.table = new ArrayList<>();
    }

    public void addToPIF(String token, Integer index){
        Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>(token, index);
        table.add(entry);
    }

    @Override
    public String toString() {
        String format = "PIF table: \n\n";

        for(Map.Entry<String, Integer> keyValuePair: table ){
            format += keyValuePair.getKey() + " -> " + keyValuePair.getValue() + "\n";
        }

        return format;
    }
}

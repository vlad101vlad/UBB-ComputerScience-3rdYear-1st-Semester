package com.company.domain;

import java.util.*;

public class PIF {
    private final List<Map.Entry<String, Map.Entry<Integer, Integer>>> table;

    public PIF() {
        this.table = new ArrayList<>();
    }

    public void addToPIF(String token, Map.Entry<Integer, Integer> index){
        Map.Entry<String, Map.Entry<Integer, Integer>> entry = new AbstractMap.SimpleEntry<>(token, index);
        table.add(entry);
    }

    @Override
    public String toString() {
        String format = "PIF table: \n\n";

        for(Map.Entry<String, Map.Entry<Integer, Integer>> keyValuePair: table ){
            format += keyValuePair.getKey() + " -> " +
                    "(" + keyValuePair.getValue().getKey() + " ," + keyValuePair.getValue().getValue() + ")" + "\n";
        }

        return format;
    }
}

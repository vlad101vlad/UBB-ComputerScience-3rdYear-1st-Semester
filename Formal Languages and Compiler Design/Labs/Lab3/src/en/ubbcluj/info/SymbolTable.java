package en.ubbcluj.info;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable {
    private final int symbolTableLength;
    private final Map<Integer, List<Map.Entry<String, String>>> symbolTable;

    public SymbolTable(int symbolTableLength) {
        this.symbolTableLength = symbolTableLength;
        this.symbolTable = new HashMap<>();

        for(int i = 0; i < symbolTableLength; i++){
            symbolTable.put(i, new ArrayList<>());
        }
    }

    private int hashFunction(String key){
        return key.length() % symbolTableLength;
    }

    public int addToTable(String type, String toBeAdded){
        int hashResult = this.hashFunction(toBeAdded);
        List<Map.Entry<String, String>> hashKeyResults = this.symbolTable.get(hashResult);

        for(int i = 0; i < hashKeyResults.size(); i++){
            Map.Entry<String, String> currentPair = hashKeyResults.get(i);
            if(currentPair.getKey().equals(toBeAdded) && currentPair.getValue().equals(type))
                return i;
        }

        hashKeyResults.add(new AbstractMap.SimpleEntry<>(toBeAdded, type));
        return hashKeyResults.size() - 1;
    }
}

///// hash -> pos (list(hash)) ; Sy[hash][pos]